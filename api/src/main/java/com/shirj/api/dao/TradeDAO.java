package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Trade;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface TradeDAO extends BaseDAO<Trade> {

    /**
     * Get statistic of current month.
     *
     * @param userId the userId
     * @return the statistic include expend and income
     */
    List<Map<String, Object>> getStat(@NonNull Long userId);
}
