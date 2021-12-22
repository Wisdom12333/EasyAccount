package com.shirj.api.dao;

import com.shirj.api.entity.User;
import com.shirj.api.core.dao.BaseDAO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DAO of the {@code User}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface UserDAO extends BaseDAO<User> {
}
