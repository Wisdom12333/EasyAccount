package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Budget;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO of the {@code Budget}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface BudgetDAO extends BaseDAO<Budget> {

}
