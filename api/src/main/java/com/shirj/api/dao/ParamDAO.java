package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.entity.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DAO of the {@code Param}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface ParamDAO extends BaseDAO<Param> {

    /**
     * get a list of params.
     * @param typeId the typeId
     * @return a list of params
     */
    List<Param> qryByTypeId(String typeId);
}
