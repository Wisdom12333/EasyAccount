/**
 * The default expend menu.
 */
export const expendMenu = [
  {
    label: "餐饮",
    value: "餐饮",
  },
  {
    label: "零食",
    value: "零食",
  },
  {
    label: "购物",
    value: "购物",
    children: [
      {
        label: "数码",
        value: "数码",
      },
      {
        label: "鞋服",
        value: "鞋服",
      },
      {
        label: "电器",
        value: "电器",
      },
      {
        label: "文教",
        value: "文教",
      },
      {
        label: "家居",
        value: "家居",
      },
      {
        label: "玩具",
        value: "玩具",
      },
      {
        label: "App",
        value: "App",
      },
    ],
  },
  {
    label: "娱乐",
    value: "娱乐",
    children: [
      {
        label: "电影",
        value: "电影",
      },
      {
        label: "游戏",
        value: "游戏",
      },
    ],
  },
  {
    label: "住房",
    value: "住房",
    children: [
      {
        label: "房租",
        value: "房租",
      },
      {
        label: "水费",
        value: "水费",
      },
      {
        label: "电费",
        value: "电费",
      },
      {
        label: "物业费",
        value: "物业费",
      },
    ],
  },
  {
    label: "交通",
    value: "交通",
  },
  {
    label: "汽车",
    value: "汽车",
    children: [
      {
        label: "停车费",
        value: "停车费",
      },
      {
        label: "加油",
        value: "加油",
      },
      {
        label: "维修",
        value: "维修",
      },
    ],
  },
  {
    label: "学习",
    value: "学习",
  },
  {
    label: "医疗",
    value: "医疗",
  },
];
/**
 * The default income menu.
 */
export const incomeMenu = [
  {
    label: "薪资",
    value: "薪资",
  },
  {
    label: "生活费",
    value: "生活费",
  },
  {
    label: "红包",
    value: "红包",
  },
  {
    label: "外快",
    value: "外快",
  },
  {
    label: "奖金",
    value: "奖金",
  },
  {
    label: "投资",
    value: "投资",
  },
];

export const budgetMenu = [
  {
    label: "餐饮",
    value: "餐饮",
  },
  {
    label: "零食",
    value: "零食",
  },
  {
    label: "购物",
    value: "购物",
  },
  {
    label: "娱乐",
    value: "娱乐",
  },
  {
    label: "住房",
    value: "住房",
  },
  {
    label: "交通",
    value: "交通",
  },
  {
    label: "汽车",
    value: "汽车",
  },
  {
    label: "学习",
    value: "学习",
  },
  {
    label: "医疗",
    value: "医疗",
  },
];

export const budgetMap = new Map<string, string>([
  ["餐饮", "餐饮"],
  ["零食", "零食"],
  ["购物", "购物"],
  ["数码", "购物"],
  ["鞋服", "购物"],
  ["电器", "购物"],
  ["文教", "购物"],
  ["家居", "购物"],
  ["玩具", "购物"],
  ["App", "购物"],
  ["娱乐", "娱乐"],
  ["电影", "娱乐"],
  ["游戏", "娱乐"],
  ["住房", "住房"],
  ["房租", "住房"],
  ["水费", "住房"],
  ["电费", "住房"],
  ["物业费", "住房"],
  ["交通", "交通"],
  ["汽车", "汽车"],
  ["停车费", "汽车"],
  ["加油", "汽车"],
  ["学习", "学习"],
  ["医疗", "医疗"],
  ["薪资", "薪资"],
  ["生活费", "生活费"],
  ["红包", "红包"],
  ["外快", "外快"],
  ["奖金", "奖金"],
  ["投资", "投资"],
  ["餐饮", "餐饮"],
  ["转账", "转账"],
]);
