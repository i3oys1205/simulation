import Vue from 'vue'
import Vuex from 'vuex'


Vue.use(Vuex)


const store = new Vuex.Store({
  state: {
    active : ''
  },
  mutations: {
    inActive (state,active) {
      state.active = active;
    }
  },
  actions:{
    setActive(state,active){
      store.commit('inActive',active);
    }
  }
})

export default store;
