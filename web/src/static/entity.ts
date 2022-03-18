export class account {
    accountId: number;
    accountName: string;
    type: string[];
    tag: string;
    balance: number;
    remark: string;
    isTotal: string;
    removeTag: string;
    userId: string;
    tagName: string;
    rsrvStr1: number;
    rsrvStr2: number;

    constructor() {
        this.removeTag = "0";
    }
}

export class trade {
    tradeId: number;
    userId: number;
    accountId: number;
    tradeTag: [];
    tradeName: string;
    tradeType: string;
    tradeAmount: number;
    remark: string;
    tradeTime: Date;
    isReTrade: string;
    rsrvStr1: number;
    rsrvStr2: number;
}