package com.shirj.pub.core.service.impl;

import com.shirj.pub.core.dao.BaseDAO;
import com.shirj.pub.core.entity.BaseEntity;
import com.shirj.pub.core.service.IBaseService;

import javax.annotation.Resource;

public class BaseServiceImpl<M extends BaseDAO<T>,T extends BaseEntity> implements IBaseService<T> {

    @Resource
    protected M dao;

    @Override
    public boolean save(T entity) {
        return false;
    }

    @Override
    public boolean removeById(long id) {
        return false;
    }

    @Override
    public boolean updateById(long id) {
        return false;
    }

    @Override
    public T getById(long id) {
        return dao.getById(id);
    }
}
