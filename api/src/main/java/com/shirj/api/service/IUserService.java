package com.shirj.api.service;

import com.shirj.api.entity.User;

public interface IUserService {

    User queryByUserId(int userId);
}
