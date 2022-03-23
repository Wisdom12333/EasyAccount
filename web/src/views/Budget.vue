<template>
  <div>Budget</div>
</template>

<script setup lang="ts">
import {ElMessage, ElMessageBox} from "element-plus";
import axios from "axios";
import {useStore} from "vuex";
import {user} from "@/static/entity";

const store = useStore();
//新的用户信息
const userNew = new user();
const upInfo = [
  {
    message: "请输入新的邮箱",
    verify:
        /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    errorMessage: "非法的邮箱！",
  },
  {
    message: "请输入新密码",
    verify:
        /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/,
    errorMessage: "密码不合法！",
  },
];
//更新用户
const Update = (type: number): void => {
  ElMessageBox.prompt(upInfo[type].message, "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: upInfo[type].verify,
    inputErrorMessage: upInfo[type].errorMessage,
  }).then(({ value }) => {
    if (type === 0) {
      userNew.eMail = value;
      userNew.password = "";
    } else if (type === 1) {
      userNew.password = value;
      userNew.eMail = "";
    }
    userNew.userId = store.state.userId;
    axios.post("/user/update", userNew).then(
        () => {
          ElMessage({
            type: "success",
            message: "修改成功!",
          });
        },
        (error) => {
          console.log(error.response);
        }
    );
  });
};
//注销用户
const soldOut = () => {
  ElMessageBox.confirm("这个操作将注销账号，仍要继续吗？", "警告！", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning",
  }).then(() => {
    axios.get(`/user/soldOut?userId=${store.state.userId}`).then(() => {
      ElMessage({
        type: "success",
        message: "注销成功",
      });
      store.dispatch("logout");
    }, null);
  });
};
</script>

<style scoped></style>
