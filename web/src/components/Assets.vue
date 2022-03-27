<template>
  <el-card class="account-card" shadow="hover">
    <template #header>
      <div class="card-header">
        <span style="font-weight: bold">我的账户</span>
        <el-button type="text" @click="showAddAccount = true"
          >新增账户</el-button
        >
      </div>
    </template>
    <div>
      <el-table
        :data="userInfoProps?.accounts"
        :show-header="false"
        style="width: 100%"
      >
        <el-table-column type="expand">
          <template #default="scope">
            <p>账户名称: {{ scope.row.accountName }}</p>
            <p>备注: {{ scope.row.remark ? scope.row.remark : "无" }}</p>
          </template>
        </el-table-column>
        <el-table-column label="账户类型">
          <template #default="scope">
            <div style="display: flex; align-items: center">
              <span style="font-weight: bold; font-size: large">{{
                scope.row.tagName
              }}</span>
              <span style="margin-left: 10px; color: #c8c9cc">{{
                scope.row.accountName
              }}</span>
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
              <el-button
                size="small"
                :icon="Edit"
                @click="console.log('dd')"
              ></el-button>
              <el-popover placement="bottom" :width="200">
                <p>确定要删除这个账户吗？</p>
                <div style="text-align: right; margin: 0">
                  <el-button
                    size="small"
                    type="danger"
                    @click="handleDelete(scope.row)"
                    >确定</el-button
                  >
                </div>
                <template #reference>
                  <el-button size="small" type="danger" :icon="Delete">
                  </el-button>
                </template>
              </el-popover>
            </el-space>
          </template>
        </el-table-column>
        <!--为空时展示内容-->
        <template #empty>
          <el-empty
            description="还没有任何账户！"
            style="height: 250px"
          ></el-empty>
        </template>
      </el-table>
    </div>
  </el-card>

  <el-dialog v-model="showAddAccount" destroy-on-close>
    <template #title>
      <h1>新建账户</h1>
    </template>
    <el-form :model="accountNew" label-width="100px" ref="accountForm">
      <el-form-item label="账户类型" prop="type">
        <el-cascader
          :options="accounts"
          v-model="accountNew.type"
          :show-all-levels="false"
        />
      </el-form-item>
      <div v-show="accountNew.type[1]">
        <el-form-item
          :label="acType === 4 ? '借款方名称' : '账户名称'"
          prop="accountName"
        >
          <el-input
            v-model="accountNew.accountName"
            :placeholder="
              '请输入' + (acType === 4 ? '借款方' : '账户') + '名称（可不填）'
            "
          />
        </el-form-item>
        <el-form-item label="备注信息" prop="remark">
          <el-input
            v-model="accountNew.remark"
            placeholder="请输入备注信息（可不填）"
          />
        </el-form-item>
        <template v-if="acType === 1">
          <el-form-item label="信用额度" prop="rsrvStr1">
            <el-input-number
              v-model="accountNew.rsrvStr1"
              :precision="2"
              :min="0"
              :controls="false"
              placeholder="0.00"
            />
          </el-form-item>
          <el-form-item label="当前欠款" prop="rsrvStr2">
            <el-input-number
              v-model="accountNew.rsrvStr2"
              :precision="2"
              :min="0"
              :controls="false"
              placeholder="0.00"
            />
          </el-form-item>
          <el-form-item label="账户余额" style="display: none" prop="balance">
            <el-input-number
              v-model="accountNew.balance"
              :precision="2"
              :min="0"
              :controls="false"
              placeholder="0.00"
            />
          </el-form-item>
        </template>
        <el-form-item v-else-if="acType === 4" label="借款金额" prop="balance">
          <el-input-number
            v-model="accountNew.balance"
            :precision="2"
            :min="0"
            :controls="false"
            placeholder="0.00"
          />
        </el-form-item>
        <el-form-item v-else label="账户余额" prop="balance">
          <el-input-number
            v-model="accountNew.balance"
            :precision="2"
            :min="0"
            :controls="false"
            placeholder="0.00"
          />
        </el-form-item>
        <el-form-item
          label="不计入可支配资产"
          label-width="150px"
          prop="isTotal"
        >
          <el-switch v-model="accountNew.isTotal" />
        </el-form-item>
      </div>
    </el-form>
    <template #footer>
      <span>
        <el-button @click="showAddAccount = false" style="font-weight: bold"
          >取消</el-button
        >
        <el-button
          type="primary"
          @click="addAccount(accountForm)"
          style="font-weight: bold"
          >提交</el-button
        >
      </span>
    </template>
  </el-dialog>
  <el-button @click="cl">assets</el-button>
</template>

<script setup lang="ts">
import { computed, reactive, ref } from "vue";
import { useStore } from "vuex";
import { accounts, acMap } from "@/static/accounts";
import axios from "axios";
import { ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";
import type { FormInstance } from "element-plus";
import { account } from "@/static/entity";
import { str2MoneyStr } from "@/hooks/StringUtils";
import { Edit, Delete } from "@element-plus/icons-vue";

const store = useStore();

const props = defineProps({ userInfoProps: Object });
const emit = defineEmits(["getUserInfo"]);

const showAddAccount = ref<boolean>(false);
const accountForm = ref<FormInstance>();
const accountNew = reactive<account>(new account()); //新建账户对象

//账户类型
const acType = computed(() => {
  if (accountNew.type && accountNew.type[0])
    return parseInt(accountNew.type[0].toString());
  else return undefined;
});

function cl() {
  console.log(props);
}
//新建账户
async function addAccount(accountForm: FormInstance): Promise<void> {
  if (accountNew.type) accountNew.tag = accountNew.type[1];
  accountNew.userId = store.state.userId;
  if (acType.value === 1) {
    accountNew.rsrvStr1 = str2MoneyStr(accountNew.rsrvStr1 as string);
    accountNew.rsrvStr2 = str2MoneyStr(accountNew.rsrvStr2 as string);
  }
  accountNew.balance =
    acType.value === 1
      ? parseFloat(accountNew.rsrvStr1 as string) -
        parseFloat(accountNew.rsrvStr2 as string)
      : (accountNew.balance as number) * 100;
  accountNew.isTotal = accountNew.isTotal ? "0" : "1";
  accountNew.tagName = acMap.get(accountNew.tag as string)?.toString();
  console.log(accountNew);
  await axios.post("/account/addAccount", accountNew).then(
    () => {
      ElNotification({
        title: "成功",
        message: "账户添加成功！",
        type: "success",
      });
      //重置表单
      accountForm.resetFields();
      showAddAccount.value = false;
      emit("getUserInfo");
    },
    (error) => {
      errorNotification(error.response.data.message);
    }
  );
}
function handleDelete(row: account): void {
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
  );
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
