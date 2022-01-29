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

    private static final long serialVersionUID = 1L;
    @TableId(value = "TRADE_ID", type = IdType.AUTO)
    private Long tradeId;
    @TableField("USER_ID")
    private long userId;
    @TableField("ACCOUNT_ID")
    private long accountId;
    @TableField("TRADE_TYPE")
    private String tradeType;
    @TableField("ACCEPT_MONTH")
    private int acceptMonth;
    @TableField("TRADE_NAME")
    private String tradeName;
    @TableField("TRADE_ICON")
    private int tradeIcon;
    @TableField("TRADE_AMOUNT")
    private int tradeAmount;
    @TableField("REMARK")
    private String remark;
    @TableField("TRADE_TIME")
    private LocalDateTime tradeTime;
    @TableField("IS_RE_TRADE")
    private String isReTrade;
}
