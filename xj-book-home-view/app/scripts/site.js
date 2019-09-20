/**
 * @authors king
 * @date    2017-04-02 17:40:17
 */
import Vue from "vue";
import VueRouter from "vue-router";
import VueResource from "vue-resource";
import iView from "iview";
import 'nvd3';
import 'nvd3/build/nv.d3.css'
import routes from "./site/router.js";
import store from "./site/store.js";
import {APPLICATION_NAME} from "./variables.js";

import App from "./site/App.vue";

import "iview/dist/styles/iview.css";


Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(iView);

Vue.prototype.$Spin.loading = () => {
  Vue.prototype.$Spin.show({
    render: (h) => {
      return h('div', [
        h('Icon', {
          'class': 'demo-spin-icon-load',
          props: {
            type: 'ios-loading',
            size: 18
          }
        }),
        h('div', 'Loading')
      ])
    }
  });
};



const router = new VueRouter({mode: 'history', routes});

router.beforeEach((to, from, next) => {
    if (to.meta.title) {
      document.title = `${APPLICATION_NAME}-${to.meta.title}`
    }
    iView.LoadingBar.start();
    next();
});

router.afterEach((to, from, next) => {
  iView.LoadingBar.finish();
});

new Vue({
  el: "#app",
  store,
  render: h => h(App),
  router,
})
