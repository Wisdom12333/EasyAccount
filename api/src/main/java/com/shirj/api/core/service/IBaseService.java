package com.shirj.api.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shirj.api.core.entity.BaseEntity;

/**
 * The basic interface of service.
 *
 * @param <T> the type parameter which extends {@code BaseEntity}
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IBaseService<T extends BaseEntity> extends IService<T> {

    /**
     * Remove a record by id.
     *
     * @param id the id of record
     * @return true if successfully remove
     */
    boolean removeById(final long id);


    /**
     * Query {@code T} by id.
     *
     * @param id the id of T.
     * @return {@code T}.
     */
    T getById(final long id);

}
