package com.shirj.svc.service.impl;

import com.shirj.api.dao.UserDAO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.api.core.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The implement of {@code IUserService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements IUserService {

    @Override
    @Autowired
    public void setDao(UserDAO userDAO) {
        super.setDao(userDAO);
    }
}
