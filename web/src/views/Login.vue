<template>
  <h1>Login</h1>
  <br />
  <form>
    <label>
      用户名：<input placeholder="请输入用户名" v-model="user.username" />
    </label>
    <label> 密码： <input type="password" v-model="user.password" /> </label>
    <button @click.prevent="login()">登陆</button>
    <router-link to="/Register">没有账号？点击注册</router-link>
  </form>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      console.log(this.user);
    },
  },
};
</script>

<style scoped></style>
