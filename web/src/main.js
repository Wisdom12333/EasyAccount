import {createApp} from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";
import axios from "axios";
import {ElMessageBox} from "element-plus";

//配置基础URL
axios.defaults.baseURL = "http://localhost:8181";
const tokenType = "Bearer ";
// 添加请求拦截器
axios.interceptors.request.use(
  (config) => {
    if (localStorage.getItem("token")) {
        config.headers.Authorization = tokenType + localStorage.getItem("token");
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
      if (response.headers.Authorization) {
          //后台返回了刷新的token,替换原有token
          store.state.token = response.headers.Authorization;
          localStorage.setItem("token", response.headers.Authorization);
      }
    return response;
  },
  (error) => {
    if (error.response.status === 401) {
      ElMessageBox.alert("登录失效,请重新登录", "登录超时", {
        confirmButtonText: "确定",
        callback: () => {
          router.push({ name: "Login" }).then();
        },
      }).then();
    }
    return Promise.reject(error);
  }
);
createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
