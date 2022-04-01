package com.shirj.api.dto;

import com.shirj.api.core.entity.BaseEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthContrastDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long amount;

    private String tradeType;

    private Integer acceptMonth;
}
