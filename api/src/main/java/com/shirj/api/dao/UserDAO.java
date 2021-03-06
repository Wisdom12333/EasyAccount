package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.User;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * DAO of the {@code User}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface UserDAO extends BaseDAO<User> {

    /**
     * Get effective one by username.
     *
     * @param username the username
     * @return {@code User} which username equal username and removeTag equal '0'
     */
    User getByUsername(@Param("username") @NonNull final String username);
}
