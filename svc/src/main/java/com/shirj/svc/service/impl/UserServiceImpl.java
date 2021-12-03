package com.shirj.svc.service.impl;

import com.shirj.api.dao.UserDAO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements IUserService {

    @Resource
    UserDAO userDAO;

    @Override
    public boolean save(User entity) {
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
}
