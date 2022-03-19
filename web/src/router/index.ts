import {
  createRouter,
  createWebHistory,
  NavigationGuardNext,
  RouteLocationNormalized,
  RouteRecordRaw,
} from "vue-router";
import App from "@/App.vue";
import Index from "@/views/Index.vue";
import Home from "@/views/Home.vue";
import Budget from "@/views/Budget.vue";
import Statistics from "@/views/Statistics.vue";
import Mine from "@/views/Mine.vue";
import Login from "@/views/Login.vue";
import Register from "@/views/Register.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "App",
    redirect: "/index/home",
    component: App,
  },
  {
    path: "/index",
    name: "Index",
    component: Index,
    children: [
      {
        path: "home",
        name: "Home",
        component: Home,
        meta: { keepalive: true },
      },
      {
        path: "budget",
        name: "Budget",
        component: Budget,
      },
      {
        path: "statistics",
        name: "Statistics",
        component: Statistics,
      },
      {
        path: "mine",
        name: "Mine",
        component: Mine,
      },
    ],
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
];

//允许直接访问的路径
const allowRoutes = ["/Login", "/login", "/Register", "/register"];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach(
  (
    to: RouteLocationNormalized,
    from: RouteLocationNormalized,
    next: NavigationGuardNext
  ) => {
    if (allowRoutes.includes(to.path)) {
      next();
    } else {
      const token = sessionStorage.getItem("token");
      if (token === null || token === "") {
        next("/Login");
      } else {
        next();
      }
    }
  }
);
export default router;
