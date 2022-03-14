import {createRouter, createWebHistory} from "vue-router";
import App from "@/App";
import Budget from "@/views/Budget";
import Mine from "@/views/Mine";
import Statistics from "@/views/Statistics";
import Index from "@/views/Index";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";
import Assets from "@/views/Assets";

const routes = [
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
        path: "budget",
        name: "Budget",
        component: Budget,
      },
      {
        path: "home",
        name: "Home",
        component: Home,
        meta: { keepalive: true },
        children: [
          {
            path: "assets",
            name: "Assets",
            component: Assets,
          },
        ],
      },
      {
        path: "mine",
        name: "Mine",
        component: Mine,
      },
      {
        path: "statistics",
        name: "Statistics",
        component: Statistics,
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

router.beforeEach((to, from, next) => {
  if (allowRoutes.includes(to.path)) {
    next();
  } else {
    let token = sessionStorage.getItem("token");
    if (token === null || token === "") {
      next("/Login");
    } else {
      next();
    }
  }
});
export default router;
