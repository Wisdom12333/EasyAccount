<template>
  <el-card shadow="always">
    <el-table
      ref="tradesRef"
      :data="trades"
      max-height="450"
      style="padding-bottom: 20px; padding-top: 10px"
    >
      <el-table-column type="selection" width="55" style="padding-left: 5px" />
      <el-table-column label="账单信息" style="width: 75%; padding: 0 10px 0 0">
        <template #header>
          <el-row :gutter="30" style="padding-right: 10px">
            <el-col :span="16">
              <el-input v-model="search" size="small" placeholder="" />
            </el-col>
            <el-col :span="8" style="text-align: right">
              <el-button type="primary" size="small" style="margin-right: 5px"
                >编辑</el-button
              >
              <el-button type="danger" size="small" @click="deleteBatch()"
                >批量删除</el-button
              >
            </el-col>
          </el-row>
        </template>
        <template #default="scope">
          <el-card
            body-style="height: 60px"
            style="height: 60px; --el-card-padding: 10px; margin-right: 10px"
          >
            <el-row>
              <el-col :span="16">
                <div style="font-weight: bold">{{ scope.row.tradeName }}</div>
                <div>
                  <span>{{ getAccountName(scope.row.accountId) }}</span>
                  <span class="trade-remark">{{ scope.row.remark }}</span>
                </div>
              </el-col>
              <el-col :span="8">
                <div
                  :style="
                    'padding-top: 5px;font-weight: bold;color: ' +
                    getTradeColor(scope.row.tradeType)
                  "
                >
                  {{ scope.row.tradeAmount / 100 }}
                </div>
                <div style="text-align: right; font-size: 12px; color: #c8c9cc">
                  {{ new Date(scope.row.tradeTime).toLocaleDateString() }}
                </div>
              </el-col>
            </el-row>
          </el-card>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { computed, ref } from "vue";
import errorNotification from "@/hooks/errorNotification";
import type { ElTable } from "element-plus";
import { ElNotification } from "element-plus";
import { Trade } from "@/static/entity";

const props = defineProps({
  userInfo: Object,
});
const emits = defineEmits(["getUserInfo"]);

const accountMap = ref<Map<number, string>>(new Map());
const search = ref<string>("");
const tradesRef = ref<InstanceType<typeof ElTable>>();

const trades = computed(() => {
  if (!props.userInfo) return;
  if (search.value === "") {
    return props.userInfo.recentTrade;
  } else {
    let tradesFilter = [];
    for (let i in props.userInfo.recentTrade) {
      let trade = props.userInfo.recentTrade[i];
      if (trade.tradeName && trade.tradeName.includes(search.value as string)) {
        tradesFilter.push(trade);
      } else if (
        trade.remark &&
        trade.remark.includes(search.value as string)
      ) {
        tradesFilter.push(trade);
      } else if (
        getAccountName(trade.accountId as number) &&
        getAccountName(trade.accountId as number)?.includes(
          search.value as string
        )
      ) {
        tradesFilter.push(trade);
      }
    }
    return tradesFilter;
  }
});

function getAccountName(accountId: number): string | null {
  if (accountMap.value.has(accountId)) {
    return accountMap.value.get(accountId) as string;
  } else {
    axios.get(`/account/queryAccount?accountId=${accountId}`).then(
      (response) => {
        accountMap.value.set(accountId, response.data.message);
        return accountMap.value.get(accountId);
      },
      (error) => {
        console.log(error.response);
      }
    );
    return null;
  }
}

function deleteBatch(): void {
  let rows: Trade[] = tradesRef.value?.getSelectionRows();
  if (rows.length === 0) {
    errorNotification("请选择要删除的账单!");
    return;
  }
  axios.post("/trade/delete", rows).then(
    () => {
      emits("getUserInfo");
      ElNotification({
        title: "成功",
        message: "删除成功！",
        type: "success",
      });
    },
    (error) => {
      errorNotification(error.response.data.message);
    }
  );
}

function getTradeColor(tradeType: string): string {
  switch (tradeType) {
    case "1":
      return "#F56C6C";
    case "2":
      return "#67C23A";
    default:
      return "#000000";
  }
}
</script>

<style scoped>
.trade-remark {
  margin-left: 10px;
  color: #c8c9cc;
  font-size: 13px;
}
</style>
