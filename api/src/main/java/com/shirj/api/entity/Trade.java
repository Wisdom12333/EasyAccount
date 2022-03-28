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
    /**
     * 账单标识
     */
    @TableId(value = "TRADE_ID", type = IdType.AUTO)
    private Long tradeId;
    /**
     * 用户标识
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 账户标识
     */
    @TableField("ACCOUNT_ID")
    private Long accountId;
    /**
     * 账单类型
     */
    @TableField("TRADE_TYPE")
    private String tradeType;
    /**
     * 入账月份
     */
    @TableField("ACCEPT_MONTH")
    private Integer acceptMonth;
    /**
     * 账单名
     */
    @TableField("TRADE_NAME")
    private String tradeName;
    /**
     * 账单图标
     */
    @TableField("TRADE_ICON")
    private Integer tradeIcon;
    /**
     * 账单金额
     */
    @TableField("TRADE_AMOUNT")
    private Integer tradeAmount;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    /**
     * 入账时间
     */
    @TableField("TRADE_TIME")
    private LocalDateTime tradeTime;
    /**
     * 是否重复账单
     */
    @TableField("IS_RE_TRADE")
    private String isReTrade;
    /**
     * 附属字段1
     */
    @TableField("RSRV_STR1")
    private String rsrvStr1;
    /**
     * 附属字段2
     */
    @TableField("RSRV_STR2")
    private String rsrvStr2;
    /**
     * 附属字段3
     */
    @TableField("RSRV_STR3")
    private String rsrvStr3;
    /**
     * 附属字段4
     */
    @TableField("RSRV_STR4")
    private String rsrvStr4;
    /**
     * 附属字段5
     */
    @TableField("RSRV_STR5")
    private String rsrvStr5;
}
