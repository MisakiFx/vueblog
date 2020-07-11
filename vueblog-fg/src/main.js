import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import axios from 'axios'
import "./axios.js"
import "./permission"
import Vue from 'vue'
import mavonEditor from 'mavon-editor'

import 'mavon-editor/dist/css/index.css'
import "element-ui/lib/theme-chalk/index.css"
import VueClipboards from 'vue-clipboard2'

Vue.use(Element)
Vue.use(mavonEditor)
Vue.use(VueClipboards);

Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
