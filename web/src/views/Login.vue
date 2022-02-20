<template>
  <h1>Login</h1>
  <br />
  <form>
    <label>
      用户名：<input v-model="user.username" placeholder="请输入用户名" />
    </label>
    <label> 密码： <input v-model="user.password" type="password" /> </label>
    <label> 记住密码<input type="checkbox" /></label>
    <button @click.prevent="login()">登陆</button>
    <router-link to="/Register">没有账号？点击注册</router-link>
    <h1>{{ store.state }}</h1>
  </form>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";
import { reactive } from "vue";
import { useRouter } from "vue-router";

const store = useStore();
const router = useRouter();

const user = reactive({
  username: "shirj",
  password: "123",
});

function login() {
  axios.post("/user/login", user).then(
    (response) => {
      if (response.data.code === "0") {
        store.commit("setUserId", response.data.result.userId);
        router.push({ name: "Home" });
      }
    },
    (error) => {
      console.log(error); //todo 弹出框提示
    }
  );
}
</script>

<style scoped></style>
