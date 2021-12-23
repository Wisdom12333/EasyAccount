<template>
  <el-container class="el-container">
    <el-header height="30px" style="background: #938989">
      <div>
        <router-link to="/Login" @click="logout()">退出登录</router-link>
      </div>
    </el-header>
    <el-container>
      <el-aside width="300px" style="background: #d7c3c3">
        <router-link to="/Index/Budget">Budget</router-link>
        <br />
        <router-link to="/Index/Home">Home</router-link>
        <br />
        <router-link to="/Index/Mine">Mine</router-link>
        <br />
        <router-link to="/Index/Statistics">Statistics</router-link>
      </el-aside>
      <el-main style="background: #99f8ff">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import {mapMutations} from "vuex"
export default {
  name: "Index",
  methods: {
    logout(){
      this.delToken();
    },
    ...mapMutations(['delToken']),
  }
};
</script>

<style scoped></style>
