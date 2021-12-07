<template>
  <div>Login</div>
  <el-select v-model.number="n">
    <el-option :value="1">1</el-option>
    <el-option :value="2">2</el-option>
    <el-option :value="3">3</el-option>
  </el-select>
  <el-button type="primary" @click="add">点我加</el-button>
  <el-button type="primary" @click="reduce">点我减</el-button>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      n: 1,
    };
  },
  methods: {
    add() {
      this.$store.dispatch("add", this.n);
    },
    reduce() {
      this.$store.dispatch("reduce", this.n);
    },
  },
};
</script>

<style scoped></style>
