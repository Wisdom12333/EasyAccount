package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.entity.Account;
import com.shirj.api.service.IAccountService;
import com.shirj.pub.consts.CommConst;
import lombok.NonNull;
import org.springframework.stereotype.Service;

/**
 * The implement of {@code IAccountService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountDAO, Account> implements IAccountService {

    @Override
    public boolean deleteAccount(@NonNull long id) {
        return super.updateById(Account
                .builder()
                .accountId(id)
                .updateTime(now())
                .removeTag(CommConst.INVALID)
                .build());
    }
}
