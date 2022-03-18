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

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Setter
@Getter
@ToString
@Builder
@Alias("Trade")
@TableName("T_TRADE")
public class Trade extends BaseEntity {

    private static final Long serialVersionUID = 1L;
    @TableId(value = "TRADE_ID", type = IdType.AUTO)
    private Long tradeId;

    @TableField("USER_ID")
    private Long userId;

    @TableField("ACCOUNT_ID")
    private Long accountId;

    @TableField("TRADE_TYPE")
    private String tradeType;

    @TableField("ACCEPT_MONTH")
    private Integer acceptMonth;

    @TableField("TRADE_NAME")
    private String tradeName;

    @TableField("TRADE_ICON")
    private Integer tradeIcon;

    @TableField("TRADE_AMOUNT")
    private Integer tradeAmount;

    @TableField("REMARK")
    private String remark;

    @TableField("TRADE_TIME")
    private LocalDateTime tradeTime;

    @TableField("IS_RE_TRADE")
    private String isReTrade;

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
}
