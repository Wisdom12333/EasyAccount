<template>
  <div class="data-header">
    <p style="margin-left: 20px">
      <span style="font-size: 18px; font-weight: bold">
        本月支出：¥{{ userInfoNow.expend ? userInfoNow.expend : "0.00" }}
      </span>
      <span style="font-size: 14px">
        收入：¥{{ userInfoNow.income ? userInfoNow.income : "0.00" }}
      </span>
      <span style="font-size: 14px">
        结余：¥{{
          (userInfoNow.income ? userInfoNow.income : 0.0) -
          (userInfoNow.expend ? userInfoNow.expend : 0.0)
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
      :user="userInfoNow"
      @getUserInfo="getUserInfo"
    ></Assets>
  </div>

  <el-divider></el-divider>

  <div>
    <Trades :user-info="userInfoNow"></Trades>
  </div>

  <el-button @click="click()">click</el-button>
  <br /><br /><br />

  <el-drawer v-model="isTrade" direction="rtl" :destroy-on-close="true">
    <template #default>
      <div>
        <el-tabs v-model="tabName" type="card" :stretch="true">
          <el-tab-pane label="支出" name="1">
            <el-form :model="tradeNew" label-width="100px" ref="tradeForm">
              <el-form-item label="账户" prop="accountId">
                <el-select
                  v-model="tradeNew.accountId"
                  placeholder="请选择支出账户"
                >
                  <el-option
                    v-for="item in userInfoNow.accounts"
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
                  v-model="tradeNew.tradeTag"
                  :show-all-levels="false"
                  placeholder="请选择消费类型"
                />
              </el-form-item>
              <el-form-item label="金额" prop="tradeAmount">
                <el-input-number
                  v-model="tradeNew.tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="tradeNew.remark"
                  placeholder="请输入备注信息"
                />
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="收入" name="2">
            <el-form :model="tradeNew" label-width="100px" ref="tradeForm">
              <el-form-item label="账户" prop="accountId">
                <el-select
                  v-model="tradeNew.accountId"
                  placeholder="请选择收入账户"
                >
                  <el-option
                    v-for="item in userInfoNow.accounts"
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
                  v-model="tradeNew.tradeTag"
                  :show-all-levels="false"
                  placeholder="请选择收入类型"
                />
              </el-form-item>
              <el-form-item label="金额" prop="tradeAmount">
                <el-input-number
                  v-model="tradeNew.tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="tradeNew.remark"
                  placeholder="请输入备注信息"
                />
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <!--          todo 可以优化为组件-->
          <el-tab-pane label="转账" name="3">
            <el-form :model="tradeNew" label-width="100px" ref="tradeForm">
              <el-form-item label="转出账户" prop="accountId">
                <el-select
                  v-model="tradeNew.accountId"
                  placeholder="请选择转出账户"
                >
                  <el-option
                    v-for="item in userInfoNow.accounts"
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
                  v-model="tradeNew.rsrvStr1"
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
                  v-model="tradeNew.tradeAmount"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="手续费" prop="rsrvStr2">
                <el-input-number
                  v-model="tradeNew.rsrvStr2"
                  :precision="2"
                  :min="0"
                  :controls="false"
                  placeholder="0.00"
                />
              </el-form-item>
              <el-form-item label="备注" prop="remark">
                <el-input
                  v-model="tradeNew.remark"
                  placeholder="请输入备注信息"
                />
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

<script setup lang="ts">
import axios from "axios";
import { useStore } from "vuex";
import { computed, onMounted, reactive, ref } from "vue";
import useElMessage from "@/hooks/useElMessage";
import Assets from "@/components/Assets.vue";
import { expendMenu, incomeMenu } from "@/static/trade";
import { ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";
import Trades from "@/components/Trades.vue";
import type { FormInstance } from "element-plus";
import { account, trade, userInfo } from "@/static/entity";

const store = useStore();
const isAssets = ref<boolean>(true); //是否显示资产详情
let isTrade = ref<boolean>(false); //是否显示记账
const tabName = ref<string>("1");
const tradeForm = ref<FormInstance>(); //记账表单
const tradeNew = reactive<trade>(new trade());
let userInfoNow = reactive<userInfo>(new userInfo()); //用户基本信息

//转入账户
const transAccounts = computed(() => {
  return userInfoNow.accounts.filter((account: account) => {
    return account.accountId !== tradeNew.accountId;
  });
});

//获取用户信息
const getUserInfo = async () => {
  console.log("getUserInfo");
  axios.get(`/user/userInfo?userId=${store.state.userId}`).then(
    (response) => {
      userInfoNow = response.data.result.userInfo;
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
async function confirmTrade(tradeForm: FormInstance) {
  tradeNew.tradeType = tabName.value;
  tradeNew.tradeName = (tradeNew.tradeTag as []).slice(-1).pop();
  tradeNew.tradeAmount = (tradeNew.tradeAmount as number) * 100;
  tradeNew.rsrvStr2 = (
    parseFloat(tradeNew.rsrvStr2 as string) * 100
  ).toString();
  console.log(tradeNew);
  await axios.post("/trade/book", tradeNew).then(
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
  console.log();
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
