export class User {
  userId?: number;
  username?: string;
  password?: string;
  eMail?: string;
  createTime?: Date;
  updateTime?: Date;
  removeTag: string;

  constructor() {
    this.removeTag = "0";
  }
}

export class UserInfo {
  userId?: number;
  username?: string;
  accounts: Account[];
  recentTrade: Trade[];
  expend: number;
  income: number;

  constructor() {
    this.accounts = [];
    this.recentTrade = [];
    this.expend = 0.0;
    this.income = 0.0;
  }
}

export class Account {
  accountId?: number;
  accountName?: string;
  type?: string[];
  tag?: string;
  balance?: number;
  remark?: string;
  isTotal?: string;
  removeTag: string;
  userId?: string;
  tagName?: string;
  rsrvStr1?: string;
  rsrvStr2?: string;

  constructor() {
    this.removeTag = "0";
  }
}

export class Trade {
  tradeId?: number;
  userId?: number;
  accountId?: number;
  tradeType?: string;
  acceptMonth?: number;
  tradeTag?: [];
  tradeName?: string;
  tradeAmount?: number;
  remark?: string;
  tradeTime?: Date;
  isReTrade?: string;
  rsrvStr1?: string;
  rsrvStr2?: string;
}

export class Budget {
  budgetId?: number;
  userId: number;
  budgetName?: string;
  budgetAmount?: number;
  createTime?: Date;

  constructor(userId: number) {
    this.userId = userId;
  }
}

export class BudgetInfo {
  userId?: number;
  totalBudget?: number;
  expend?: number;
  trades?: Trade[];
  budgets?: Budget[];
}
