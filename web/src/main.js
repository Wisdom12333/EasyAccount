import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";
import axios from "axios";

//配置基础URL
axios.defaults.baseURL = "http://localhost:8181";
// 添加请求拦截器
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

// 添加响应拦截器
axios.interceptors.response.use(
  (response) => {
    if (response.headers) {
      // console.log(response.headers);
    }
    return response;
  },
  (error) => {
    return Promise.reject(error);
  }
);
createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
