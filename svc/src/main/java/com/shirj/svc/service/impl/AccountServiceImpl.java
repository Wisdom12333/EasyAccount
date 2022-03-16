package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.entity.Account;
import com.shirj.api.service.IAccountService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountDAO, Account> implements IAccountService {

    @Override
    public boolean removeById(@NonNull long id) {
        return super.updateById(Account
                .builder()
                .accountId(id)
                .updateTime(now())
                .removeTag("1")
                .build());
    }
}
