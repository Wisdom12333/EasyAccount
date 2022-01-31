import { createStore } from "vuex";
import router from "@/router";

export default createStore({
  state: {
    token: localStorage.getItem("token") ? localStorage.getItem("token") : "",
    userId: localStorage.getItem("userId")
      ? localStorage.getItem("userId")
      : "",
  },
  mutations: {
    // setToken(state, token) {
    //   state.token = token;
    //   localStorage.setItem("token", token.token);
    // },
    delToken(state) {
      state.token = "";
      localStorage.removeItem("token");
    },
    setUserId(state, userId) {
      state.userId = userId;
      localStorage.setItem("userId", userId);
    },
    delUserId(state) {
      state.userId = "";
      localStorage.removeItem("userId");
    },
  },
  actions: {
    logout(context) {
      context.commit("delToken");
      context.commit("delUserId");
      localStorage.clear();
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
