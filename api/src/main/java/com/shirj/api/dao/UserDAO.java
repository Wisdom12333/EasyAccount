package com.shirj.api.dao;

import com.shirj.api.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDAO {

    User queryByUserId(@Param("id") int id);
}
