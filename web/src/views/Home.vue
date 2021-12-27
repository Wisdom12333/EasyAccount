<template>
  <div>Home</div>
  <el-button @click="click">click</el-button>
  <el-button @click="del">delToken</el-button>
</template>

<script>
import axios from "axios";
import {mapMutations} from "vuex";

export default {
  name: "Home",
  methods:{
    click(){
      axios.get("/user/queryByUserId?userId=1").then(()=>{},(error)=>{
        // console.log(error);
      });
    },
    del(){
      this.delToken();
    },
    ...mapMutations(["delToken"]),
  }
};
</script>

<style scoped></style>
