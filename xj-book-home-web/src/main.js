// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import iView from 'iview'
import 'iview/dist/styles/iview.css'
import {store} from './store/store.js'
import vue_resource from 'vue-resource'
import axios from 'axios'

Vue.prototype.$ajax = axios


Vue.config.productionTip = false

Vue.use(vue_resource);
Vue.use(iView);

/*Vue.http.interceptors.push((request, next) => {
  const url='https://www.taobao.com';
  if(request.url.startsWith(url)){
    request.headers.map['origin']='www.taobao.com';
    request.headers.map['referer']='www.taobao.com';
  }
  next();
})*/

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  components: { App },
  template: '<App/>',
})
