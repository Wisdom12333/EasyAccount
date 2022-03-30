package com.shirj.api.dto;

import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer expend;

    private Integer income;

    private Integer transfer;

    private List<Trade> monthTrades;
}
