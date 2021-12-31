<template>
  <div>Home</div>
  <el-button @click="click">click</el-button>
  <el-button @click="store.commit('delToken')">delToken</el-button>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";

const store = useStore();

function click() {
  axios.get("/user/queryByUserId?userId=1").then(null, (error) => {
    console.log(error);
  });
}
</script>

<style scoped></style>
