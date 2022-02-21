package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import com.shirj.pub.consts.CommConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
@Slf4j
public class TradeServiceImpl extends BaseServiceImpl<TradeDAO, Trade> implements ITradeService {

    @Resource
    private AccountDAO accountDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Trade entity) {
        String tradeType = entity.getTradeType();
        Integer tradeAmount = entity.getTradeAmount();
        Account account = accountDAO.selectById(entity.getAccountId());
        Account transAccount = null;
        Long balance = account.getBalance();
        switch (Integer.parseInt(tradeType)) {
            //支出
            case 1: {
                account.setBalance(balance - tradeAmount);
                break;
            }
            //收入
            case 2: {
                account.setBalance(balance + tradeAmount);
                break;
            }
            //转账
            case 3: {
                Long rsrvStr1 = Long.parseLong(entity.getRsrvStr1());
                transAccount = accountDAO.selectById(rsrvStr1);
                //手续费
                Long transFee = Long.parseLong(entity.getRsrvStr2());
                //转账
                transAccount.setBalance(transAccount.getBalance() + tradeAmount);
                balance -= (tradeAmount + transFee);
                account.setBalance(balance);
                transAccount.setUpdateTime(now());
                break;
            }
            default: {
                break;
            }
        }
        account.setUpdateTime(now());
        entity.setTradeTime(now());
        entity.setAcceptMonth(now().getMonthValue());
        try {
            if (CommConst.TRADE_TYPE.TRANSFER.getValue().equals(tradeType)) {
                accountDAO.updateById(transAccount);
            }
            accountDAO.updateById(account);
            super.save(entity);
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}
