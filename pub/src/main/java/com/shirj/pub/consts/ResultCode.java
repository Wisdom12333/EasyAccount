package com.shirj.pub.consts;

import lombok.Getter;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
public enum ResultCode {
    //成功
    SUCCESS("0"),
    //数据错误
    ERROR_DATA("1"),
    //服务异常
    SVC_EXCEPTION("2");

    private final String value;

    ResultCode(String value) {
        this.value = value;
    }

    public final boolean equals(ResultCode resultCode) {
        return value.equals(resultCode.getValue());
    }
}