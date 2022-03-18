<template>
  <div class="data-header">
    <p style="margin-left: 20px">
      <span style="font-size: 18px; font-weight: bold">
        本月支出：¥{{ data.userInfo.expend ? data.userInfo.expend : "0.00" }}
      </span>
      <span style="font-size: 14px">
        收入：¥{{ data.userInfo.income ? data.userInfo.income : "0.00" }}
      </span>
      <span style="font-size: 14px">
        结余：¥{{
          (data.userInfo.income ? data.userInfo.income : 0.0) -
          (data.userInfo.expend ? data.userInfo.expend : 0.0)
        }}
      </span>
    </p>
    <el-button
      type="success"
      icon="el-icon-edit"
      @click="isTrade = true"
      style="font-weight: bold; margin-right: 20px"
      >记一笔</el-button
    >
  </div>

  <el-divider></el-divider>

  <div>
    <h1>
      我的资产
      <el-switch v-model="isAssets" />
    </h1>
    <Assets
      v-if="isAssets"
      :user="data.userInfo"
      @getUserInfo="getUserInfo"
    ></Assets>
  </div>

  <el-divider></el-divider>

  <div>
    <Trades :recent-trade="data.userInfo.recentTrade"></Trades>
  </div>

  <el-button @click="click()">clicmk</el-button>
  <br /><br /><br />

  <el-drawer v-model="isTrade" direction="rtl" :destroy-on-close="true">
    <template #default>
      <div>
        <el-tabs v-model="tabName" type="card" :stretch="true">
          <el-tab-pane label="支出" name="1">
            <el-form :model="trade" label-width="100px" ref="tradeForm">
              <el-form-item label="账户" prop="accountId">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择支出账户"
                >
                  <el-option
                    v-for="item in data.userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="消费类型" prop="tradeTag">
                <el-cascader
                  :options="expendMenu"
                  v-model="trade.tradeTag"
                  :show-all-levels="false"
                  placeholder="请选择消费类型"
                />
              </el-form-item>
              <el-form-item label="金额" prop="tradeAmount">
                <el-input-number
                  v-model="trade._tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input v-model="trade.remark" placeholder="请输入备注信息" />
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="收入" name="2">
            <el-form :model="trade" label-width="100px" ref="tradeForm">
              <el-form-item label="账户" prop="accountId">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择收入账户"
                >
                  <el-option
                    v-for="item in data.userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="收入类型" prop="tradeTag">
                <el-cascader
                  :options="incomeMenu"
                  v-model="trade.tradeTag"
                  :show-all-levels="false"
                  placeholder="请选择收入类型"
                />
              </el-form-item>
              <el-form-item label="金额" prop="tradeAmount">
                <el-input-number
                  v-model="trade._tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input v-model="trade.remark" placeholder="请输入备注信息" />
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <!--          todo 可以优化为组件-->
          <el-tab-pane label="转账" name="3">
            <el-form :model="trade" label-width="100px" ref="tradeForm">
              <el-form-item label="转出账户" prop="accountId">
                <el-select
                  v-model="trade.accountId"
                  placeholder="请选择转出账户"
                >
                  <el-option
                    v-for="item in data.userInfo.accounts"
                    :key="item.accountId"
                    :label="
                      item.accountName != null ? item.accountName : item.tagName
                    "
                    :value="item.accountId"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="转入账户" prop="rsrvStr1">
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
              <el-form-item label="金额" prop="tradeAmount">
                <el-input-number
                  v-model="trade._tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="手续费" prop="rsrvStr2">
                <el-input-number
                  v-model="trade._rsrvStr2"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input v-model="trade.remark" placeholder="请输入备注信息" />
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </div>
      <div style="padding-left: 50%; padding-top: 200px">
        <el-button @click="isTrade = false">取消</el-button>
        <el-button type="primary" @click="confirmTrade(tradeForm)"
          >确定</el-button
        >
      </div>
    </template>
  </el-drawer>
</template>

<script setup>
import axios from "axios";
import { useStore } from "vuex";
import { computed, onMounted, reactive, ref } from "vue";
import useElMessage from "@/hooks/useElMessage";
import Assets from "@/components/Assets";
import { expendMenu, incomeMenu } from "@/static/trade";
import { ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";
import Trades from "@/components/Trades";

const store = useStore();
const isAssets = ref(true); //是否显示资产详情
let isTrade = ref(false); //是否显示记账
const tabName = ref("1");
const tradeForm = ref(); //记账表单
const trade = reactive({
  userId: store.state.userId,
  accountId: null,
  tradeTag: [],
  tradeName: String.constructor,
  tradeType: String.constructor,
  _tradeAmount: 0,
  tradeAmount: Number.constructor,
  remark: null,
  isReTrade: "0",
  rsrvStr1: null, //转账时转入账户标识
  _rsrvStr2: 0,
  rsrvStr2: Number.constructor, //转账时手续费
});
let data = reactive({
  userInfo: {
    userId: store.state.userId,
    username: String.constructor,
    accounts: [],
    recentTrade: [],
    expend: Number.constructor,
    income: Number.constructor,
    menu: Array.constructor,
  }, //用户基本信息
});

//转入账户
const transAccounts = computed(() => {
  return data.userInfo.accounts.filter((account) => {
    return account.accountId !== trade.accountId;
  });
});

//获取用户信息
const getUserInfo = async () => {
  console.log("getUserInfo");
  axios.get(`/user/userInfo?userId=${store.state.userId}`).then(
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
//记账
async function confirmTrade(tradeForm) {
  trade.tradeType = tabName.value;
  trade.tradeName = trade.tradeTag.slice(-1).pop();
  trade.tradeAmount = trade._tradeAmount * 100;
  trade.rsrvStr2 = trade._rsrvStr2 * 100;
  console.log(trade);
  await axios.post("/trade/book", trade).then(
    () => {
      //重置表单
      tradeForm.resetFields();
      getUserInfo();
      isTrade.value = false;
      ElNotification({
        title: "成功",
        message: "提交成功！",
        type: "success",
      });
    },
    (error) => {
      errorNotification(error.response.data.message);
    }
  );
}
onMounted(getUserInfo);

const click = () => {
  console.log(!!data.userInfo.expend);
};
</script>

<style scoped>
.data-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 75%;
  height: 100px;
  margin-top: 30px;
  border-radius: 25px;
  background-color: #a0cfff;
}
.el-select,
.el-input__inner,
.el-input-number,
.el-input {
  width: 200px;
}
</style>
