<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-input
            v-model="name"
            clearable
            placeholder="名称"
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="load">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true">新增角色</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="del(null)">
          <el-button slot="reference" :disabled="delBtlStatu" type="danger">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>
    <el-table
        ref="multipleTable"
        :data="tableData"
        border
        style="width: 100%"
        tooltip-effect="dark"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          align="center"
          label="ID"
          prop="id"
          width="60">
      </el-table-column>
      <el-table-column
          label="名称"
          prop="name"
          width="120">
      </el-table-column>
      <el-table-column
          label="唯一标识"
          prop="perms"
      >
      </el-table-column>
      <el-table-column
          label="描述"
          prop="description"
      >
      </el-table-column>
      <el-table-column
          align="center"
          label="当前状态"
          prop="statu"
      >
        <template slot-scope="scope">
          <el-button v-if="scope.row.statu==0" type="text">正常</el-button>
          <el-button v-if="scope.row.statu==1" type="text">禁用</el-button>
        </template>
      </el-table-column>
      <el-table-column

          align="center"
          label="操作"
          width="300"
      >
        <template slot-scope="scope">
          <el-button type="success" @click="editPerms(scope.row.id)">分配菜单<i class="el-icon-menu"></i></el-button>
          <el-button type="info" @click="edit(scope.row)">编辑</el-button>
          &nbsp;
          <el-popconfirm title="这是一段内容确定删除吗？" @confirm="del(scope.row.id)">
            <el-button slot="reference" type="danger">删除</el-button>
          </el-popconfirm>

        </template>


      </el-table-column>
    </el-table>
    <el-pagination
        :current-page="current"
        :page-size="size"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
    </el-pagination>


    <div>
      <el-dialog :visible.sync="dialogVisible" title="更新角色" v-bind="$attrs" @close="onClose" @open="onOpen"
                 v-on="$listeners">
        <el-form ref="editForm" :model="editForm" :rules="ruleForm" label-width="100px" size="medium">
          <el-form-item label="角色名称" prop="name">
            <el-input v-model="editForm.name" :maxlength="10" :style="{width: '100%'}" clearable
                      placeholder="请输入角色名称" prefix-icon='el-icon-files'></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="description">
            <el-input v-model="editForm.description" :maxlength="10" :style="{width: '100%'}" clearable
                      placeholder="请输入描述" prefix-icon='el-icon-caret-top'></el-input>
          </el-form-item>
          <el-form-item label="权限编码" prop="perms">
            <el-input v-model="editForm.perms" :maxlength="10" :style="{width: '100%'}" clearable
                      placeholder="请输入权限编码" prefix-icon='el-icon-s-finance'></el-input>
          </el-form-item>
          <el-form-item label="当前状态" prop="statu">
            <el-radio-group v-model="editForm.statu" size="medium">
              <el-radio-button v-for="(item, index) in statuOptions" :key="index" :disabled="item.disabled"
                               :label="item.value">{{ item.label }}
              </el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="close">取消</el-button>
          <el-button type="primary" @click="handelConfirm">确定</el-button>
        </div>
      </el-dialog>
    </div>
    <el-dialog
        :visible.sync="permDialogVisible"
        title="分配权限"
        width="600px">

      <el-form :model="permForm">

        <el-tree
            ref="permTree"
            :check-strictly=true
            :data="menusTree"
            :default-expand-all=true
            :props="defaultProps"
            node-key="id"
            show-checkbox>
        </el-tree>

      </el-form>

      <span slot="footer" class="dialog-footer">
			    <el-button @click="permDialogVisible = false">取 消</el-button>
			    <el-button type="primary" @click="submitPermFormHandle('permForm')">确 定</el-button>
			</span>

    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "Role",
  data() {

    return {
      name: '',
      editForm: {
        name: undefined,
        description: undefined,
        perms: undefined,
        statu: 0,
      },
      ruleForm: {
        name: [{
          required: true,
          message: '请输入角色名称',
          trigger: 'blur'
        }],
        description: [{
          required: true,
          message: '请输入描述',
          trigger: 'blur'
        }],
        perms: [{
          required: true,
          message: '请输入权限编码',
          trigger: 'blur'
        }],
        statu: [{
          required: true,
          message: '当前状态不能为空',
          trigger: 'change'
        }],
      },
      statuOptions: [{
        "label": "正常",
        "value": 0
      }, {
        "label": "禁用",
        "value": 1
      }],
      dialogVisible: false,
      delBtlStatu: true,

      current: 1,
      size: 10,
      total: 0,
      tableData: [],
      multipleSelection: [],

      permDialogVisible: false,
      permForm: {},
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      menusTree: []
    }

  },
  created() {
    this.load()
  },
  methods: {
    reset() {
      this.editForm = {
        name: undefined,
        description: undefined,
        perms: undefined,
        statu: 0,
      }
    },
    load() {

      this.request.get("/role/page", {
        params: {
          pageNum: this.current,
          pageSize: this.size,
          name: this.name,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.current = res.data.current
        this.size = res.data.size
        this.total = res.data.total
      })
      this.request.get("/menu/list").then(res => {
        this.menusTree = res.data
      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.load()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.current = val
      this.load()
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.delBtlStatu = val.length == 0
    },

    del(id) {
      var ids = []

      if (id) {
        ids.push(id)
      } else {
        this.multipleSelection.forEach(row => {
          ids.push(row.id)
        })

      }

      this.request.delete("/role/" + ids).then(res => {
        this.$message.success('删除成功')
        this.load()
      })

    },
    edit(entity) {
      this.editForm = JSON.parse(JSON.stringify(entity))
      this.dialogVisible = true
    },
    onOpen() {
    },
    onClose(){
      this.reset()
    },
    close() {
      this.$emit('update:visible', false)

      this.reset()
      this.dialogVisible = false
    },
    handelConfirm() {
      this.$refs['editForm'].validate(valid => {
        if (!valid) return
        this.request.post("/role", this.editForm).then(res => {
          this.$message.success('操作成功')


          this.load()
          this.close()
          this.$nextTick(() => {
            this.$refs['editForm'].clearValidate();
          })
        })

      })
    },
    editPerms(id) {
      this.permDialogVisible = true
      this.request.get("/role/info/" + id).then(res => {
        this.$refs.permTree.setCheckedKeys(res.data.menusId)
        this.permForm = res.data

      })


    },
    submitPermFormHandle(formName) {

      this.permForm.menusId = this.$refs.permTree.getCheckedKeys()
      this.request.post("/role/perm", this.permForm).then(res => {
        this.$message.success('操作成功')
        this.permDialogVisible = false


      })

    }
  }
}
</script>

<style scoped>

</style>
