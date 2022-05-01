<template>
  <el-container>
    <el-aside style="width: 50%"
      ><el-card style="text-align: center; margin-top: 20px">
        <div class="budget-head">
          <span> 本月预算 </span>
          <el-button
            type="text"
            :plain="true"
            size="large"
            @click="setBudget(0, '')"
          >
            <el-icon :size="25">
              <edit />
            </el-icon>
          </el-button>
        </div>
        <el-progress
          type="dashboard"
          :percentage="totalPercent"
          :status="status(totalPercent)"
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
              @click="addBudgetForm = true"
            >
              <el-icon :size="25">
                <circle-plus />
              </el-icon>
            </el-button>
          </div>
        </template>
        <el-table :data="data.budgetInfo.budgets" style="max-height: 100%">
          <el-table-column min-width="100">
            <template #default="scope">
              <div style="font-weight: bold">{{ scope.row.budgetName }}</div>
              <div>总预算: {{ scope.row.budgetAmount / 100 }}</div>
            </template>
          </el-table-column>
          <el-table-column min-width="300">
            <template #default="scope">
              <el-progress
                :text-inside="true"
                :stroke-width="26"
                :percentage="
                  getPercent(scope.row) > 100 ? 100 : getPercent(scope.row)
                "
                :status="status(getPercent(scope.row))"
              >
                <span style="padding-right: 5px"
                  >{{ getPercent(scope.row) }} %</span
                >
                <span v-show="getPercent(scope.row) > 100"
                  >本月预算已超出!</span
                >
              </el-progress>
            </template>
          </el-table-column>
          <el-table-column min-width="70" align="right">
            <template #default="scope">
              <el-button
                round
                @click="setBudget(1, scope.row.budgetName)"
                size="small"
                >编辑</el-button
              >
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </el-main>
  </el-container>

  <!-- 添加分类预算 -->
  <el-dialog v-model="addBudgetForm" title="添加分类预算" width="30%" center>
    <div style="text-align: center">
      <div style="width: 320px; margin: auto auto">
        <el-form :model="newBudget" ref="newBudgetForm">
          <el-form-item label="预算类型">
            <el-cascader
              :options="budgetMenu"
              v-model="budgetType"
              placeholder="请选择预算类型"
            />
          </el-form-item>
          <el-form-item label="预算额度">
            <el-input-number
              v-model="newBudget.budgetAmount"
              :precision="2"
              :min="0"
              :controls="false"
            />
          </el-form-item>
        </el-form>
      </div>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="addBudgetForm = false">取消</el-button>
        <el-button type="primary" @click="afterSubmit(newBudgetForm)"
          >确定</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import axios from "axios";
import { useStore } from "vuex";
import { computed, onMounted, reactive, ref } from "vue";
import { ElMessageBox, ElMessage, ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";
import { Budget, BudgetInfo, Trade, UserInfo } from "@/static/entity";
import Trades from "@/components/Trades.vue";
import { budgetMap, budgetMenu } from "@/static/trade";
import type { FormInstance } from "element-plus";

class Info {
  budgetInfo: BudgetInfo;

  constructor() {
    this.budgetInfo = new BudgetInfo();
  }
}

const store = useStore();
const addBudgetForm = ref<boolean>(false);
const budgetType = ref<string[]>();
const newBudgetForm = ref<FormInstance>();
const data = reactive<Info>(new Info());
const newBudget = reactive<Budget>(new Budget(store.state.userId));
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
const status = (percent: number): string => {
  if (percent >= 0 && percent <= 50) {
    return "success";
  } else if (percent <= 80) {
    return "warning";
  } else return "exception";
};

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

function afterSubmit(form: FormInstance): boolean {
  if (budgetType.value) {
    newBudget.budgetName = budgetType.value[0];
  } else {
    ElMessage({
      message: "请选择预算类型!",
      type: "warning",
    });
    return false;
  }
  if (!newBudget.budgetAmount) {
    ElMessage({
      message: "请输入预算额度!",
      type: "warning",
    });
    return false;
  }
  newBudget.budgetAmount *= 100;
  addBudget(newBudget as Budget);
  addBudgetForm.value = false;
  form.resetFields();
  return true;
}
function setBudget(type: number, budgetName: string) {
  let message = type === 0 ? "当月" : budgetName; //0为总预算,1为分类预算
  ElMessageBox.prompt(`请设置${message}预算`, "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: /^(0|[1-9]+[0-9]*)(.[0-9]{0,2})?$/,
    inputErrorMessage: "请输入非负两位小数!",
  })
    .then(({ value }) => {
      let budget = new Budget(store.state.userId);
      budget.budgetName = budgetName;
      budget.budgetAmount = parseFloat(value) * 100;
      addBudget(budget);
    })
    .catch(() => {
      ElMessage({
        type: "info",
        message: "取消设置",
      });
    });
}
function addBudget(budget: Budget) {
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
}
function getPercent(budget: Budget): number {
  if (!budget.budgetAmount) {
    return 0;
  }
  let name = budget.budgetName;
  let trades = data.budgetInfo.trades?.filter((x) => {
    return budgetMap.get(x.tradeName as string) === name;
  });
  let expend = 0;
  if (trades) {
    trades.forEach((x) => {
      expend += x.tradeAmount as number;
    });
  }
  return parseFloat(((expend * 100) / budget.budgetAmount).toFixed(2));
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
