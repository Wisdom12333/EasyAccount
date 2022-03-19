<template>
  <h1>Register</h1>
  <br />
  <el-form :model="user" label-width="100px" style="width: 50%">
    <el-form-item label="用户名">
      <el-input
        v-model="userReg.username"
        placeholder="请输入用户名"
        @change="checkUsername"
      >
        <template #suffix
          ><el-tag type="danger" v-show="usernameCheck === false"
            >用户名已被使用!</el-tag
          ></template
        >
      </el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="userReg.password" type="password" show-password />
    </el-form-item>
    <el-form-item label="确认密码">
      <el-input v-model="checkPwd" type="password" show-password />
    </el-form-item>
    <el-form-item label="邮箱">
      <el-input v-model="userReg.eMail" />
    </el-form-item>
    <el-form-item>
      <el-space :size="10" spacer="|">
        <el-button
          type="primary"
          auto-insert-space
          @click.prevent="submit()"
          style="font-weight: bold"
          >提交</el-button
        >
        <el-check-tag checked @click="router.push({ name: 'Login' })"
          >已有账号？现在登陆!</el-check-tag
        >
      </el-space>
    </el-form-item>
  </el-form>
</template>

<script setup lang="ts">
import { ElNotification } from "element-plus";
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { user } from "@/static/entity";

const router = useRouter();

let usernameCheck = ref<boolean>(true);
const checkPwd = ref<string>();
const userReg = reactive<user>(new user());

function submit() {
  //提交前校验
  let check = true,
    message;
  if (!usernameCheck.value) {
    check = false;
    message = "用户名已被使用!";
  }
  if (!check) {
    ElNotification({
      type: "error",
      message: message,
    });
    return false;
  }
  //提交
  axios.post("/user/register", userReg).then(
    () => {
      ElNotification({
        type: "success",
        message: "注册成功！",
      });
      router.push({ name: "Login" });
    },
    (error) => {
      console.log(error.response.data);
    }
  );
}
function checkUsername(): void {
  axios.get(`/user/checkUsername?username=${userReg.username}`).then(
    () => (usernameCheck.value = true),
    () => (usernameCheck.value = false)
  );
}
</script>

<style scoped></style>
