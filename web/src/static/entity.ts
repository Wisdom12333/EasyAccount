export class user {
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

export class userInfo {
  userId?: number;
  username?: string;
  accounts: account[];
  recentTrade: trade[];
  expend: number;
  income: number;

  constructor() {
    this.accounts = [];
    this.recentTrade = [];
    this.expend = 0.0;
    this.income = 0.0;
  }
}

export class account {
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

export class trade {
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