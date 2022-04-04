package com.shirj.pub.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * {@code Account} const.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
public class AccountConst {

    @AllArgsConstructor
    @Getter
    public enum ACCOUNT_TYPE {
        //现金账户
        CASH("0"),
        //信用账户
        CREDIT("1"),
        //充值账户
        RECHARGE("2"),
        //投资账户
        INVEST("3"),
        //债务
        DEBT("4");

        private final String value;
    }

    @AllArgsConstructor
    @Getter
    public enum CASH {
        //储蓄卡
        DEBIT_CARD("00"),
        //微信钱包
        WECHAT_WALLET("01"),
        //支付宝
        ALIPAY("02"),
        //现金钱包
        CASH_WALLET("03"),
        //余额宝
        YUE_BAO("04"),
        //微信零钱通
        WECHAT_CHANGE("05"),
        //其他
        OTHER("06");

        private final String value;
    }

    @AllArgsConstructor
    @Getter
    public enum CREDIT {
        //信用卡
        CREDIT_CARD("10"),
        //蚂蚁花呗
        ANT_FLOWER("11"),
        //京东白条
        JINGDONG_WHITE("12"),
        //分期贷款
        INSTALLMENT_LOAN("13"),
        //其他
        OTHER("14");

        private final String value;
    }

    @AllArgsConstructor
    @Getter
    public enum RECHARGE {
        //公交卡
        BUS_CARD("20"),
        //校园卡
        SCHOOL_CARD("21"),
        //公积金
        PROVIDENT_FUND("22"),
        //其他
        OTHER("23");

        private final String value;
    }

    @AllArgsConstructor
    @Getter
    public enum INVEST {
        //股票
        STOCK("30"),
        //基金
        FUND("31"),
        //数字货币
        DIGITAL_CURRENCY("32"),
        //其他
        OTHER("33");

        private final String value;
    }

    @AllArgsConstructor
    @Getter
    public enum DEBT {
        //借入
        BORROW("40"),
        //借出
        LEND("41");

        private final String value;
    }
}
