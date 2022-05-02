<template>
  <el-card style="width: 25vw; margin: 10% auto auto">
    <template #header>
      <div>Register</div>
    </template>
    <el-form
      :model="userReg"
      label-width="100px"
      style="width: 80%; margin: auto auto"
      :rules="rules"
      ref="ruleFormRef"
    >
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="userReg.username"
          placeholder="请输入用户名"
          @change="checkUsername"
        >
          <template #suffix
            ><el-tag
              type="danger"
              v-show="usernameCheck === false"
              size="small"
              style="position: relative; top: 5px"
              >用户名已被使用!</el-tag
            ></template
          >
        </el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="userReg.password" type="password" show-password />
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPwd">
        <el-input v-model="checkPwd" type="password" show-password />
      </el-form-item>
      <el-form-item label="邮箱" prop="eMail">
        <el-input v-model="userReg.eMail" />
      </el-form-item>
      <el-form-item>
        <el-space :size="10" spacer="|">
          <el-button
            type="primary"
            auto-insert-space
            @click.prevent="submit(ruleFormRef)"
            style="font-weight: bold"
            >提交</el-button
          >
          <el-check-tag checked @click="router.push({ name: 'Login' })"
            >已有账号？现在登陆!</el-check-tag
          >
        </el-space>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script setup lang="ts">
import { ElMessage, ElNotification } from "element-plus";
import type { FormInstance, FormRules } from "element-plus";
import { reactive, ref } from "vue";
import { useRouter } from "vue-router";
import axios from "axios";
import { User } from "@/static/entity";
import errorNotification from "@/hooks/errorNotification";
import { isBlank } from "@/hooks/StringUtils";

const router = useRouter();

let usernameCheck = ref<boolean>(true);
const checkPwd = ref<string>();
const ruleFormRef = ref<FormInstance>();
const userReg = reactive<User>(new User());
const rules = reactive<FormRules>({
  username: [
    { required: true, message: "请输入用户名", trigger: "change" },
    { min: 4, max: 16, message: "用户名长度必须为4-16", trigger: "change" },
  ],
  password: [
    {
      required: true,
      message: "请输入密码",
      trigger: "change",
    },
  ],
  checkPwd: [
    {
      required: true,
      message: "请输入密码",
      trigger: "change",
    },
  ],
  eMail: [
    {
      required: true,
      message: "请输入邮箱",
      trigger: "change",
    },
  ],
});

function submit(formEl: FormInstance | undefined) {
  if (!formEl) return;
  //提交前校验
  formEl.validate((valid) => {
    if (valid) {
      let check = true,
        message;
      if (!userReg.username || isBlank(userReg.username)) {
        check = false;
        message = "用户名不能为空!";
      } else if (!usernameCheck.value) {
        check = false;
        message = "用户名已被使用!";
      } else if (!userReg.password || isBlank(userReg.password)) {
        check = false;
        message = "密码不能为空!";
      } else if (userReg.password != checkPwd.value) {
        check = false;
        message = "请确认密码!";
      }
      if (!check) {
        ElMessage({
          type: "error",
          message: message,
        });
        return false;
      }
      //提交
      axios.post("/user/register", userReg).then(
        () => {
          ElNotification({
            type: "success",
            message: "注册成功！",
          });
          router.push({ name: "Login" });
        },
        (error) => {
          errorNotification(error.response.data.message);
        }
      );
    }
  });
}
function checkUsername(): void {
  axios.get(`/user/checkUsername?username=${userReg.username}`).then(
    () => (usernameCheck.value = true),
    () => (usernameCheck.value = false)
  );
}
</script>

<style scoped></style>
