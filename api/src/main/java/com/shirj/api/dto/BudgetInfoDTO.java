package com.shirj.api.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Budget;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

/**
 * A dto of user budget info.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户标识
     */
    private Long userId;
    /**
     * 该月总预算
     */
    private Long totalBudget;
    /**
     * 该月支出
     */
    private Integer expend;
    /**
     * 该月账单
     */
    private List<Trade> trades;
    /**
     * 子类预算
     */
    private List<Budget> budgets;

    public BudgetInfoDTO(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
        return jsonObject.toJSONString();
    }
}
