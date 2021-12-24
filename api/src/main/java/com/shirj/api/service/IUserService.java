package com.shirj.api.service;

import com.shirj.api.entity.User;
import com.shirj.api.core.service.IBaseService;

import java.util.Map;

/**
 * The interface for {@code User} service.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IUserService extends IBaseService<User> {

    Map<String, Object> Login(String username, String password);

    boolean checkUsername(String username);
}
