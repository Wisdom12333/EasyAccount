package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.User;

/**
 * The interface for {@code User} service.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IUserService extends IBaseService<User> {

    /**
     * Login, create a token for the user.
     * Check the username and password.
     *
     * @param username the username
     * @param password the password
     * @return the status
     */
    ResultDTO login(final String username, final String password);

    /**
     * Check if a user exists.
     *
     * @param username the username
     * @return true if exist a user whose name equal the username
     */
    boolean checkUsername(final String username);
}
