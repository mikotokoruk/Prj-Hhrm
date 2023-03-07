import axios from "axios";
import router from "../router";
import Element from "element-ui"

axios.defaults.baseURL = "http://localhost:8888"

const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    }
})

request.interceptors.request.use(config => {

    config.headers['token'] = localStorage.getItem("token")


    return config
})

request.interceptors.response.use(
    response => {


        let res = response.data

        if (res.code === 200) {
            Element.Loading.service().close()
            return response.data

        } else {
            Element.Message.error(!res.msg ? '系统异常' : res.msg)
            Element.Loading.service().close()
            return Promise.reject(response.data.msg)
        }
    },
    error => {

        if (error.response.status === 401) {
            router.push("/login")
            return false
        }
        if (error.response.data) {
            error.massage = error.response.data.msg
        }


        Element.Message.error(error.massage, {duration: 3000})
        Element.Loading.service().close()
        return Promise.reject(error)
    }
)

export default request
