<template>
  <div id="app">
    <el-container class="el-container">
      <el-header height="30px" style="background: #938989">Hi</el-header>
      <el-container>
        <el-aside width="300px" style="background: #d7c3c3">Aside</el-aside>
        <el-main style="background: #99f8ff">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
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
#app,
.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}
</style>
