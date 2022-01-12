package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO extends BaseDAO<Account> {

}
