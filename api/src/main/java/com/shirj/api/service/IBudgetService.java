package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Budget;

public interface IBudgetService extends IBaseService<Budget> {

    ResultDTO getBudgetInfo(Long userId);

    void saveBudget(Budget budget);
}
