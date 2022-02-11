package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import org.springframework.stereotype.Service;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class TradeServiceImpl extends BaseServiceImpl<TradeDAO, Trade> implements ITradeService {


    @Override
    public boolean save(Trade entity) {
        System.out.println(entity);
        return true;
//        return super.save(entity);
    }
}
