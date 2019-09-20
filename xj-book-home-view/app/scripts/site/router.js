/**
 * web router
 * @authors king
 * @date    2017-04-02 17:26:02
 */


import Home from './start/Home.vue'





const routes = [{
  path: '/',
  component: Home,
  meta: {title: '主页'}
},
  {path: '*', redirect: '/'}
];

export default routes;