import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Index from '../views/sys/Index.vue'

import request from "../utils/request";
import store from "../store"

Vue.use(VueRouter)
//解决编程式路由往同一地址跳转时会报错的情况
const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;
//push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch(err => err);
};
//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalReplace.call(this, location, onResolve, onReject);
    return originalReplace.call(this, location).catch(err => err);
};

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home,
        redirect: {name: 'Index'},
        children: [
            {
                path: '/index',
                name: 'Index',
                meta: {
                    title: "系统首页"
                },
                component: Index
            },
            {
                path: '/person',
                name: 'Person',
                meta: {
                    title: "个人中心"
                },
                component: () => import('@/views/sys/Person.vue')
            },
            {
                path: '/repassword',
                name: 'Repassword',
                meta: {
                    title: "修改密码"
                },
                component: () => import('@/views/sys/Repassword.vue')
            }
            // {
            // 	path: '/sys/users',
            // 	name: 'SysUser',
            // 	component: User
            // },
            // {
            // 	path: '/sys/roles',
            // 	name: 'SysRole',
            // 	component: Role
            // },
            // {
            // 	path: '/sys/menus',
            // 	name: 'SysMenu',
            // 	component: Menu
            // },
        ]
    },

    {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login.vue')
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {


    let hasRoute = store.state.hasRoute
    let token = localStorage.getItem("token")
    localStorage.setItem("currentPathName", to.meta.title)
    store.commit("setPath")  //触发store的数据更新
    if (to.path == '/login') {
        next()

    } else if (!token) {
        next({path: '/login'})
    } else if (token && !hasRoute) {
        request.get("/menu/getMenusByUsername", {
            headers: {
                Authorization: localStorage.getItem("token")
            }
        }).then(res => {


            // 动态绑定路由
            let newRoutes = router.options.routes

            res.data.forEach(menu => {
                if (menu.component != '') {
                    // 转成路由
                    let route = menuToRoute(menu)

                    // 吧路由添加到路由管理中
                    if (route) {
                        newRoutes[0].children.push(route)
                    }
                }
                if (menu.parentId == 0 && menu.children.length > 0) {

                    menu.children.forEach(e => {

                        // 转成路由
                        let route = menuToRoute(e)

                        // 吧路由添加到路由管理中
                        if (route) {
                            newRoutes[0].children.push(route)
                        }

                    })
                }
            })


            router.addRoutes(newRoutes)

            hasRoute = true
            store.commit("changeRouteStatus", hasRoute)
        })
    }


    next()
})


// 导航转成路由
const menuToRoute = (menu) => {

    if (!menu.component) {
        return null
    }


    let route = {
        name: menu.perms,
        path: menu.path,
        meta: {
            icon: menu.icon,
            title: menu.title
        }
    }

    route.component = () => import('@/views/' + menu.component + '.vue')

    return route
}

export default router
