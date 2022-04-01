<template>
  <div ref="chart" style="width: 100%; height: 500px"></div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref, watch } from "vue";
import * as echarts from "echarts";
import { useStore } from "vuex";
import axios from "axios";
import errorNotification from "@/hooks/errorNotification";

const chartExpendData = ref<number[]>([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
const chartIncomeData = ref<number[]>([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]);
const props = defineProps({
  year: Number,
});
const store = useStore();
const chart = ref<HTMLElement>();
const charts = ref();
const year = computed(() => {
  let y = new Date().getFullYear();
  return props.year ? props.year : y;
});
watch(year, () => {
  getInfo();
});

function init() {
  charts.value = echarts.init(chart.value as HTMLElement);
  setOp();
}
function setOp() {
  if (charts.value) {
    charts.value.setOption(
      {
        tooltip: {
          show: true,
          trigger: "axis",
        },
        legend: {
          data: ["支出", "收入"],
        },
        toolbox: {
          show: false,
          feature: {
            dataView: { show: true, readOnly: false },
            magicType: { show: true, type: ["line", "bar"] },
            restore: { show: true },
            saveAsImage: { show: true },
          },
        },
        calculable: true,
        xAxis: [
          {
            type: "category",
            // prettier-ignore
            data: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月",],
          },
        ],
        yAxis: [
          {
            type: "value",
            show: false,
          },
        ],
        series: [
          {
            name: "支出",
            type: "bar",
            data: chartExpendData.value,
            itemStyle: {
              color: "#F56C6C",
              borderRadius: [30, 30, 30, 30],
            },
          },
          {
            name: "收入",
            type: "bar",
            data: chartIncomeData.value,
            itemStyle: {
              color: "#67C23A",
              borderRadius: [30, 30, 30, 30],
            },
          },
        ],
      },
      true
    );
  }
}
function getInfo() {
  axios
    .get(
      `/trade/getMonthContrast?userId=${store.state.userId}&year=${year.value}`
    )
    .then(
      (response) => {
        let monthContrast = response.data.result.monthContrast;
        for (let i in monthContrast) {
          if ("1" === monthContrast[i].tradeType) {
            chartExpendData.value[monthContrast[i].acceptMonth - 1] =
              monthContrast[i].amount / 100;
          } else if ("2" === monthContrast[i].tradeType) {
            chartIncomeData.value[monthContrast[i].acceptMonth - 1] =
              monthContrast[i].amount / 100;
          }
        }
        setOp();
      },
      (error) => {
        errorNotification(error.response.data.message);
      }
    );
}
onMounted(() => {
  getInfo();
  init();
});
</script>

<style scoped></style>
