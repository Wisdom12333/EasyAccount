package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Budget;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BudgetDAO extends BaseDAO<Budget> {

}
