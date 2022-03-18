import { createStore } from "vuex";
import router from "@/router";

export default createStore({
  state: {
    token: sessionStorage.getItem("token")
      ? sessionStorage.getItem("token")
      : "",
    userId: sessionStorage.getItem("userId")
      ? sessionStorage.getItem("userId")
      : "",
  },
  mutations: {
    delToken(state) {
      state.token = "";
      sessionStorage.removeItem("token");
    },
    setUserId(state, userId) {
      state.userId = userId;
      sessionStorage.setItem("userId", userId);
    },
    delUserId(state) {
      state.userId = "";
      sessionStorage.removeItem("userId");
    },
  },
  actions: {
    logout(context) {
      context.commit("delToken");
      context.commit("delUserId");
      sessionStorage.clear();
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
