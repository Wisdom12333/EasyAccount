package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import com.shirj.pub.consts.AccountConst;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.consts.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

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
                //如果是信用账户,还需要修改欠款
                if(AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0,1))){
                    long rsrvStr2 = Long.parseLong(account.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 + tradeAmount));
                }
                break;
            }
            //收入
            case 2: {
                account.setBalance(balance + tradeAmount);
                //如果是信用账户,还需要修改欠款
                if(AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0,1))){
                    long rsrvStr2 = Long.parseLong(account.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 - tradeAmount));
                }
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
                //如果是信用账户,还需要修改欠款
                if(AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0,1))){
                    long rsrvStr2 = Long.parseLong(account.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 + tradeAmount + transFee));
                }
                if(AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(transAccount.getTag().substring(0,1))){
                    long rsrvStr2 = Long.parseLong(transAccount.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 - tradeAmount));
                }
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

    @Override
    public ResultDTO delete(List<Trade> trades) {
        try {
            trades.forEach(this::removeById);
            return new ResultDTO(ResultCode.SUCCESS,null);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDTO(ResultCode.SVC_EXCEPTION,null);
        }
    }

    @Override
    public boolean removeById(Trade trade) {
        //删除原账单
        super.removeById(trade);
        //先进行退款,这里新增一条与原账单金额相反的账单
        Integer tradeAmount = trade.getTradeAmount() * -1;
        trade.setTradeAmount(tradeAmount);
        if("3".equals(trade.getTradeType())){
            int transFee = Integer.parseInt(trade.getRsrvStr2());
            trade.setRsrvStr2(String.valueOf(transFee * -1));
        }
        this.save(trade);
        return super.removeById(trade);
    }
}
