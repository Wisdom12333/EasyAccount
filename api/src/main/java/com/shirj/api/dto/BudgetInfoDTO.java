package com.shirj.api.dto;

import com.shirj.api.entity.Budget;
import com.shirj.api.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetInfoDTO {

    private Long userId;

    private Long totalBudget;

    private Integer expend;

    private List<Trade> trades;

    private List<Budget> budgets;

    public BudgetInfoDTO(Long userId){
        this.userId = userId;
    }
}
