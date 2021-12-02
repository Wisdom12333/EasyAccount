import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import installElementPlus from "./plugins/element";

createApp(App).use(router).use(installElementPlus).mount("#app");
