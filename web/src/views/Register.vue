<template>
  <h1>Register</h1>
  <br />
  <form>
    <label> 用户名<input placeholder="input" v-model="user.username" /> </label>
    <label> 密码<input type="password" v-model="user.password" /> </label>
    <label> 确认密码<input type="password" v-model="user.checkPwd" /> </label>
    <label> 邮箱<input v-model="user.eMail" /> </label>
    <label>
      <button @click.prevent="submit()">提交</button>
    </label>
    <label><router-link to="/Login">已有账号？现在登陆</router-link></label>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
  data() {
    return {
      user: {
        username: "",
        password: null,
        checkPwd: null,
        eMail: null,
      },
    };
  },
  methods: {
    submit() {
      console.log(this.user);
      axios.post("/user/register", this.user).then(
        () => {
          console.log("注册成功");
          this.$router.push({ name: "Login" });
        },
        (error) => {
          console.log(error.response.data);
        }
      );
    },
    checkUsername(username) {
      axios.get("/user/checkUsername?username="+username).then(
          ()=>{
            console.log("可用")
          },
          ()=>{
            console.log("用户名已被使用");
          }
      )
    }
  },
  watch:{
    "user.username"(val){
      this.checkUsername(val);
    }
  }
};
</script>

<style scoped></style>
