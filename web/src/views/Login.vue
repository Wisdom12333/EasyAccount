<template>
  <h1>Login</h1>
  <br />
  <el-form  :model="user" label-width="100px" style="width: 50%">
    <el-form-item label="用户名">
      <el-input v-model="user.username" placeholder="请输入用户名" />
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="user.password" type="password" />
    </el-form-item>
    <el-form-item label="记住密码">
      <input v-model="rememberPwd" type="checkbox" />
    </el-form-item>
    <el-form-item>
      <el-space :size="10" spacer="|">
        <el-button type="primary" @click.prevent="login()" style="font-weight: bold">登陆</el-button>
        <el-check-tag checked  @click="router.push({ name: 'Register' })">没有账号？点击注册</el-check-tag>
      </el-space>
    </el-form-item>
  </el-form>
</template>

<script setup>
import axios from "axios";
import {useStore} from "vuex";
import {onMounted, reactive, ref} from "vue";
import {useRouter} from "vue-router";
import errorNotification from "@/hooks/errorNotification";

const store = useStore();
const router = useRouter();

const user = reactive({
  username: "shirj",
  password: "",
});
const rememberPwd = ref(false);

function login() {
  axios.post("/user/login", user).then(
    (response) => {
      if (response.data.code === "0") {
        store.commit("setUserId", response.data.result.userId);
        if(rememberPwd.value){
          localStorage.setItem("pwd", user.password);
        } else {
          localStorage.removeItem("pwd");
        }
        router.push({ name: "Home" });
      }
    },
    (error) => {
      errorNotification(error.response.data.message);
    },
  );
}

onMounted(()=>{
  //之前存储了密码
  if(localStorage.getItem("pwd")){
    rememberPwd.value = true;
    user.password = localStorage.getItem("pwd");
  } else rememberPwd.value = false;
})
</script>

<style scoped></style>
