<template>
  <el-table ref="tradesRef" :data="trades" max-height="450">
    <el-table-column type="selection" width="55" />
    <el-table-column label="账单信息" style="padding: 0; width: 75%">
      <template #header>
        <el-row :gutter="30">
          <el-col :span="16">
            <el-input v-model="search" size="small" placeholder="" />
          </el-col>
          <el-col :span="8" style="text-align: right">
            <el-button type="primary" size="small" style="margin-right: 5px"
              >编辑</el-button
            >
            <el-button type="danger" size="small">批量删除</el-button>
          </el-col>
        </el-row>
      </template>
      <template #default="scope">
        <el-card
          body-style="height: 60px"
          style="height: 60px; --el-card-padding: 10px"
        >
          <el-row>
            <el-col :span="16" style="">
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
  <el-button @click="test">try it </el-button>
</template>

<script setup lang="ts">
import axios from "axios";
import { computed, ref, defineProps } from "vue";
import { userInfo } from "@/static/entity";

const props = defineProps({
  userInfo: userInfo,
});

const accountMap = ref<Map<number, string>>(new Map());
const search = ref<string>();

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
function test() {
  console.log();
}
</script>

<style scoped>
.el-table {
  width: 75%;
}
.trade-remark {
  margin-left: 10px;
  color: #c8c9cc;
  font-size: 13px;
}
</style>
