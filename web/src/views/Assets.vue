<template>
  <el-card class="account_card" shadow="hover">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">我的账户</span>
        <el-button class="button" type="text" @click="showAddAccount = true">新增账户</el-button>
      </div>
    </template>
    <div>
      <el-table
          :data="user.accounts"
          :row-key="user.accounts.accountId"
          :show-header="false"
          style="width: 100%"
          @expand-change="cl"
      >
        <el-table-column type="expand">
          <template #default="scope">
            <p>账户名称: {{ scope.row.accountName }}</p>
            <p>备注: {{ scope.row.tagName }}</p>
          </template>
        </el-table-column>
        <el-table-column label="账户类型">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="font-weight: bold;font-size: large">{{ scope.row.tagName }}</span>
              <span style="margin-left: 10px;color: gray">{{ scope.row.accountName }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="余额">
          <template #default="scope">
            {{ scope.row.balance / 100 }}
          </template>
        </el-table-column>
        <el-table-column align="right">
          <template #default="scope">
            <el-space :size="5" spacer="|">
              <el-button size="small" icon="el-icon-edit-outline" @click=""></el-button>
              <el-popover placement="bottom" :width="200">
                <p>确定要删除这个账户吗？</p>
                <div style="text-align: right; margin: 0">
                  <el-button size="small" type="danger" @click="handleDelete(scope.row)">确定</el-button>
                </div>
                <template #reference>
                  <el-button size="small"
                             type="danger"
                             icon="el-icon-delete"
                             @click="">
                  </el-button>
                </template>
              </el-popover>
            </el-space>
          </template>
        </el-table-column>
        <!--为空时展示内容-->
        <template #empty>
          <el-empty description="还没有任何账户！" style="height: 250px"></el-empty>
        </template>
      </el-table>
    </div>
  </el-card>


  <el-dialog v-model="showAddAccount" destroy-on-close>
    <template #title>
      <h1>新建账户</h1>
    </template>
    <el-form :model="account" label-width="100px" ref="accountForm">
      <el-form-item label="账户类型" prop="type">
        <el-cascader
            :options="accounts"
            v-model="account.type"
            :show-all-levels="false"
        />
      </el-form-item>
      <div v-show="account.type[1]">
        <el-form-item :label="acType === 4 ? '借款方名称' : '账户名称'" prop="accountName">
          <el-input
              v-model="account.accountName"
              :placeholder="'请输入' + (acType === 4 ? '借款方' : '账户') + '名称（可不填）'"
          />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input
              v-model="account.remark"
              placeholder="请输入备注信息（可不填）"
          />
        </el-form-item>
        <template v-if="acType === 1">
          <el-form-item label="信用额度" prop="rsrvStr1">
            <el-input-number v-model="account._rsrvStr1" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
          <el-form-item label="当前欠款" prop="rsrvStr2">
            <el-input-number v-model="account._rsrvStr2" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
          <el-form-item label="账户余额" style="display: none" prop="balance">
            <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
          </el-form-item>
        </template>
        <el-form-item v-else-if="acType === 4" label="借款金额" prop="balance">
          <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
        </el-form-item>
        <el-form-item v-else label="账户余额" prop="balance">
          <el-input-number v-model="account._balance" :precision="2" :min="0" :controls="false" placeholder="0.00" />
        </el-form-item>
        <el-form-item label="不计入可支配资产" label-width="150px" prop="isTotal">
          <el-switch v-model="account.isTotal" />
        </el-form-item>
      </div>
    </el-form>
    <template #footer>
      <span>
        <el-button @click="showAddAccount = false" style="font-weight: bold">取消</el-button>
        <el-button type="primary" @click="addAccount" style="font-weight: bold">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>

import {computed, reactive, ref} from "vue";
import {useStore} from "vuex";
import {accounts, acMap} from "@/static/accounts";
import axios from "axios";
import {ElNotification} from "element-plus";
import errorNotification from "@/hooks/errorNotification";

const store = useStore();

const props = defineProps({
  user: Object,
});
const emit = defineEmits(["getUserInfo"]);

const showAddAccount = ref(false);
const account = reactive({
  accountName: null,
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


//账户类型
const acType = computed(() => {
  if (account.type[0]) return parseInt(account.type[0].toString());
  else return undefined;
});

function cl(row,num){
  if(num.length===1){
    console.log("展开");
  }else {
    console.log("关闭")
  }
  console.log(row,num);
}
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
          : account._balance * 100;
  account.isTotal = account.isTotal ? "0" : "1";
  account.tagName = acMap.get(account.tag).toString();
  console.log(account);
  await axios.post("/account/addAccount", account).then(
      () => {
        ElNotification({
          title: "成功",
          message: "账户添加成功！",
          type: "success",
        });
        //重置表单
        this.$refs.accountForm.resetFields();
        showAddAccount.value = false;
        emit("getUserInfo");
      },
      (error) => {
        errorNotification(error.response.data.message);
      }
  );
}
function handleDelete(row){
  axios.get(`/account/deleteAccount?accountId=${row.accountId}`).then(
      () => {
        ElNotification({
          title: "成功",
          message: "账户删除成功！",
          type: "success",
        });
        emit("getUserInfo");
      },
      (error) => {
        errorNotification(error.response.data.message);
      }
  )

}
</script>

<style scoped>
.el-card {
  width: 75%;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.el-input {
  width: 200px;
}
</style>
