//全局的拦截器
import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'

axios.defaults.baseURL = "http://localhost:8881"

//前置拦截
axios.interceptors.request.use(config => {
    return config
})

//后置拦截
axios.interceptors.response.use(response => {
        let result = response.data

        console.log("-------------------------")
        console.log(result)
        console.log("-------------------------")

        // if(result){
        //     result.message = response.data.msg
        // }

        if (result.code === 200) {
            // Element.Message.success(result.message)
            return response
        } else {
            Element.Message.error("账户或密码不正确", {duration: 3 * 1000})
            return Promise.reject(response.data.message)
        }
    },

    error => {
        console.log(error)
        if (error.response.data) {
            error.message = error.response.data.message
        }

        if (error.response.status === 401) {
            store.commit("REMOVE_INFO")
            router.push("/login")
        }
        Element.Message.error(error.message, {duration: 3 * 1000})
        return Promise.reject(error)
    }
)