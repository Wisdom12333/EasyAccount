package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.entity.Trade;

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
}
