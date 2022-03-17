package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.entity.Account;
import lombok.NonNull;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
public interface IAccountService extends IBaseService<Account> {

    boolean deleteAccount(@NonNull long id);

}
