package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO of the {@code Account}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface AccountDAO extends BaseDAO<Account> {

}
