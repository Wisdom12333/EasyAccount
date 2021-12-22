package com.shirj.api.core.dao;

import com.shirj.api.core.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

/**
 * The basic interface of DAO.
 *
 * @param <T> the type parameter which extends {@code BaseEntity}
 * @author shirj, wisdom12333@iCloud.com
 */
public interface BaseDAO<T extends BaseEntity> {

    /**
     * Save an entity to its table.
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
    boolean removeById(@Param("id") long id);

    /**
     * Update a record by id.
     *
     * @param id the id of record
     * @return true if successfully update
     */
    boolean updateById(@Param("id") long id);

    /**
     * Get a record by id.
     *
     * @param id the id of record
     * @return an entity T
     */
    T getById(@Param("id") long id);

}
