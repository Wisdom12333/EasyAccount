package com.shirj.api.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.core.entity.BaseEntity;
import com.shirj.api.core.service.IBaseService;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Slf4j
public class BaseServiceImpl<M extends BaseDAO<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements IBaseService<T> {

    @Override
    public boolean save(T entity) {
        return super.save(entity);
    }

    @Override
    public boolean removeById(@NonNull final long id) {
        return super.removeById(id);
    }

    @Override
    public boolean updateById(@NonNull final T entity) {
        return super.updateById(entity);
    }

    @Override
    public T getById(@NonNull final long id) {
        return super.getById(id);
    }

    protected static LocalDateTime now() {
        return LocalDateTime.now();
    }

    protected static void copy(Object source, Object target) {
        if (null == source) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }


    @SneakyThrows
    protected static <S, T> T copy(S source, Class<T> targetClass) {
        T target;
        target = targetClass.newInstance();
        copy(source, target);
        return target;
    }
}
