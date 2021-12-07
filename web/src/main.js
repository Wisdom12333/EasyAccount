import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import ElementPlus from "./plugins/element";
import store from "./store";

createApp(App).use(store).use(router).use(ElementPlus).mount("#app");
