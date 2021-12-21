<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: "App",
};
</script>

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
</style>
