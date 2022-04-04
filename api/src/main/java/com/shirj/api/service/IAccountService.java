package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.entity.Account;
import lombok.NonNull;

/**
 * The interface for {@code Account} service.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IAccountService extends IBaseService<Account> {

    /**
     * Delete an Account by id.
     *
     * @param id id of this Account
     * @return true if delete success
     */
    boolean deleteAccount(@NonNull long id);

}
