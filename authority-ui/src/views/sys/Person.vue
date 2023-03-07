<template>
  <div>

    <el-row>
      <el-col :span="10"><div class="grid-content bg-purple-dark">
    <el-card>
      <el-upload
          class="avatar-uploader"

          action="http://localhost:8888/file/upload"
          :show-file-list="false"

          :on-success="handleAvatarSuccess"
          >

        <el-avatar  class="avatar avatar-uploader" icon="el-icon-user-solid" :src="personform.avatar"></el-avatar>

      </el-upload>

      <el-form ref="personform" :model="personform" :rules="rulesform" size="medium" label-width="100px"
               label-position="left">
        <el-form-item label="账户名" prop="username">
          <el-input v-model="personform.username" placeholder="请输入账户名" :maxlength="10" :disabled='true'
                    clearable prefix-icon='el-icon-s-custom' :style="{width: '100%'}"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="personform.nickname" placeholder="请输入昵称" :maxlength="5" clearable
                    prefix-icon='el-icon-user-solid' :style="{width: '100%'}"></el-input>
        </el-form-item>
        <el-form-item size="large">
          <el-button type="primary" style="margin-left: 70px" @click="submitForm">确认修改</el-button>

        </el-form-item>
      </el-form>
    </el-card>


      </div></el-col>
    </el-row>
  </div>
</template>
<script>
import router from "@/router";

export default {
  name:'Person',
  components: {},
  props: [],
  data() {
    return {
      personform: {
        username: undefined,
        nickname: undefined,
        avatar:undefined,
      },
      rulesform: {
        username: [{
          required: true,
          message: '请输入账户名',
          trigger: 'blur'
        }],
        nickname: [{
          required: true,
          message: '请输入昵称',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {
    this.load()
  },
  mounted() {},
  methods: {
    load(){
      this.request.get("/user/getUserInfo").then(res=>{
        this.personform=res.data
      })
    },
    handleAvatarSuccess(res) {

      this.personform.avatar = res
      this.save()
    },

    // beforeAvatarUpload(file) {
    //   const isJPG = file.type === 'image/jpeg';
    //   const isLt2M = file.size / 1024 / 1024 < 2;
    //
    //
    //   // return isJPG && isLt2M;
    // },
    submitForm() {
      this.$refs['personform'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        this.request.post("/user",this.personform).then(res=>{
          this.$message.success('修改成功')
          this.$router.go(0)
        })
      })
    },
    resetForm() {
      this.$refs['personform'].resetFields()
    },
  }
}

</script>
<style>
.avatar-uploader{
  margin-left: 100px;

}
</style>
