<template>
  <el-container class="el-container">
    <el-header height="30px" style="background: #938989">
      <div>
        <router-link to="/Login" @click="logout()">退出登录</router-link>
      </div>
    </el-header>
    <el-container>
      <el-aside width="300px" style="background: #d7c3c3">
        <router-link to="/Index/Home" replace>首页</router-link>
        <br />
        <router-link to="/Index/Statistics" replace>统计</router-link>
        <br />
        <router-link to="/Index/Budget" replace>预算</router-link>
        <br />
        <router-link to="/Index/Mine" replace>我的</router-link>
      </el-aside>
      <el-main style="background: #99f8ff">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { useStore } from "vuex";
import { onUnmounted } from "vue";

const store = useStore();

function logout() {
  console.log("logout");
  store.dispatch("logout");
}

onUnmounted(() => logout());
</script>

<style scoped></style>
