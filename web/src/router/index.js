import { createRouter, createWebHistory } from "vue-router";
import App from "@/App";
import Budget from "@/views/Budget";
import Mine from "@/views/Mine";
import Statistics from "@/views/Statistics";
import Index from "@/views/Index";
import Home from "@/views/Home";
import Login from "@/views/Login";
import Register from "@/views/Register";

const routes = [
  {
    path: "/",
    name: "App",
    redirect: "/index",
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

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.path === "/Login" || to.path === "/Register") {
    next();
  } else {
    let token = localStorage.getItem("token");
    if (token === null || token === "") {
      next("/Login");
    } else {
      next();
    }
  }
});
export default router;
