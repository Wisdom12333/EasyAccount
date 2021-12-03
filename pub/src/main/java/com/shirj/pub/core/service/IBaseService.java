package com.shirj.pub.core.service;

import com.shirj.pub.core.entity.BaseEntity;

public interface IBaseService<T extends BaseEntity> {

    boolean save(T entity);

    boolean removeById(long id);

    boolean updateById(long id);

    /**
     * <p>Query {@code T} by Id.</p>
     *
     * @param id the T id.
     * @return {@code T}.
     */
    T getById(long id);

}
