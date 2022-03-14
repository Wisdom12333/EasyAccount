<template>
  <h1 style="font-size: 30px">EasyAccount</h1>
  <hr />
  <p>
    本月支出：¥{{ userInfo.expend ? userInfo.expend : "0.00" }}， 本月收入：¥{{
      userInfo.income ? userInfo.income : "0.00"
    }}， 本月结余：¥{{
      (userInfo.income ? userInfo.income : 0.0) -
      (userInfo.expend ? userInfo.expend : 0.0)
    }}
  </p>
  <h1>
    我的资产
    <el-switch v-model="isAssets" />
  </h1>
  <Assets v-if="isAssets"></Assets>
  <div v-if="userInfo.accounts.length !== 0">
    <div v-for="item in userInfo.accounts" :key="item.accountId">
      <p>{{ item.tagName }},{{ item.balance / 100 }}</p>
    </div>
  </div>
  <div v-else>
    <h1>还没有任何账户！</h1>
    <router-link to="">点击新增账户</router-link>
    <br /><br /><br />
    <el-form :model="account" label-width="140px">
      <el-form-item label="账户类型">
        <el-cascader
            :options="accounts"
            v-model="account.type"
            :show-all-levels="false"
        />
      </el-form-item>
      <div v-show="account.type[1]">
        <el-form-item :label="acType === 4 ? '借款方名称' : '账户名称'">
          <el-input
            v-model="account.accountName"
            :placeholder="
              '请输入' + (acType === 4 ? '借款方' : '账户') + '名称（可不填）'
            "
          />
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input
            v-model="account.remark"
            placeholder="请输入备注信息（可不填）"
          />
        </el-form-item>
        <template v-if="acType === 1">
          <el-form-item label="信用额度">
            <el-input-number v-model="account._rsrvStr1" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
          <el-form-item label="当前欠款">
            <el-input-number v-model="account._rsrvStr2" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
          <el-form-item label="账户余额" style="display: none">
            <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
        </template>
        <el-form-item v-else-if="acType === 4" label="借款金额">
          <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
        </el-form-item>
        <el-form-item v-else label="账户余额">
          <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
        </el-form-item>
        <el-form-item label="不计入可支配资产">
          <el-switch v-model="account.isTotal" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="addAccount">提交</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>
  <h1>{{}}</h1>
  <h1>{{}}</h1>
  <el-button @click="click()">clicmk</el-button>
  <el-button @click="isTrade = true">记一笔</el-button>
  <br/><br/><br/>

  <el-drawer v-model="isTrade" direction="rtl" :destroy-on-close="true">
    <template #default>
      <div>
        <el-tabs v-model="tabName" type="card" :stretch="true">
          <el-tab-pane label="支出" name="1">
            <el-form :model="trade" label-width="100px">
              <el-form-item label="账户">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择支出账户"
                >
                  <el-option
                    v-for="item in userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="消费类型">
                <el-cascader
                    :options="expendMenu"
                    v-model="trade.tradeTag"
                    :show-all-levels="false"
                    placeholder="请选择消费类型"
                />
              </el-form-item>
              <el-form-item label="金额">
                <el-input-number v-model="trade._tradeAmount" :precision="2" :min="0" :controls="false" placeholder="0.00" />
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="trade.remark" placeholder="请输入备注信息" />
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="收入" name="2">
            <el-form :model="trade" label-width="100px">
              <el-form-item label="账户">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择收入账户"
                >
                  <el-option
                    v-for="item in userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="收入类型">
                <el-cascader
                    :options="incomeMenu"
                    v-model="trade.tradeTag"
                    :show-all-levels="false"
                    placeholder="请选择收入类型"
                />
              </el-form-item>
              <el-form-item label="金额">
                <el-input-number v-model="trade._tradeAmount" :precision="2" :min="0" :controls="false" placeholder="0.00" />
              </el-form-item>
              <el-form-item label="备注">
                <el-input v-model="trade.remark" placeholder="请输入备注信息" />
              </el-form-item>
            </el-form>
          </el-tab-pane>
<!--          todo 可以优化为组件-->
          <el-tab-pane label="转账" name="3">
            <el-form label-width="100px">
              <el-form-item label="转出账户">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择转出账户"
                >
                  <el-option
                    v-for="item in userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="转入账户">
                <el-select
                  v-model="trade.rsrvStr1"
                  placeholder="请选择转入账户"
                >
                  <el-option
                    v-for="item in transAccounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="金额">
                <el-input-number v-model="trade._tradeAmount" :precision="2" :min="0" :controls="false" placeholder="0.00" />
              </el-form-item>
              <el-form-item label="手续费">
                <el-input-number v-model="trade._rsrvStr2" :precision="2" :min="0" :controls="false" placeholder="0.00"/>
              </el-form-item>
            </el-form>
          </el-tab-pane>
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
import {expendMenu, incomeMenu} from "@/static/trade";
import {ElNotification} from "element-plus";

const store = useStore();
const isAssets = ref(false); //是否显示资产详情
let isTrade = ref(false); //是否显示记账
const tabName = ref("1");
const account = reactive({
  accountName: "",
  type: [],
  tag: String.constructor,
  _balance: 0,
  balance: Number.constructor,
  remark: null,
  isTotal: "1",
  removeTag: 0,
  userId: store.state.userId,
  tagName: String.constructor,
  _rsrvStr1: 0,
  rsrvStr1: Number.constructor,
  _rsrvStr2: 0,
  rsrvStr2: Number.constructor,
}); //新建账户对象
const trade = reactive({
  userId: store.state.userId,
  accountId: null,
  tradeTag: [],
  tradeName: String.constructor,
  tradeType: String.constructor,
  _tradeAmount: null,
  tradeAmount: Number.constructor,
  remark: null,
  isReTrade: "0",
  rsrvStr1: null, //转账时转入账户标识
  _rsrvStr2: null,
  rsrvStr2: Number.constructor, //转账时手续费
});
let data = reactive({
  userInfo: {
    userId: store.state.userId,
    username: String.constructor,
    accounts: Array.constructor,
    recentTrade: Array.constructor,
    expend: Number.constructor,
    income: Number.constructor,
    menu: Array.constructor,
  }, //用户基本信息
});

//用户信息
const userInfo = computed(() => {
  return data.userInfo;
});
//账户类型
const acType = computed(() => {
  if (account.type[0]) return parseInt(account.type[0].toString());
  else return undefined;
});
//转入账户
const transAccounts = computed(() => {
  return userInfo.value.accounts.filter((account) => {
    return account.accountId !== trade.accountId;
  });
});

//获取用户信息
const getUserInfo = async () => {
  console.log("getUserInfo");
  axios.get("/user/userInfo?userId=" + store.state.userId).then(
    (response) => {
      data.userInfo = response.data.result.userInfo;
    },
    (error) => {
      if (error.response.status === 400) {
        useElMessage("服务异常，请重新登陆！", "服务异常", () => {
          store.dispatch("logout");
        });
      }
    }
  );
};
//新建账户
async function addAccount() {
  account.tag = account.type[1];
  account.userId = store.state.userId;
  if (acType.value === 1) {
    account.rsrvStr1 = account._rsrvStr1 * 100;
    account.rsrvStr2 = account._rsrvStr2 * 100;
  }
  account.balance =
    acType.value === 1
      ? account.rsrvStr1 - account.rsrvStr2
      : account.balance * 100;
  account.isTotal = account.isTotal ? "0" : "1";
  account.tagName = acMap.get(account.tag).toString;
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
}
//关闭记账
const cancelTrade = () => isTrade.value = false;
//记账
function confirmTrade(){
  trade.tradeType = tabName.value;
  trade.tradeName = trade.tradeTag.slice(-1).pop();
  trade.tradeAmount = trade._tradeAmount * 100;
  trade.rsrvStr2 = trade._rsrvStr2 * 100;
  console.log(trade);
  axios.post("/trade/book", trade).then(
    () => {
      ElNotification({
        title: "成功",
        message: "提交成功！",
      });
      getUserInfo();
      isTrade.value = false;
    },
    (error) => {
      console.log(error.response);
    }
  );
  // isTrade.value = false;
}
onMounted(getUserInfo);

const click = () => {
  console.log(!!userInfo.value.expend);
};
</script>

<style scoped></style>
