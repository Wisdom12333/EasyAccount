package com.shirj.api.core.service;

import com.shirj.api.core.entity.BaseEntity;

/**
 * The basic interface of service.
 *
 * @param <T> the type parameter which extends {@code BaseEntity}
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IBaseService<T extends BaseEntity> {

    /**
     * Save an entity.
     *
     * @param entity the entity
     * @return true if successfully save
     */
    boolean save(T entity);

    /**
     * Remove a record by id.
     *
     * @param id the id of record
     * @return true if successfully remove
     */
    boolean removeById(final long id);

    /**
     * Update a record by id.
     *
     * @param id the id of record
     * @return true if successfully update
     */
    boolean updateById(final long id);

    /**
     * Query {@code T} by id.
     *
     * @param id the id of T.
     * @return {@code T}.
     */
    T getById(final long id);

}
