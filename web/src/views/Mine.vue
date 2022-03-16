<template>
  <el-space :fill="true" style="width: 100%">
    <div style="height: 50px"></div>
    <el-card>
      <template #header>
        <span style="font-size: 18px; font-weight: bold">设置</span>
      </template>
      <div>
        <el-space direction="vertical" :size="10">
          <el-link @click.prevent="Update(0)"
            ><span class="link">修改邮箱</span></el-link
          >
          <el-link @click.prevent="Update(1)"
            ><span class="link">修改密码</span></el-link
          >
        </el-space>
      </div>
    </el-card>
    <el-card>
      <template #header>
        <span style="font-size: 18px; font-weight: bold">其他</span>
      </template>
      <div>
        <el-space direction="vertical" :size="10">
          <el-link><span class="link">反馈意见</span></el-link>
          <el-link type="danger" @click.prevent="soldOut"
            ><span class="link">注销账号</span></el-link
          >
        </el-space>
      </div>
    </el-card>
  </el-space>
</template>

<script setup>
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";
import {useStore} from "vuex";

const store = useStore();
//新的用户信息
const user = {
  userId: store.state.userId,
  password: "",
  eMail: "",
};
const upInfo = [
  {
    message: "请输入新的邮箱",
    verify:
      /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    errorMessage: "非法的邮箱！",
  },
  {
    message: "请输入新密码",
    verify: null,
    errorMessage: "密码不合法！",
  },
];
//更新用户
const Update = (type) => {
  ElMessageBox.prompt(upInfo[type].message, "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: upInfo[type].verify,
    inputErrorMessage: upInfo[type].errorMessage,
  })
    .then(({ value }) => {
      if (type === 0) {
        user.eMail = value;
        user.password = "";
      } else if (type === 1) {
        user.password = value;
        user.eMail = "";
      }
      axios.post("/user/update", user).then(
        () => {},
        (error) => {
          console.log(error.response);
        }
      );
      ElMessage({
        type: "success",
        message: "修改成功!",
      });
    })
    .catch(()=>{});
};
//注销用户
const soldOut = () => {
  ElMessageBox.confirm("这个操作将注销账号，仍要继续吗？", "警告！", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      axios.get(`/user/soldOut?userId=${store.state.userId}`).then(
        () => {
          ElMessage({
            type: "success",
            message: "注销成功",
          });
          store.dispatch("logout");
        },
        () => {}
      );
    })
    .catch(()=>{});
};
</script>

<style scoped>
.link {
  font-size: 16px;
}
.el-card {
  width: 80%;
}
</style>
