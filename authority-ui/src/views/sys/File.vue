<template>
  <div>
    <!-- 1.2.2.(2)新增、删除、导入、导出按钮 -->
    <div style="margin:10px 0">
      <el-upload action="http://localhost:8888/file/upload" :show-file-list="false"
                 :on-success="handleFileUploadSuccess" style="display:inline-block">
        <el-button type="primary">
          上传文件
          <i class="el-icon-top"></i>
        </el-button>
      </el-upload>
      <el-button type="danger" @click="delBatch" class="ml-5">
        批量删除
        <i class="el-icon-remove-outline"></i>
      </el-button>

    </div>

    <!-- 1.2.2.(3)表格 -->
    <el-table
        :data="tableData"
        border
        stripe
        :header-cell-class-name="headerBg"
        @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" align="center" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="50"></el-table-column>
      <el-table-column prop="name" label="文件名称"></el-table-column>
      <el-table-column prop="type" label="文件类型" width="120"></el-table-column>
      <el-table-column prop="size" label="文件大小KB" width="190"></el-table-column>
      <el-table-column label="下载" width="120">
        <template slot-scope="scope">
          <el-button type="primary" @click="download(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="启用"
      width="120"
      >
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc"
                     @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="操作" align="center">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-10"
              confirm-button-text="确定"
              cancel-button-text="我再想想"
              icon="el-icon-info"
              icon-color="red"
              title="这是一段内容确定删除吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">
              删除
              <i class="el-icon-remove-outline"></i>
            </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 1.2.2(4)分页查询 -->
    <div class="block ml-5">
      <!--
            current-page(当前页面)=pageNum(前端下面传过来的)
            page-size(每页条数)=pagesize(前端下面传过来的)
            :total=total(后台传过来的)
            @size-change="handleSizeChange"
      -->
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
      ></el-pagination>

    </div>
  </div>
</template>

<script>
import {Loading} from "element-ui";

export default {
  name: "File",
  data() {
    return {
      tableData: [],
      name: '',
      multipleSelection: [],
      pageNum: 1,
      pageSize: 10,
      total: 0,
      headerBg: "headerBg"
    }
  },
  created() {
    //执行create生命周期函数之前，我们已经拿到了data数据，和methods方法了

    this.load();
  },
  methods: {
    load() {
      this.request.get("/file/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }

      }).then(res => {
        // console.log(res.data);
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
    },
    changeEnable(row) {
      this.request.post("/file/update", row).then(res => {
        if (res.code === '200') {
          this.$message.success("操作成功")
        }
      })
    },
    // 【重置】
    reset() {
      this.username = "";
      this.email = "";
      this.address = "";
      this.load();
    },
    // 【删除】
    del(id) {
      this.request.delete("/file/" + id).then(res => {
        console.log(res.code)
        if (res.code === 200) {

          this.$message.success("删除成功！");
          // if (id) {
          //   //如果当前页面没有数据，跳转到上一页 (暂时写不到)
          // }
          this.load(); //重新加载页面
        } else {
          this.$message.error("删除失败！");
        }
      })
    },
    // 【表格绑定好的】
    handleSelectionChange(val) {
      console.log(val);
      this.multipleSelection = val;
    },
    // 【批量删除】
    delBatch() {
      let ids = this.multipleSelection.map(v => v.id); // [{}, {}, {}] => [1, 2, 3]     对象数组 变为 纯id数组
      this.request.post("/file/del/batch", ids).then(res => {
        if (ids.length !== 0) {
          console.log(ids);
          this.$confirm("是否确认删除这" + ids.length + "条数据?", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
              .then(() => {
                return batchDelete(rows);
              })
              .then(() => {
                this.created();
                this.msgSuccess("删除成功");
              })
        }
        if (res.code === '200') {
          this.$message.success("批量删除成功！");
          this.load(); //重新加载页面
        } else {
          this.$message.error("未选择数据！");
        }
      });
    },
    // 【分页】 - val当前页码
    handleSizeChange(val) {
      console.log(val);
      this.pageSize = val;
      this.load();
    },
    // 【分页】 - 当前为val条数据每页
    handleCurrentChange(val) {
      console.log(val);
      this.pageNum = val;
      this.load();
    },
    // 【上传成功】 回调函数
    handleFileUploadSuccess(res) {
      console.log(res)
      this.load()
    },
    // 【下载】
    download(url) {
      window.open(url)
    }
  }

}
</script>

<style>

</style>
