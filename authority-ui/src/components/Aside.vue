<template>
  <el-menu style="height: 100%"
           background-color="#2F4056"
           text-color="#fff"
           active-text-color="#ffd04b"
           default-active="/index"
  >
    <div v-for="item in menus" >
      <el-submenu :index="(item.id).toString()" v-if="item.path==''" >
        <template slot="title"><i :class="item.icon"></i>{{ item.title }}</template>
        <div v-for="child in item.children">
          <router-link :to="child.path">
            <el-menu-item :index="(child.id).toString()"><i :class="child.icon"></i>{{ child.title }}</el-menu-item>
          </router-link>
        </div>
      </el-submenu>
      <router-link :to="item.path" v-else>
        <el-menu-item :index="item.path"><i :class="item.icon"></i>{{ item.title }}</el-menu-item>
      </router-link>
    </div>

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  data() {
    return {
      menus: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/menu/getMenusByUsername").then(res => {
        this.menus=res.data
      })
    }
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}
</style>
