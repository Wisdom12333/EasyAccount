package com.shirj.svc.service.impl;

import com.shirj.api.dao.UserDAO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    UserDAO userDAO;

    @Override
    public User queryByUserId(long userId) {
        return userDAO.queryByUserId(userId);
    }
}
