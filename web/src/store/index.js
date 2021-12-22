import { createStore } from "vuex";

export default createStore({
  state: {
    token: localStorage.getItem("token") ? localStorage.getItem("token") : "",
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
      localStorage.setItem("token", token.token);
    },
    delToken(state) {
      state.token = "";
      localStorage.removeItem("token");
    },
  },
  actions: {
    add(context, value) {
      context.commit("ADD", value);
    },
    reduce(context, value) {
      context.commit("REDUCE", value);
    },
  },
  modules: {},
});
