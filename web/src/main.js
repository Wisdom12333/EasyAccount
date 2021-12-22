import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";
import axios from "axios";

axios.interceptors.request.use(
  (config) => {
    if (localStorage.getItem("token")) {
      config.headers.token = localStorage.getItem("token");
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
