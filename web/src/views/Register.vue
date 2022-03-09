<template>
  <h1>Register</h1>
  <br />
  <el-form :model="user" label-width="100px" style="width: 50%">
    <el-form-item label="用户名">
      <el-input v-model="user.username" placeholder="请输入用户名" />
      <el-tag v-show="usernameCheck===false" type="danger">用户名已被使用!</el-tag>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="user.password" type="password" />
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input v-model="user.checkPwd" type="password" />
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="user.eMail" />
    </el-form-item>
    <el-form-item>
      <el-space :size="10" spacer="|">
        <el-button type="primary" @click.prevent="submit()">提交</el-button>
        <el-check-tag checked  @click="router.push({ name: 'Login' })">已有账号？现在登陆!</el-check-tag>
      </el-space>
    </el-form-item>
  </el-form>
</template>

<script setup>
import axios from "axios";
import {useRouter} from "vue-router";
import {reactive, ref, watch} from "vue";

const router = useRouter();
let usernameCheck = ref(true);
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
      () => usernameCheck.value = true,
      () => usernameCheck.value = false,
    );
  }
);
</script>

<style scoped></style>
