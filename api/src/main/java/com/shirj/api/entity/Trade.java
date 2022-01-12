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
@Alias("Trade")
public class Trade extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long tradeId;

    private long userId;

    private long accountId;

    private String tradeType;

    private int acceptMonth;

    private String tradeName;

    private int tradeIcon;

    private int tradeAmount;

    private String remark;

    private LocalDateTime tradeTime;

    private String isReTrade;
}
