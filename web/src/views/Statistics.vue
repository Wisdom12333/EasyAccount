<template>
  <div style="width: 75%; padding-top: 20px">
    <el-card>
      <template #header>
        <div style="text-align: center">
          <el-button
            class="button-top"
            plain
            type="text"
            size="large"
            @click="addMonth(-1)"
            :icon="ArrowLeft"
          />
          <el-button plain type="text" @click="chooseMonth = true">
            <template #default>
              <span class="month">{{ month + " 月" }}</span>
              <span class="year">{{ year }}</span>
            </template>
          </el-button>
          <el-button
            class="button-top"
            plain
            type="text"
            size="large"
            @click="addMonth(1)"
            :icon="ArrowRight"
          />
        </div>
      </template>
      <!--      <span style="color: gray; font-size: 14px">月度收支概览</span>-->
      <!--      <el-divider style="margin-top: 10px; margin-bottom: 15px" />-->
      <el-row style="text-align: center">
        <el-col :span="8">
          <p class="text-info">本月结余</p>
          <p class="money-info" style="color: #409eff">
            ￥{{ (data.statInfo.income - data.statInfo.expend) / 100 }}
          </p>
        </el-col>
        <el-col :span="8">
          <p class="text-info">本月支出</p>
          <p class="money-info" style="color: #f56c6c">
            ￥{{ data.statInfo.expend / 100 }}
          </p>
        </el-col>
        <el-col :span="8">
          <p class="text-info">本月收入</p>
          <p class="money-info" style="color: #67c23a">
            ￥{{ data.statInfo.income / 100 }}
          </p>
        </el-col>
      </el-row>
    </el-card>
    <el-card style="margin-top: 30px">
      <template #header> 收支统计 </template>
      <el-tabs v-model="tabName" :stretch="true">
        <el-tab-pane label="支出" name="expend">
          <div style="text-align: center">
            <pie-chart
              title="本月支出"
              :amount="data.statInfo.expend / 100"
              :trades="data.statInfo.monthTrades"
              trade-type="1"
              chart-name="expend"
              :tab-name="tabName"
              style="width: 600px; margin: auto auto"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="收入" name="income">
          <div style="text-align: center">
            <pie-chart
              title="本月收入"
              :amount="data.statInfo.income / 100"
              :trades="data.statInfo.monthTrades"
              trade-type="2"
              chart-name="income"
              :tab-name="tabName"
              style="width: 600px; margin: auto auto"
            />
          </div>
        </el-tab-pane>
        <el-tab-pane label="其他" name="other">
          <div style="text-align: center">
            <pie-chart
              title="其他"
              :amount="data.statInfo.expend / 100"
              :trades="data.statInfo.monthTrades"
              trade-type="3"
              chart-name="other"
              :tab-name="tabName"
              style="width: 600px; margin: auto auto"
            />
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>

    <el-card style="margin-top: 30px">
      <template #header> 月度收支对比 </template>
      <div></div>
    </el-card>
  </div>

  <el-dialog v-model="chooseMonth" width="30%" center>
    <div style="text-align: center; margin: auto auto">
      <el-date-picker v-model="cMonth" type="month" format="YYYY/MM" />
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="chooseMonth = false">取消</el-button>
        <el-button type="primary" @click="changeMonth">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref } from "vue";
import { useStore } from "vuex";
import { ArrowLeft, ArrowRight } from "@element-plus/icons-vue";
import axios from "axios";
import { StatInfo } from "@/static/entity";
import errorNotification from "@/hooks/errorNotification";
import PieChart from "@/components/PieChart.vue";

class Stat {
  statInfo: StatInfo;

  constructor() {
    this.statInfo = new StatInfo();
  }
}

const store = useStore();
const month = ref<number>();
const year = ref<number>();
const tabName = ref<string>("expend");
const chooseMonth = ref<boolean>(false);
const cMonth = ref<Date>();
const data = reactive<Stat>(new Stat());

function addMonth(n: number): void {
  if (!month.value || !year.value) {
    let data = new Date();
    month.value = data.getMonth() + 1;
    year.value = data.getFullYear();
  }
  month.value += n;
  if (month.value > 12) {
    month.value -= 12;
    year.value += 1;
  } else if (month.value === 0) {
    month.value = 12;
    year.value -= 1;
  }
  getStat();
}
function changeMonth() {
  year.value = cMonth.value?.getFullYear();
  month.value = (cMonth.value?.getMonth() as number) + 1;
  getStat();
  chooseMonth.value = false;
}
function getStat() {
  if (!month.value || !year.value) {
    let data = new Date();
    month.value = data.getMonth() + 1;
    year.value = data.getFullYear();
  }
  axios
    .get(
      `/trade/getStat?userId=${store.state.userId}&month=${month.value}&year=${year.value}`
    )
    .then(
      (response) => {
        data.statInfo = response.data.result.stat;
        if (!data.statInfo.expend) data.statInfo.expend = 0;
        if (!data.statInfo.income) data.statInfo.income = 0;
        if (!data.statInfo.transfer) data.statInfo.transfer = 0;
      },
      (error) => {
        errorNotification(error.response.data.message);
      }
    );
}

onMounted(() => {
  let data = new Date();
  month.value = data.getMonth() + 1;
  year.value = data.getFullYear();
  getStat();
});
</script>

<style scoped>
.month {
  font-size: 18px;
  font-weight: bold;
  padding-right: 5px;
  color: black;
}
.year {
  color: gray;
}
.button-top {
  color: gray;
  font-size: 20px;
  padding-right: 20px;
  padding-left: 20px;
}
.text-info {
  color: gray;
  margin-bottom: 5px;
}
.money-info {
  margin-top: 0;
  font-weight: bold;
}
</style>
