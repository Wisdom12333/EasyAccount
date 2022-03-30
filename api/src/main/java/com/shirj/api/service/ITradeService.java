package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Trade;

import java.util.List;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
public interface ITradeService extends IBaseService<Trade> {

    /**
     * Book and update Account info.
     *
     * @param entity a new trade
     * @return true if book success.
     */
    @Override
    boolean save(Trade entity);

    ResultDTO delete(List<Trade> trades);

    @Override
    boolean removeById(Trade trade);

    ResultDTO getStat(Long userId, Integer year, Integer month);
}
