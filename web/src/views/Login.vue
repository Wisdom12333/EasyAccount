<template>
  <h1>Login</h1>
  <br />
  <form>
    <label>
      用户名：<input placeholder="请输入用户名" v-model="user.username" />
    </label>
    <label> 密码： <input type="password" v-model="user.password" /> </label>
    <label> 记住密码<input type="checkbox" /></label>
    <button @click.prevent="login()">登陆</button>
    <router-link to="/Register">没有账号？点击注册</router-link>
  </form>
</template>

<script>
import axios from "axios";
import { mapMutations } from "vuex";
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "shirj",
        password: "111",
      },
    };
  },
  methods: {
    login() {
      axios.post("/user/login", this.user).then(
        (response) => {
          if (response.data.code === "0") {
            this.setToken({ token: response.data.token });
            console.log(response.data.token);
            this.$router.push({ name: "Home" });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
    ...mapMutations(["setToken"]),
  },
};
</script>

<style scoped></style>
