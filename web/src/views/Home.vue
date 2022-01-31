<template>
  <h1 style="font-size: 30px">EasyAccount</h1>
  <hr />
  <p>
    本月支出：¥{{ userInfo.expend === null ? "0.00" : userInfo.expend }}，
    本月收入：¥{{ userInfo.income === null ? "0.00" : userInfo.income }},
    本月结余：¥{{ userInfo.income - userInfo.expend }}
  </p>
  <h1>我的资产<el-switch v-model="isAssets" /></h1>
  <Assets v-if="isAssets"></Assets>
  <div v-if="userInfo.accounts.length !== 0"></div>
  <div v-else>
    <h1>还没有任何账户！</h1>
    <router-link to="">点击新增账户</router-link><br /><br /><br />
    <el-form :model="account" label-width="140px">
      <el-form-item label="账户类型">
        <el-cascader
          :options="accounts"
          :show-all-levels="false"
          v-model="account.type"
        />
      </el-form-item>
      <el-form-item label="账户名称">
        <el-input
          v-model="account.accountName"
          placeholder="请输入账户名称（可不填）"
        />
      </el-form-item>
      <el-form-item label="备注信息">
        <el-input
          v-model="account.remark"
          placeholder="请输入备注信息（可不填）"
        />
      </el-form-item>
      <el-form-item label="账户余额">
        <el-input v-model="account.balance" placeholder="0.00" />
      </el-form-item>
      <el-form-item label="不计入可支配资产">
        <el-switch v-model="account.isTotal" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
      </el-form-item>
    </el-form>
  </div>
  <h1>{{ data }}</h1>
  <h1>{{ userInfo }}</h1>
  <el-button @click="click()">clicmk</el-button>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";
import { computed, onMounted, reactive, ref } from "vue";
import useElMessage from "@/hooks/useElMessage";
import Assets from "@/views/Assets";
import { accounts } from "@/static/accounts";
import { ElNotification } from "element-plus";

const store = useStore();
const isAssets = ref(false);
const account = reactive({
  accountName: null,
  type: [],
  tag: "",
  balance: 0.0,
  remark: null,
  isTotal: "1",
  removeTag: 0,
  userId: "",
});
let data = reactive({
  userInfo: {
    userId: store.state.userId,
    username: "",
    accounts: [],
    recentTrade: [],
    expend: undefined,
    income: undefined,
  },
});

const userInfo = computed(() => {
  return data.userInfo;
});

const getUserInfo = async () => {
  console.log("getUserInfo");
  axios.get("/user/userInfo?userId=" + store.state.userId).then(
    (response) => {
      data.userInfo = response.data.result.userInfo;
    },
    (error) => {
      if (error.response.status === 406) {
        useElMessage("服务异常，请重新登陆！", "服务异常", () => {
          store.dispatch("logout");
        });
      }
    }
  );
};
const onSubmit = async () => {
  account.tag = account.type[1];
  account.userId = store.state.userId;
  account.balance = parseFloat(account.balance.toString()) * 100;
  account.isTotal = account.isTotal ? "0" : "1";
  console.log(account);
  axios.post("/account/addAccount", account).then(
    () => {
      ElNotification({
        title: "成功",
        message: "账户添加成功！",
      });
    },
    (error) => {
      console.log(error.response);
    }
  );
};
onMounted(getUserInfo);

const click = () => {
  console.log(account.type[2]);
};
</script>

<style scoped></style>
