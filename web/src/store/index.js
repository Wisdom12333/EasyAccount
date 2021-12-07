import { createStore } from "vuex";

export default createStore({
  state: {
    sum: 0,
  },
  mutations: {
    ADD(state, value) {
      state.sum += value;
    },
    REDUCE(state, value) {
      state.sum -= value;
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
