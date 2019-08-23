// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import {store} from './store/store.js'
import vue_resource from 'vue-resource'


Vue.config.productionTip = false

Vue.use(vue_resource);
Vue.use(iView);

new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>',
})
