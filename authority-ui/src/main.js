import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '../src/assets/all.css'
import request from "@/utils/request"
Vue.use(ElementUI,{size:'small'})

Vue.config.productionTip = false

Vue.prototype.request=request

new Vue({
    router,
    store,
    render: function (h) {
        return h(App)
    }
}).$mount('#app')
