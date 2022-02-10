package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.entity.Trade;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
public interface ITradeService extends IBaseService<Trade> {

    @Override
    default boolean save(Trade entity) {

        return IBaseService.super.save(entity);
    }
}
