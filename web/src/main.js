import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";
import axios from 'axios';

const app = createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
app.config.globalProperties.$http=axios;