<template>
  <div>

    <el-form :inline="true">
      <el-form-item>
        <el-input
            v-model="name"
            placeholder="名称"
            clearable
        >
        </el-input>
      </el-form-item>

      <el-form-item>
        <el-button @click="load">搜索</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true">新增用户</el-button>
      </el-form-item>
      <el-form-item>
        <el-popconfirm title="这是确定批量删除吗？" @confirm="del(null)">
          <el-button type="danger" slot="reference" :disabled="delBtlStatu">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
    </el-form>
    <el-table
        ref="multipleTable"
        :data="tableData"
        border
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column
          prop="id"
          label="ID"
          align="center"
          width="60">
      </el-table-column>
      <el-table-column
          prop="avatar"
          label="头像"
          align="center"
          width="70"
      >
        <template slot-scope="scope">
          <el-avatar :size="40" :src="scope.row.avatar"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column
          prop="username"
          label="账户名"
          align="center"
      >
      </el-table-column>
      <el-table-column

          label="所属角色"
          align="center"
      >
        <template slot-scope="scope">

          <el-button type="text" v-for="(item,index) in scope.row.roles" :key="index">
            {{ item }}

          </el-button>

        </template>
      </el-table-column>
      <el-table-column
          prop="nickname"
          label="昵称"
          align="center"
      >
      </el-table-column>
      <el-table-column
          prop="statu"
          label="当前状态"
          align="center"
      >
        <template slot-scope="scope">
          <el-button v-if="scope.row.statu==0" type="success">正常</el-button>
          <el-button v-if="scope.row.statu==1" type="warning">禁用</el-button>
        </template>
      </el-table-column>

      <el-table-column

          label="操作"
          align="center"
          width="340"
      >
        <template slot-scope="scope">
          <el-button type="success" @click="roleHandle(scope.row.id)">分配角色<i class="el-icon-menu"></i></el-button>
          <el-button type="info" @click="edit(scope.row)">编辑</el-button>
          <el-button type="primary" v-if="scope.row.statu==0" @click="changeStatu(scope.row)">禁用</el-button>
          <el-button type="danger" v-if="scope.row.statu==1" @click="changeStatu(scope.row)">启用</el-button>
          &nbsp;
          <el-popconfirm title="系统数据，确定删除吗？" @confirm="del(scope.row.id)">
            <el-button type="danger" slot="reference">删除</el-button>
          </el-popconfirm>

        </template>


      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[10, 20, 50, 100]"
        :current-page="current"
        :page-size="size"
        :total="total">
    </el-pagination>


    <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="dialogVisible" @open="onOpen" @close="onClose"
               title="更新用户">
      <el-form ref="editForm" :model="editForm" :rules="ruleForm" size="medium" label-width="100px">
        <el-form-item label="账户" prop="username">
          <el-input v-model="editForm.username" placeholder="请输入账户" :maxlength="10" clearable
                    prefix-icon='el-icon-user-solid' :style="{width: '100%'}"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="editForm.nickname" placeholder="请输入昵称" :maxlength="10" clearable
                    prefix-icon='el-icon-user' :style="{width: '100%'}"></el-input>
        </el-form-item>
        <!--处理用户头像上传失败的问题-->
        <el-form-item label="头像" prop="avatar">
<!--          <el-upload ref="avatar" :file-list="false" :onsuccess="avatarSuccess" :action="avatarAction"-->
<!--                     :before-upload="avatarBeforeUpload">-->
<!--            <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>-->
<!--          </el-upload>-->
          <el-upload
              class="avatar-uploader"

              action="http://localhost:8888/file/upload"
              :show-file-list="false"

              :on-success="avatarSuccess"
          >
            <el-avatar  class="avatar avatar-uploader" icon="el-icon-user-solid" :src="editForm.avatar"></el-avatar>

          </el-upload>
        </el-form-item>

        <el-form-item>
          <el-alert
              title="默认密码为12345"
              type="success"
              :closable="false">
          </el-alert>
        </el-form-item>

      </el-form>
      <div slot="footer">
        <el-button @click="close">取消</el-button>
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
    <!--    分配角色-->
    <!-- 分配权限对话框 -->
    <el-dialog title="分配角色" :visible.sync="roleDialogFormVisible" width="600px">

      <el-form :model="roleForm">
        <el-tree
            :data="roleTreeData"
            show-checkbox
            ref="roleTree"
            :check-strictly=checkStrictly
            node-key="id"
            :default-expand-all=true
            :props="defaultProps">
        </el-tree>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="roleDialogFormVisible=false">取 消</el-button>
        <el-button type="primary" @click="submitRoleHandle('roleForm')">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      name: '',
      diaRoleVisible: false,
      size: 10,
      total: 0,
      current: 1,
      tableData: [],
      multipleSelection: [],
      delBtlStatu: true,
      dialogVisible: false,

      editForm: {
        username: undefined,
        nickname: undefined,
        avatar: null,
      },
      userRoleForm: '',
      ruleForm: {
        username: [{
          required: true,
          message: '请输入单行文本账户',
          trigger: 'blur'
        }],
        nickname: [{
          required: true,
          message: '请输入昵称',
          trigger: 'blur'
        }],

      },
      avatarAction: 'http://localhost:8888/file/upload',
      avatarfileList: [],
      roleDialogFormVisible: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      roleForm: {},
      roleTreeData: [],
      treeCheckedKeys: [],
      checkStrictly: true

    }
  },
  created() {
    this.load()
  },
  methods: {
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.size = val
      this.load()
    },
    avatarSuccess(res) {
      this.editForm.avatar = res
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
    reset() {
      this.editForm = {
        username: undefined,
        nickname: undefined,
        avatar: null,
      }

    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      this.delBtlStatu = val.length == 0
    },
    load() {
      this.request.get("/user/page", {
        params: {
          name: this.name,
          pageNum: this.current,
          pageSize: this.size
        }
      }).then(res => {
        this.tableData = res.data.records
        this.current = res.data.current
        this.size = res.data.size
        this.total = res.data.total

      })

      this.request.get("/role").then(res => {
        this.roleTreeData = res.data
      })

    },
    roleHandle(id) {
      this.roleDialogFormVisible = true

      this.request.get('/user/info/' + id).then(res => {

        this.roleForm = res.data
        let roleIds = res.data.roleIds

        this.$refs.roleTree.setCheckedKeys(roleIds)
      })
    },
    submitRoleHandle(roleForm) {
      var roleIds = this.$refs.roleTree.getCheckedKeys()
      this.roleForm.roleIds = roleIds


      this.request.post('/user/perm/', this.roleForm).then(res => {
        this.$message.success('操作成功！')
        this.load()

        this.roleDialogFormVisible = false
      })
    },
    edit(entity) {
      this.editForm = JSON.parse(JSON.stringify(entity))
      this.dialogVisible = true
    },
    changeStatu(entity) {
      this.editForm = JSON.parse(JSON.stringify(entity))

      if (entity.statu == 0) {


        this.editForm.statu = 1

      } else if (entity.statu == 1) {

        this.editForm.statu = 0

      }
      this.request.post("/user", this.editForm).then(res => {

        this.$message('操作成功')
        this.load()
      })


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

      this.request.delete("/user/" + ids).then(res => {
        this.$message.success('删除成功')
        this.load()
      })

    },
    onOpen() {
    },
    onClose() {
      this.$refs['editForm'].resetFields()
    },
    close() {
      this.reset()
      this.onClose()
      this.dialogVisible = false
    },
    handelConfirm() {
      this.$refs['editForm'].validate(valid => {
        if (!valid) return
        this.request.post("/user", this.editForm).then(res => {
          this.$message.success('操作成功')
          this.load()
          this.close()

        })

      })
    },
    avatarBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 10
      if (!isRightSize) {
        this.$message.error('文件大小超过 10MB')
      }
      return isRightSize
    },
  }
}
</script>

<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>

