import { createStore } from "vuex";
import router from "@/router";

export default createStore({
  state: {
    token: localStorage.getItem("token") ? localStorage.getItem("token") : "",
    userId: null,
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
    setUserId(state, userId) {
      state.userId = userId;
    },
    delUserId(state) {
      state.userId = null;
    },
  },
  actions: {
    logout(context) {
      context.commit("delToken");
      context.commit("delUserId");
      router.push({ name: "Login" }).then();
    },
    add(context, value) {
      context.commit("ADD", value);
    },
    reduce(context, value) {
      context.commit("REDUCE", value);
    },
  },
  modules: {},
});
