export const accounts = [
  {
    value: "0",
    label: "现金账户",
    children: [
      {
        value: "00",
        label: "储蓄卡",
      },
      {
        value: "01",
        label: "微信钱包",
      },
      {
        value: "02",
        label: "支付宝",
      },
      {
        value: "03",
        label: "现金钱包",
      },
      {
        value: "04",
        label: "余额宝",
      },
      {
        value: "05",
        label: "微信零钱通",
      },
      {
        value: "06",
        label: "其他",
      },
    ],
  },
  {
    value: "1",
    label: "信用账户",
    children: [
      {
        value: "10",
        label: "信用卡",
      },
      {
        value: "11",
        label: "蚂蚁花呗",
      },
      {
        value: "12",
        label: "京东白条",
      },
      {
        value: "13",
        label: "分期付款",
      },
      {
        value: "14",
        label: "其他",
      },
    ],
  },
  {
    value: "2",
    label: "充值账户",
    children: [
      {
        value: "20",
        label: "公交卡",
      },
      {
        value: "21",
        label: "校园卡",
      },
      {
        value: "22",
        label: "公积金",
      },
      {
        value: "23",
        label: "其他",
      },
    ],
  },
  {
    value: "3",
    label: "投资账户",
    children: [
      {
        value: "30",
        label: "股票",
      },
      {
        value: "31",
        label: "基金",
      },
      {
        value: "32",
        label: "数字货币",
      },
      {
        value: "33",
        label: "其他",
      },
    ],
  },
  {
    value: "4",
    label: "债务",
    children: [
      {
        value: "40",
        label: "借入",
      },
      {
        value: "41",
        label: "借出",
      },
    ],
  },
];

export const acMap = new Map([
  ["00", "储蓄卡"],
  ["01", "微信钱包"],
  ["02", "支付宝"],
  ["03", "现金钱包"],
  ["04", "余额宝"],
  ["05", "微信零钱通"],
  ["06", "其他"],
  ["10", "信用卡"],
  ["11", "蚂蚁花呗"],
  ["12", "京东白条"],
  ["13", "分期付款"],
  ["14", "其他"],
  ["20", "公交卡"],
  ["21", "校园卡"],
  ["22", "公积金"],
  ["23", "其他"],
  ["30", "股票"],
  ["31", "基金"],
  ["32", "数字货币"],
  ["33", "其他"],
  ["40", "借入"],
  ["41", "借出"],
]);