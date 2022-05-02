<template>
  <el-container class="el-container" style="overflow: hidden">
    <el-header
      height="50px"
      style="background: #fefefe; border-bottom: solid #dcdfe6 2px"
    >
      <el-row :gutter="10" style="height: 100%">
        <el-col :span="16"
          ><el-image
            style="width: 120px; height: 100%"
            :src="require('@/assets/logo.png')"
            fit="contain"
          ></el-image
        ></el-col>
        <el-col :span="8"
          ><el-button
            type="text"
            @click="logout()"
            style="float: right; height: 100%"
            >退出登录</el-button
          ></el-col
        >
      </el-row>
    </el-header>

    <el-container>
      <el-menu
        active-text-color="#ffd04b"
        background-color="#545c64"
        class="menu"
        default-active="1"
        text-color="#fff"
        :router="true"
        style="font-weight: bold"
      >
        <h1 style="font-size: 20px; padding-left: 20px; color: white">
          EasyAccount
        </h1>
        <el-menu-item index="1" route="/Index/Home">首页</el-menu-item>
        <el-menu-item index="2" route="/Index/Statistics">统计</el-menu-item>
        <el-menu-item index="3" route="/Index/Budget">预算</el-menu-item>
        <el-menu-item index="4" route="/Index/Mine">我的</el-menu-item>
      </el-menu>

      <el-main style="background: #ecf5ff; padding-right: 0; padding-top: 0">
        <el-scrollbar>
          <router-view />
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { onUnmounted } from "vue";

const store = useStore();
const router = useRouter();

function logout() {
  router.push({ name: "Login" }).then();
}

onUnmounted(() => store.dispatch("logout"));
</script>

<style scoped>
.menu {
  width: 200px;
  min-height: 400px;
}
.el-menu-item {
  font-size: 18px;
}
</style>
