package com.shirj.api.service;

import com.shirj.api.entity.User;

/**
 * The interface for {@code User} service.
 *
 * @author shirj, wisdom12333@iCloud.com.
 */
public interface IUserService {

    /**
     * <p>Query {@code User} by userId.</p>
     *
     * @param userId the user id.
     * @return the {@code User}.
     */
    User queryByUserId(long userId);
}
