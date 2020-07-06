import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: localStorage.getItem("token"),
    userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
  },
  mutations: {
    //存储token
    SET_TOKEN:(state,token)=>{
      state.token = token
      localStorage.setItem("token",token)
    },
    //存储用户信息
    SET_USERINFO:(state,userInfo)=>{
      state.userInfo = userInfo
      sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    //删除token和用户信息
    REMOVE_INFO:(state)=>{
      state.token = '';
      state.userInfo = {};
      localStorage.setItem("token",'')
      sessionStorage.setItem("userInfo",JSON.stringify(''))
    }
  },
  getters: {
    getUserInfo:state => {
      return state.userInfo
    },
    getToken:state => {
      return state.token
    }
  },
  actions: {
  },
  modules: {
  }
})
