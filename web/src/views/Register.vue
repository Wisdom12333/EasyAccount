<template>
  <h1>Register</h1>
  <br />
  <form>
    <label>
      用户名<input placeholder="input" v-model="user.username" />
    </label>
    <label>
      密码<input type="password" v-model="user.password" />
    </label>
    <label>
      确认密码<input type="password" v-model="user.checkPwd" />
    </label>
    <label>
      邮箱<input v-model="user.eMail" />
    </label>
    <label>
      <button @click.prevent="submit()">提交</button>
    </label>
  </form>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        checkPwd: "",
        eMail: ""
      }
    };
  },
  methods:{
    submit(){
      console.log(this.user);
      this.axios.get("localhost:8080/user").then((response) => {
        console.log(response.data)
      })
    },
  }
};
</script>

<style scoped></style>
