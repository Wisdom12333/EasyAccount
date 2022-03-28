<template>
  <el-container>
    <el-aside style="width: 40%"
      ><el-card style="text-align: center; margin-top: 20px">
        <div class="budget-head">
          <span> 本月预算 </span>
          <el-button type="text" :plain="true" size="large" @click="setBudget">
            <el-icon :size="25">
              <edit />
            </el-icon>
          </el-button>
        </div>
        <el-progress
          type="dashboard"
          :percentage="totalPercent"
          :status="totalStatus"
          :stroke-width="12"
          stroke-linecap="round"
          :width="300"
        >
          <template #default>
            <template v-if="data.budgetInfo.totalBudget">
              <span class="percentage-value"
                >￥
                {{
                  (data.budgetInfo.totalBudget - data.budgetInfo.expend) / 100
                }}</span
              >
              <span class="percentage-label">本月预算额度剩余</span>
            </template>
            <template v-else>
              <span>还没有设置预算!</span>
            </template>
          </template>
        </el-progress>
        <div class="budget-footer">
          <span>已花费 ￥{{ data.budgetInfo.expend / 100 }}</span>
          <span>总预算 ￥{{ data.budgetInfo.totalBudget / 100 }}</span>
        </div>
      </el-card>
      <el-divider />
      <Trades :user-info="userInfoBudget"></Trades>
    </el-aside>
    <el-main>
      <el-card>
        <template #header>
          <div class="card-header">
            <span style="font-weight: bold">分类预算</span>
            <el-button
              type="text"
              :plain="true"
              size="large"
              @click="setBudget"
            >
              <el-icon :size="25">
                <circle-plus />
              </el-icon>
            </el-button>
          </div>
        </template>
        <el-table :data="data.budgetInfo.budgets" style="max-height: 100%">
          <el-table-column prop="budgetName"></el-table-column>
        </el-table>
      </el-card>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import axios from "axios";
import { useStore } from "vuex";
import { computed, onMounted, reactive } from "vue";
import { ElMessageBox, ElMessage, ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";
import { Budget, BudgetInfo, Trade, UserInfo } from "@/static/entity";
import Trades from "@/components/Trades.vue";

class Info {
  budgetInfo: BudgetInfo;

  constructor() {
    this.budgetInfo = new BudgetInfo();
  }
}

const store = useStore();
const data = reactive<Info>(new Info());
const userInfoBudget = computed(() => {
  let info = new UserInfo();
  if (data.budgetInfo.trades) {
    info.recentTrade = data.budgetInfo.trades as Trade[];
  }
  return info;
});
const totalPercent = computed(() => {
  if (data.budgetInfo.totalBudget && data.budgetInfo.expend) {
    if (data.budgetInfo.expend > data.budgetInfo.totalBudget) {
      return 100;
    } else return (data.budgetInfo.expend * 100) / data.budgetInfo.totalBudget;
  } else {
    return 0;
  }
});
const totalStatus = computed(() => {
  if (totalPercent.value >= 0 && totalPercent.value <= 50) {
    return "success";
  } else if (totalPercent.value <= 80) {
    return "warning";
  } else return "exception";
});

const getBudgetInfo = () => {
  axios.get(`/budget/getInfo?userId=${store.state.userId}`).then(
    (response) => {
      data.budgetInfo = response.data.result.budgetInfo;
      if (!data.budgetInfo.expend) {
        data.budgetInfo.expend = 0.0;
      }
    },
    (error) => {
      errorNotification(error.response.data.message);
    }
  );
};

function addBudget() {
  return false;
}
function setBudget() {
  ElMessageBox.prompt("请设置当月预算", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: /^(0|[1-9]+[0-9]*)(.[0-9]{0,2})?$/,
    inputErrorMessage: "请输入非负两位小数!",
  })
    .then(({ value }) => {
      let budget = new Budget();
      budget.budgetName = "";
      budget.userId = store.state.userId;
      budget.budgetAmount = parseFloat(value) * 100;
      axios.post("/budget/addBudget", budget).then(
        () => {
          ElNotification({
            type: "success",
            message: "设置成功!",
          });
          getBudgetInfo();
        },
        (error) => {
          errorNotification(error.response.data.message);
        }
      );
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消设置",
      });
    });
}

onMounted(getBudgetInfo);
</script>

<style scoped>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.budget-head {
  font-size: 18px;
  font-weight: bold;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}
.budget-footer {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.percentage-value {
  display: block;
  margin-top: 5px;
  font-size: 28px;
  font-weight: bold;
}
.percentage-label {
  display: block;
  margin-top: 10px;
  font-size: 23px;
  font-weight: bolder;
}
</style>
