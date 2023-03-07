<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <el-card style="margin-top: 20px;height: 600px;width: 500px">
            <div style="text-align: center;line-height: 40px;font-size: larger"><h3>修改密码</h3></div>
            <el-divider></el-divider>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                     class="demo-ruleForm">
              <el-form-item label="原密码" prop="oldpass" required>
                <el-input type="password" style="width: 300px" v-model="ruleForm.oldpass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="pass" required>
                <el-input type="password" style="width: 300px" v-model="ruleForm.pass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass" required>
                <el-input type="password" style="width: 300px" v-model="ruleForm.checkPass"
                          autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">确认修改</el-button>

              </el-form-item>
            </el-form>
          </el-card>
        </div>
      </el-col>

    </el-row>


  </div>
</template>

<script>


export default {
  name: "Menu",
  data() {
    var validatePass = (rule, value, callback) => {

      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {

      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userInfo: {},

      ruleForm: {
        oldpass: '',
        pass: '',
        checkPass: '',
        age: ''
      },
      passform:{
        oldpass:'',
        newpass:''
      },
      rules: {
        oldpass: [
          {required: true, message: '请输入旧密码', trigger: 'blur'},

        ],
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.getUserInfo()
  },
  methods: {
    getUserInfo() {
      this.request.get('/user/getUserInfo').then(res => {

        this.userInfo = res.data
      })
    },
    logout(){
      localStorage.clear()
      sessionStorage.clear()
      this.$store.commit("resetState")
      this.$router.push("/login")
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.userInfo.password=this.ruleForm.oldpass
          if(this.ruleForm.oldpass==this.ruleForm.pass){
            this.$message.error('新密码和旧密码不能一样')
            return  false

          }
          this.passform.newpass=this.ruleForm.pass
          this.passform.oldpass=this.ruleForm.oldpass
          console.log(this.passform)
          this.request.post("/user/repassword",this.passform).then(res=>{
            this.$message.success('修改成功！')
          })



        } else {

          return false;
        }
      });
    },
  }
}
</script>

<style scoped>

</style>
