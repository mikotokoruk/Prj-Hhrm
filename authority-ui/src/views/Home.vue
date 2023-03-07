<template>
  <div style="height: 100%">

    <el-container style="height:100%">

      <el-aside width="200px" style="background-color: rgb(140,140,179);height: 100%!important;">
        <Aside></Aside>
      </el-aside>

      <el-container>
        <el-header style="height: 60px">
          <el-row>
            <el-col :span="20">
              <div class="grid-content bg-purple">

                <el-tooltip content="全屏显示" placement="top" effect="dark">
                  <i class="el-icon-crop" @click="fullScreen" style="margin-top: 15px;color: #FFB800"> </i>
                </el-tooltip>
                <span style="line-height: 60px;color:  #FFB800 ">Hhrm</span>

              </div>
            </el-col>

            <el-col :span="1">
              <div class="grid-content bg-purple">
                <el-avatar :size="40" :src="userInfo.avatar" style="margin-top: 10px"></el-avatar>


              </div>
            </el-col>
            <el-col :span="2">
              <el-dropdown>
                <span>{{ userInfo.nickname }}</span>
                <i class="el-icon-arrow-down"></i>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item to="">
                    <router-link :to="{name: 'Person'}">个人信息</router-link>
                  </el-dropdown-item>
                  <el-dropdown-item>
                    <router-link :to="{name: 'Repassword'}">修改密码</router-link>
                  </el-dropdown-item>
                  <el-dropdown-item><span @click="logout">退出系统</span></el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </el-col>
          </el-row>


        </el-header>

        <el-main>
          <div style="margin-top: 10px;margin-bottom: 30px">
            <el-breadcrumb separator="/" style="font-size: 16px;font-weight: inherit">
              <el-breadcrumb-item><i class="el-icon-s-unfold">{{ currentPathName }}</i>
              </el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Aside from "@/components/Aside";
import screenfull from 'screenfull'

export default {
  components: {
    Aside
  },
  data() {

    const item = {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    };
    return {
      circleUrl: 'http://localhost:8888/file/598093f448214453bcbbe890d34520d5.PNG',
      tableData: Array(10).fill(item),
      userInfo: {
        username: '',
        nickname: '',
        avatar: ''
      }
    }

  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;
    }
  },
  watch: {  //百度搜索，监听路由的变化
    currentPathName(newVal, oldVal) {

    }
  },
  created() {
    this.getuserInfo()
  },
  methods: {
    getuserInfo() {
      this.request.get("/user/getUserInfo").then(res => {
        this.userInfo = res.data
      })
    },

    logout() {
      this.request.post("/user/clear").then(res=>{
        this.request.post("/logout")
        this.$message.success('退出成功！')
        localStorage.clear()
        this.$store.commit("resetState")

        this.$router.push("/login")
      })


    },
    fullScreen() {
      if (!screenfull.isEnabled) {
        this.$message({message: '你的浏览器不支持全屏', type: 'warning'})
        return false
      }

      screenfull.toggle()
    }
  }
};
</script>

<style>
.el-header {
  background-color:#1E9FFF;
  color: #333;
  line-height: 60px;
}

.el-menu {
  border-bottom-right-radius: 2px;
}

.el-aside {
  color: #333;
}

.router-link-active {
  text-decoration: none;
  color: black;
}

a {
  text-decoration: none;
  color: black;
}
</style>
