package com.shirj.api.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

/**
 * A dto of user statistic.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 本月支出
     */
    private Integer expend;
    /**
     * 本月收入
     */
    private Integer income;
    /**
     * 本月转账
     */
    private Integer transfer;
    /**
     * 本月账单
     */
    private List<Trade> monthTrades;

    @Override
    public String toString() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
        return jsonObject.toJSONString();
    }
}
