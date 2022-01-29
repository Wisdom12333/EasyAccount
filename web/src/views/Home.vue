<template>
  <div>Home</div>
  <h1>{{ userInfo }}</h1>
  <el-button @click="click()">clicmk</el-button>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";
import { onBeforeMount } from "vue";
import useElMessage from "@/hooks/useElMessage";

const store = useStore();
const getUserInfo = async () => {
  axios.get("/user/userInfo?userId=" + store.state.userId).then(
    (response) => {
      userInfo = response.data.result.userInfo;
    },
    (error) => {
      if (error.response.status === 406) {
        useElMessage("服务异常，请重新登陆！", "服务异常", () => {
          store.dispatch("logout");
        });
      }
    }
  );
};
onBeforeMount(getUserInfo);

let userInfo;
const click = () => {
  axios.get("/user/userInfo?userId=").then(
    (response) => {
      userInfo = response.data.result.userInfo;
    },
    (error) => {
      if (error.response.status === 406) {
        useElMessage("服务异常，请重新登陆！", "服务异常", () => {
          store.dispatch("logout");
        });
      }
    }
  );
};
</script>

<style scoped></style>
