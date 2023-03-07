import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: '',
        hasRoute:false,
        menuList: [],

        currentPathName: ''


    },
    getters: {},
    mutations: {
        setPath (state) {
            state.currentPathName = localStorage.getItem("currentPathName")
        },
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        resetState:(state)=>{
            state.token=''
            state.menuList=[]


        },

        changeRouteStatus(state,hasRoute){
            state.hasRoute=hasRoute
        }
    },
    actions: {},
    modules: {

    }
})
