<template>
  <el-card style="width: 33%; margin: 10% auto auto">
    <template #header>
      <div>Login</div>
    </template>
    <el-form
      :model="userLogin"
      label-width="100px"
      style="width: 60%; margin: auto auto"
    >
      <el-form-item label="用户名">
        <el-input v-model="userLogin.username" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="userLogin.password" type="password" />
      </el-form-item>
      <el-form-item label="记住密码">
        <input v-model="rememberPwd" type="checkbox" />
      </el-form-item>
      <el-form-item>
        <el-space :size="10" spacer="|">
          <el-button
            type="primary"
            @click.prevent="login()"
            style="font-weight: bold"
            >登陆</el-button
          >
          <el-check-tag checked @click="router.push({ name: 'Register' })"
            >没有账号？点击注册</el-check-tag
          >
        </el-space>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { useStore } from "vuex";
import { onMounted, reactive, ref } from "vue";
import { useRouter } from "vue-router";
import errorNotification from "@/hooks/errorNotification";
import { user } from "@/static/entity";

const store = useStore();
const router = useRouter();

const userLogin = reactive<user>(new user());
const rememberPwd = ref<boolean>(false);

function login() {
  axios.post("/user/login", userLogin).then(
    (response) => {
      if (response.data.code === "0") {
        store.commit("setUserId", response.data.result.userId);
        if (rememberPwd.value) {
          localStorage.setItem("pwd", userLogin.password as string);
        } else {
          localStorage.removeItem("pwd");
        }
        router.push({ name: "Home" });
      }
    },
    (error) => {
      errorNotification(error.response.data.message);
    }
  );
}

onMounted(() => {
  userLogin.username = "shirj";
  //之前存储了密码
  if (localStorage.getItem("pwd")) {
    rememberPwd.value = true;
    userLogin.password = localStorage.getItem("pwd") as string;
  } else rememberPwd.value = false;
});
</script>

<style scoped></style>
