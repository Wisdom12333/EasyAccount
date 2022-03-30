package com.shirj.api.dto;

import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Budget;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private Long totalBudget;

    private Integer expend;

    private List<Trade> trades;

    private List<Budget> budgets;

    public BudgetInfoDTO(Long userId){
        this.userId = userId;
    }
}
