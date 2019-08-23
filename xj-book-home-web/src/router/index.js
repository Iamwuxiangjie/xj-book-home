import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/base/Login.vue'
import Index from '../components/base/Index.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '*',
      redirect: '/'
    },
  ]
})
