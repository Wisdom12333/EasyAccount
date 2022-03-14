<template>
  <router-view />
</template>

<script setup></script>

<style>
/*使布局容器占据整个页面*/
html,
body,
.el-container,
#app {
  padding: 0;
  margin: 0;
  height: 100%;
}
.body {
  font-family: "PingFang SC", "Helvetica Neue", sans-serif;
}
</style>
