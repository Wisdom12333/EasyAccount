import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";
import axios from "axios";
import useElMessage from "@/hooks/useElMessage";

//配置基础URL
axios.defaults.baseURL = "http://localhost:8181";
const tokenType = "Bearer ";
// 添加请求拦截器
axios.interceptors.request.use(
  (config) => {
    if (sessionStorage.getItem("token")) {
      config.headers.Authorization = tokenType + sessionStorage.getItem("token");
    } else if (store.state.token) {
      config.headers.Authorization = tokenType + store.state.token;
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
    if (response.headers.authorization) {
      //后台返回了刷新的token,替换原有token
      store.state.token = response.headers.authorization;
      sessionStorage.setItem("token", response.headers.authorization);
    }
    return response;
  },
  (error) => {
    if (error.response.status === 401) {
      useElMessage("登录失效,请重新登录", "登录超时", () => {
        store.dispatch("logout").then();
      });
    }
    return Promise.reject(error);
  }
);
createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
