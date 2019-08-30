import {initSelf} from '../api/user.js'

export const UserModule = {
  state: {
    user:{},
    roles:[],
  },
  getters: {
    getUser(state){
      return state.user;
    },
    getRoles(state){
      return state.roles;
    }
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setRoles(state, roles) {
      state.roles = roles;
    }
  },
  actions: {
    async initSelf({state,commit}){
      const result = await initSelf();
      if(result.code===200){
        const {user,roles}=result.body;
        commit('setUser', user);
        commit('setRoles', roles);
      }
      return new Promise((resolve,reject)=>{
        resolve(result);
      });
    },
  },
};
