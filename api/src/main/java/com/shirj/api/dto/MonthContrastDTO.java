package com.shirj.api.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shirj.api.core.entity.BaseEntity;
import lombok.*;

/**
 * A dto of month contrast info.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthContrastDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 该月账单金额
     */
    private Long amount;
    /**
     * 账单类型
     */
    private String tradeType;
    /**
     * 账单月份
     */
    private Integer acceptMonth;

    @Override
    public String toString() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
        return jsonObject.toJSONString();
    }
}
