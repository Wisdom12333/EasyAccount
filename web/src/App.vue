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

::-webkit-scrollbar {
  width: 8px;
  height: 8px;
}

::-webkit-scrollbar-thumb {
  background-color: #eaecf1;
  border-radius: 3px;
}

.el-table__body-wrapper::-webkit-scrollbar {
  width: 6px;
  height: 10px;
}
.el-table__body-wrapper::-webkit-scrollbar-thumb {
  background-color: #d1d8e1;
  border-radius: 3px;
}
</style>
