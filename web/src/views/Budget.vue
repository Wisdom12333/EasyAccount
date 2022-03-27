<template>
  <el-card style="text-align: center">
    <div class="budget-head">
      <span> 本月预算 </span>
      <el-button type="text" :plain="true" size="large" @click="setBudget">
        <el-icon :size="25">
          <edit></edit>
        </el-icon>
      </el-button>
    </div>
    <el-progress
      type="dashboard"
      :percentage="80"
      status="exception"
      :stroke-width="12"
      stroke-linecap="round"
      width="300"
    >
      <template #default="{ percentage }">
        <template v-if="false">
          <span class="percentage-value">{{ percentage }}%</span>
          <span class="percentage-label">Progressing</span>
        </template>
        <template v-else>
          <span>还没有设置预算!</span>
        </template>
      </template>
    </el-progress>
  </el-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { useStore } from "vuex";
import { onMounted } from "vue";
import { ElMessageBox, ElMessage, ElNotification } from "element-plus";
import errorNotification from "@/hooks/errorNotification";

const store = useStore();

const getBudgetInfo = () => {
  axios.get(`/user/getBudget?userId=${store.state.userId}`).then();
};
function setBudget() {
  ElMessageBox.prompt("请设置当月预算", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    inputPattern: /^(0|[1-9]+[0-9]*)(.[0-9]{0,2})?$/,
    inputErrorMessage: "请输入非负两位小数!",
  })
    .then(({ value }) => {
      axios.get(`${value}`).then(
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
.el-card {
  width: 75%;
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
