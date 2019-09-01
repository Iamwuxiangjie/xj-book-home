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

Vue.http.interceptors.push((request, next) => {
  iView.LoadingBar.start();
  next((response) => {
    if (!response.ok) {
      iView.LoadingBar.error();
    } else {
      iView.LoadingBar.finish();
    }
    return response;
  });
});

router.beforeEach((to, from, next) => {
  iView.LoadingBar.start();
  next();
});

router.afterEach(route => {
  iView.LoadingBar.finish();
});

Promise.all([
  store.dispatch('initSelf')
]).then(([res1])=>{
  new Vue({
    el: '#app',
    store,
    router,
    components: { App },
    template: '<App/>',
  })
})


