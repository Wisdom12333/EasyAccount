<template>
  <el-container class="el-container">
    <el-header height="30px" style="background: #938989">
      <div>
        <router-link to="/Login" @click="logout()">退出登录</router-link>
      </div>
    </el-header>
    <el-container>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="menu"
        default-active="1"
        text-color="#fff"
        :router="true"
      >
        <el-menu-item index="1" route="/Index/Home">首页</el-menu-item>
        <el-menu-item index="2" route="/Index/Statistics">统计</el-menu-item>
        <el-menu-item index="3" route="/Index/Budget">预算</el-menu-item>
        <el-menu-item index="4" route="/Index/Mine">我的</el-menu-item>
      </el-menu>
      <el-main style="background: #99f8ff">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import {useStore} from "vuex";
import {onUnmounted} from "vue";
import {useRouter} from "vue-router";

const store = useStore();
const router = useRouter();

function logout() {
  console.log("logout");
  router.push({ name: "Login" }).then();
}

onUnmounted(() => store.dispatch("logout"));
</script>

<style scoped>
.menu {
  width: 200px;
  min-height: 400px;
}
</style>
