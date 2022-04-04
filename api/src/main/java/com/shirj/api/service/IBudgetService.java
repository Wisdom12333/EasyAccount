package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Budget;

/**
 * The interface for {@code Budget} service.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IBudgetService extends IBaseService<Budget> {

    /**
     * Get a user's budget info({@code BudgetInfoDTO}).
     *
     * @param userId user's id
     * @return a result includes budget info
     */
    ResultDTO getBudgetInfo(Long userId);

    /**
     * Save a budget record.
     *
     * @param budget the budget
     */
    void saveBudget(Budget budget);
}
