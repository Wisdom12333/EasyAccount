<template>
  <h1 style="font-size: 30px">EasyAccount</h1>
  <hr/>
  <p>
    本月支出：¥{{ userInfo.expend ? userInfo.expend : "0.00" }}，
    本月收入：¥{{ userInfo.income ? userInfo.income : "0.00" }},
    本月结余：¥{{ (userInfo.income ? userInfo.income : 0.00) - (userInfo.expend ? userInfo.expend : 0.00) }}
  </p>
  <h1>我的资产
    <el-switch v-model="isAssets"/>
  </h1>
  <Assets v-if="isAssets"></Assets>
  <div v-if="userInfo.accounts.length !== 0">
    {{ userInfo.accounts }}
  </div>
  <div v-else>
    <h1>还没有任何账户！</h1>
    <router-link to="">点击新增账户</router-link>
    <br/><br/><br/>
    <el-form :model="account" label-width="140px">
      <el-form-item label="账户类型">
        <el-cascader
            :options="accounts"
            :show-all-levels="false"
            v-model="account.type"
        />
      </el-form-item>
      <div v-show="account.type[1]">
        <el-form-item :label="acType===4?'借款方名称':'账户名称'">
          <el-input
              v-model="account.accountName"
              :placeholder="'请输入'+(acType===4?'借款方':'账户')+'名称（可不填）'"
          />
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input
              v-model="account.remark"
              placeholder="请输入备注信息（可不填）"
          />
        </el-form-item>
        <template v-if="acType===1">
          <el-form-item label="信用额度">
            <el-input v-model="account.rsrvStr1" placeholder="0.00"/>
          </el-form-item>
          <el-form-item label="当前欠款">
            <el-input v-model="account.rsrvStr2" placeholder="0.00"/>
          </el-form-item>
          <el-form-item label="账户余额" style="display: none">
            <el-input v-model="account.balance" placeholder="0.00"/>
          </el-form-item>
        </template>
        <template v-else-if="acType===4">
          <el-form-item label="借款金额">
            <el-input v-model="account.balance" placeholder="0.00"/>
          </el-form-item>
        </template>
        <template v-else>
          <el-form-item label="账户余额">
            <el-input v-model="account.balance" placeholder="0.00"/>
          </el-form-item>
        </template>
        <el-form-item label="不计入可支配资产">
          <el-switch v-model="account.isTotal"/>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addAccount">提交</el-button>
        </el-form-item>
      </div>

    </el-form>
  </div>
  <h1>{{ }}</h1>
  <h1>{{ }}</h1>
  <el-button @click="click()">clicmk</el-button>
  <el-button @click="isTrade = true">记一笔</el-button>

  <el-drawer v-model="isTrade" direction="rtl" destroy-on-close="true">
    <template #default>
      <div>
        <el-tabs
            v-model="tabName"
            type="card"
            stretch="true"
        >
          <el-tab-pane label="Expend">
            支出
            <el-form :model="trade" label-width="100px">
              <el-form-item label="金额">
                <el-input v-model="trade.tradeAmount" placeholder="0.00"/>
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="trade.remark" placeholder="请输入备注信息"/>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="Income">收入</el-tab-pane>
          <el-tab-pane label="Transfer">转账</el-tab-pane>
        </el-tabs>
      </div>
      <div style="flex: auto">
        <el-button @click="cancelTrade">取消</el-button>
        <el-button type="primary" @click="confirmTrade">确定</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script setup>
import axios from "axios";
import {useStore} from "vuex";
import {computed, onMounted, reactive, ref} from "vue";
import useElMessage from "@/hooks/useElMessage";
import Assets from "@/views/Assets";
import {accounts, acMap} from "@/static/accounts";
import {ElNotification} from "element-plus";

const store = useStore();
const isAssets = ref(false);//是否显示资产详情
const isTrade = ref(false);//是否显示记账
const tabName = ref("first");
const account = reactive({
  accountName: null,
  type: [],
  tag: "",
  balance: 0.0,
  remark: null,
  isTotal: "1",
  removeTag: 0,
  userId: "",
  tagName: "",
  rsrvStr1: 0.0,
  rsrvStr2: 0.0,
});//新建账户对象
const trade = reactive({
  tradeType: "",
  tradeAmount: "",
  remark: "",
});
let data = reactive({
  userInfo: {
    userId: store.state.userId,
    username: "",
    accounts: [],
    recentTrade: [],
    expend: undefined,
    income: undefined,
  },//用户基本信息
});

const userInfo = computed(() => {
  return data.userInfo;
});
const acType = computed(() => {
  if (account.type[0]) return parseInt(account.type[0].toString());
  else return undefined;
});
//获取用户信息
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
//新建账户
const addAccount = async () => {
  account.tag = account.type[1];
  account.userId = store.state.userId;
  if (acType.value === 1) {
    account.rsrvStr1 = parseFloat(account.rsrvStr1.toString()) * 100;
    account.rsrvStr2 = parseFloat(account.rsrvStr2.toString()) * 100;
  }
  account.balance = acType.value === 1 ?
      account.rsrvStr1 - account.rsrvStr2 :
      parseFloat(account.balance.toString()) * 100;
  account.isTotal = account.isTotal ? "0" : "1";
  account.tagName = acMap.get(account.tag);
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
//关闭记账
const cancelTrade = () => {
  isTrade.value = false;
};
//记账
const confirmTrade = () => {
  isTrade.value = false;
};
onMounted(getUserInfo);

const click = () => {
  console.log(userInfo.expend);
};
</script>

<style scoped></style>
