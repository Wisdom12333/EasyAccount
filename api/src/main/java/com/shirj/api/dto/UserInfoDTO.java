package com.shirj.api.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import lombok.*;

import java.util.List;

/**
 * A dto of user info.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户标识
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户账户
     */
    private List<Account> accounts;
    /**
     * 近期账单
     */
    private List<Trade> recentTrade;
    /**
     * 本月支出
     */
    private Integer expend;
    /**
     * 本月收入
     */
    private Integer income;

    @Override
    public String toString() {
        JSONObject jsonObject = (JSONObject) JSON.toJSON(this);
        return jsonObject.toJSONString();
    }
}
