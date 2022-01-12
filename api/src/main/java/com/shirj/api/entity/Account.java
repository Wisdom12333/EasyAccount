package com.shirj.api.entity;

import com.shirj.api.core.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
@Alias("Account")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long accountId;

    private String accountName;

    private long userId;

    private String tag;

    private long balance;

    private String remark;

    private String isTotal;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String rsrvStr1;

    private String rsrvStr2;

    private String rsrvStr3;

    private String rsrvStr4;

    private String rsrvStr5;

}
