<template>
  <el-container class="el-container">
    <el-header height="30px" style="background: #938989">Hi</el-header>
    <el-container>
      <el-aside width="300px" style="background: #d7c3c3">Aside</el-aside>
      <el-main style="background: #99f8ff">
        <el-button type="primary" @click="click">click</el-button>
        <el-button type="warning" @click="click2">click2</el-button>
        <!--        <router-view />-->
        <div style="background-color: azure">
          <Register />
          <Login />
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from "axios";
import Login from "@/views/Login";
import Register from "@/views/Register";

export default {
  name: "App",
  methods: {
    click() {
      axios.get("http://localhost:8080/user/queryByUserId?userId=1").then(
        (response) => {
          console.log(response.data);
        },
        (error) => {
          console.log(error.message);
        }
      );
    },
    click2() {
      console.log(this.$store);
    },
  },
  components: { Login, Register },
};
</script>

<style>
/*使布局容器占据整个页面*/
html,
body,
.el-container {
  padding: 0;
  margin: 0;
  height: 100%;
}
</style>
