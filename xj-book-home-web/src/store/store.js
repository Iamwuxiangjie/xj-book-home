import Vue from 'vue'
import Vuex from 'vuex'
import {TestModule} from './test.js'

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    test: TestModule
  }
});

