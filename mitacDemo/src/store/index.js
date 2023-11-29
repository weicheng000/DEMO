import Vuex from 'vuex';

export default new Vuex.Store({
  state: {
    token: localStorage.getItem('token') ? localStorage.getItem('token') : null
  },
  mutations: {
    setToken(state, token){
      localStorage.setItem('token', token);
      state.token = token;
    },
    logOut(state){
      localStorage.removeItem('token');
      state.token = null;
    }
  },
  getters: {
    getToken(state){
      return state.token;
    }
  }
});
