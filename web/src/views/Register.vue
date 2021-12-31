<template>
  <h1>Register</h1>
  <br />
  <form>
    <label> 用户名<input v-model="user.username" placeholder="input" /> </label>
    <label> 密码<input v-model="user.password" type="password" /> </label>
    <label> 确认密码<input v-model="user.checkPwd" type="password" /> </label>
    <label> 邮箱<input v-model="user.eMail" /> </label>
    <label>
      <button @click.prevent="submit()">提交</button>
    </label>
    <label>
      <router-link to="/Login">已有账号？现在登陆</router-link>
    </label>
  </form>
</template>

<script setup>
import axios from "axios";
import { useRouter } from "vue-router";
import { reactive, watch } from "vue";

const router = useRouter();

const user = reactive({
  username: undefined,
  password: undefined,
  checkPwd: undefined,
  eMail: undefined,
});

function submit() {
  axios.post("/user/register", user).then(
    () => {
      console.log("注册成功");
      router.push({ name: "Login" });
    },
    (error) => {
      console.log(error.response.data);
    }
  );
}

watch(
  () => user.username,
  (val) => {
    axios.get("/user/checkUsername?username=" + val).then(
      () => console.log("可用"),
      () => console.log("用户名已被使用")
    );
  }
);
</script>

<style scoped></style>
