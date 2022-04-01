<template>
  <div>
    <div
      name="pieChartDiv"
      ref="chart"
      style="width: 600px; height: 400px"
    ></div>
    <div style="height: 20px" />
    <el-space :size="20" direction="vertical" style="width: 600px">
      <div v-for="item in data" :key="item.name">
        <div
          style="float: left; text-align: left; width: 300px; font-weight: bold"
        >
          <div style="margin-bottom: 5px">
            {{ item.name }} &nbsp;
            {{ ((item.value * 100) / amount).toFixed(0) }} %
          </div>
          <el-progress
            :percentage="parseInt(((item.value * 100) / amount).toFixed(0))"
            :show-text="false"
          />
        </div>
        <div
          :class="chartName"
          style="float: right; text-align: right; width: 100px"
        >
          ￥ {{ item.value }}
        </div>
      </div>
    </el-space>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, onUpdated, ref, watch } from "vue";
import * as echarts from "echarts";
import { Trade } from "@/static/entity";
import { budgetMap } from "@/static/trade";

class Data {
  value: number;
  name: string;

  constructor(value: number, name: string) {
    this.value = value;
    this.name = name;
  }
}
const props = defineProps({
  title: String,
  tradeType: String,
  chartName: String,
  tabName: String,
  amount: Number,
  trades: Array,
});
const chart = ref<HTMLElement>();
const charts = ref();
const hasInit = ref<boolean>(false);
const data = computed(() => {
  if (props.trades) {
    let arr: Data[] = [];
    let map = new Map<string, number>();
    for (let i in props.trades) {
      let trade = props.trades[i] as Trade;
      if (trade.tradeType === props.tradeType) {
        let tradeName = budgetMap.get(trade.tradeName as string);
        if (tradeName && map.has(tradeName) && trade.tradeAmount) {
          map.set(
            tradeName,
            (map.get(tradeName) as number) + trade.tradeAmount
          );
        } else if (tradeName && trade.tradeAmount) {
          map.set(tradeName, trade.tradeAmount);
        }
      }
    }
    map.forEach((value, key) => {
      let x = { value: value / 100, name: key };
      arr.push(x);
    });
    return arr;
  } else return [];
});
const tabName = computed(() => {
  return props.tabName;
});
watch(tabName, (value) => {
  if (value === props.chartName) {
    setTimeout(() => {
      getChart();
      setOp();
    }, 300);
  }
});

function getChart() {
  //初始化
  if (!hasInit.value) {
    charts.value = echarts.init(chart.value as HTMLElement);
    hasInit.value = true;
  }
}
function setOp() {
  if (hasInit.value) {
    charts.value.setOption({
      tooltip: {
        trigger: "item",
      },
      legend: {
        top: "5%",
        left: "center",
      },
      series: [
        {
          name: "Trade Form",
          type: "pie",
          radius: ["50%", "70%"],
          width: 600,
          height: 400,
          avoidLabelOverlap: false,
          label: {
            show: true,
            position: "center",
            color: "#4c4a4a",
            formatter:
              "{total|" +
              props.title +
              "}" +
              "\n\r" +
              "{active| ￥" +
              props.amount +
              "}",
            rich: {
              total: {
                fontSize: 14,
                color: "gray",
              },
              active: {
                fontSize: 18,
                color: "black",
                lineHeight: 30,
                fontWeight: "bold",
              },
            },
          },
          emphasis: {
            label: {
              show: false,
            },
          },
          labelLine: {
            show: false,
          },
          data: data.value,
        },
      ],
    });
  }
}

onMounted(() => {
  if (props.trades && props.chartName === props.tabName) {
    getChart();
    setOp();
  }
});
onUpdated(() => {
  if (props.tabName === props.chartName) {
    setTimeout(() => {
      getChart();
      setOp();
    }, 300);
  }
});
</script>

<style scoped>
.income {
  color: #67c23a;
}
.expend {
  color: #f56c6c;
}
.transfer {
  color: black;
}
</style>
