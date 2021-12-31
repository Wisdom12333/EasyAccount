package com.shirj.api.core.service.impl;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.core.service.IBaseService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Setter
@Getter
public class BaseServiceImpl<M extends BaseDAO<T>,T extends BaseEntity> implements IBaseService<T> {

    private M dao;

    @Override
    public boolean save(T entity) {
        return dao.save(entity);
    }

    @Override
    public boolean removeById(long id) {
        return dao.removeById(id);
    }

    @Override
    public boolean updateById(long id) {
        return dao.updateById(id);
    }

    @Override
    public T getById(long id) {
        return dao.getById(id);
    }
}
