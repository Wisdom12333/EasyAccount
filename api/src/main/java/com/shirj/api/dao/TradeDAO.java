package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Trade;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeDAO extends BaseDAO<Trade> {
}
