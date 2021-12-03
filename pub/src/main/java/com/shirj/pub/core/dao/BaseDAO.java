package com.shirj.pub.core.dao;

import com.shirj.pub.core.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

public interface BaseDAO<T extends BaseEntity> {

    boolean save(T entity);

    boolean removeById(@Param("id") long id);

    boolean updateById(@Param("id") long id);

    T getById(@Param("id") long id);

}
