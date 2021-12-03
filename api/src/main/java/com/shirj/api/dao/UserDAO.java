package com.shirj.api.dao;

import com.shirj.api.entity.User;
import com.shirj.pub.core.dao.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO extends BaseDAO<User> {
}
