<template>
  <div id="bg">

    <el-row style="height: 200px;">
    </el-row>
    <el-row style="height: 300px;">


      <el-col :span="8" :offset="8">

        <el-card style="height: 100%">
          <div style="height: 30%;text-align: center;line-height: 500%;font-size: 20px">欢迎登录</div>
          <el-form style="height: 70%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form-item label="账户名" prop="username">
              <el-input v-model="ruleForm.username"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="ruleForm.password" type="password"></el-input>
            </el-form-item>
            <el-form-item style="margin-left:10%">
              <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
              <el-button @click="resetForm('ruleForm')">重置</el-button>
              <el-button @click="handAdd" type="text">立即注册</el-button>
            </el-form-item>
          </el-form>
        </el-card>

        <div>


        </div>
      </el-col>
    </el-row>

    <template>
      <div>
        <el-dialog v-bind="$attrs" v-on="$listeners" :visible.sync="dialogVisible" @open="onOpen" @close="onClose"
                   title="欢迎注册">
          <el-form ref="registerform" :model="registerform" :rules="rulesform" size="medium" label-width="100px"
                   label-position="left">
            <el-form-item label="账户名" prop="username">
              <el-input v-model="registerform.username" placeholder="请输入账户名" :maxlength="10" clearable
                        prefix-icon='el-icon-s-custom' :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="registerform.nickname" placeholder="请输入昵称" :maxlength="5" clearable
                        prefix-icon='el-icon-user-solid' :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input v-model="registerform.password" placeholder="请输入密码" :maxlength="10" clearable
                        prefix-icon='el-icon-lock' show-password :style="{width: '100%'}"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="repassword">
              <el-input v-model="registerform.repassword" placeholder="请确认密码" :maxlength="10" clearable
                        prefix-icon='el-icon-lock' :style="{width: '100%'}"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer">
            <el-button @click="close">取消</el-button>
            <el-button type="primary" @click="handelConfirm">确定</el-button>
          </div>
        </el-dialog>
      </div>
    </template>
    <!--    <script>-->
    <!--      export default {-->
    <!--        inheritAttrs: false,-->
    <!--        components: {},-->
    <!--        props: [],-->
    <!--        data() {-->
    <!--          return {-->

    <!--          }-->
    <!--        },-->
    <!--        computed: {},-->
    <!--        watch: {},-->
    <!--        created() {},-->
    <!--        mounted() {},-->
    <!--        methods: {-->

    <!--        }-->
    <!--      }-->

    <!--    </script>-->
    <!--    <style>-->
    <!--    </style>-->


  </div>
</template>


<script>

import qs from "qs";
import axios from "axios";

export default {

  name: "Login",
  data() {
    return {
      ruleForm: {
        username: '',
        password: '',
        // token: ''

      },
      dialogVisible: false,
      registerform: {
        username: undefined,
        nickname: undefined,
        password: undefined,
        repassword: undefined,
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
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }],
        repassword: [{
          required: true,
          message: '请确认密码',
          trigger: 'blur'
        }],
      },
      rules: {
        username: [
          {required: true, message: '请输入账户名', trigger: 'blur'},
          {min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 5, max: 10, message: '长度在 5 到 10 个字符', trigger: 'blur'}
        ],

      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      localStorage.clear()
    },
    submitForm(formName) {
      console.log(this.ruleForm)
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post("/login?" + qs.stringify(this.ruleForm)).then(res => {

            if (res.data.code == 200) {

              localStorage.setItem("token",res.headers['token'])
              this.$message.success('登录成功')
              this.$router.push('/index')

            } else {
              this.$message.error(res.data.msg)
              return false;
            }
          })
          // axios.post("/login?"+qs.stringify(this.ruleForm)).then(res=>{
          //   this.$message.success('登陆成功')
          //   localStorage.setItem("token",res.headers['token'])

          // })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      formName = {}
    },
    onOpen() {
    },
    onClose() {
      this.$refs['registerform'].resetFields()
      this.resetForm("registerform")
      this.dialogVisible = false
    },
    close() {
      this.$emit('update:visible', false)
      this.onClose()
    },
    handelConfirm() {
      this.$refs['registerform'].validate(valid => {
        if (!valid) return
        if (this.registerform.password != this.registerform.repassword) {
          this.$message.error('两次密码输入不一致！')
          return false;
        }

        this.request.post("/user/register", this.registerform).then(res => {
          this.$message.success('注册成功，请登录')
          this.close()
        })

      })
    },
    handAdd() {
      this.dialogVisible = true
    }
  }
}
</script>

<style scoped>
#bg {
  height: 100%;
  background-color: #393D49;
}




</style>
