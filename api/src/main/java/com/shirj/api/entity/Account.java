package com.shirj.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("T_ACCOUNT")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long accountId;
    @TableField("ACCOUNT_NAME")
    private String accountName;
    @TableField("USER_ID")
    private long userId;
    @TableField("TAG")
    private String tag;
    @TableField("BALANCE")
    private long balance;
    @TableField("REMARK")
    private String remark;
    @TableField("IS_TOTAL")
    private String isTotal;
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;
    @TableField("REMOVE_TAG")
    private String removeTag;
    @TableField("RSRV_STR1")
    private String rsrvStr1;
    @TableField("RSRV_STR2")
    private String rsrvStr2;
    @TableField("RSRV_STR3")
    private String rsrvStr3;
    @TableField("RSRV_STR4")
    private String rsrvStr4;
    @TableField("RSRV_STR5")
    private String rsrvStr5;
    @TableField("RSRV_TIME1")
    private LocalDateTime rsrvTime1;
    @TableField("RSRV_TIME2")
    private LocalDateTime rsrvTime2;
    @TableField("RSRV_TIME3")
    private LocalDateTime rsrvTime3;

}
