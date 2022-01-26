package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl extends BaseServiceImpl<TradeDAO, Trade> implements ITradeService {

}
