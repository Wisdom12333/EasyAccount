package com.shirj.pub.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Common const.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public class CommConst {

    /**
     * 有效
     */
    public static final String VALID = "0";
    /**
     * 失效
     */
    public static final String INVALID = "1";

    /**
     * NULL
     */
    public static final String NULL = "null";

    @Getter
    @AllArgsConstructor
    public enum TRADE_TYPE {
        //支出
        EXPEND("1"),
        //收入
        INCOME("2"),
        //转账
        TRANSFER("3");

        private String value;

        public boolean equals(String str){
            return value.equals(str);
        }
    }
}
