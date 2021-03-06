package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.dto.MonthContrastDTO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.dto.StatDTO;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import com.shirj.pub.consts.AccountConst;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.consts.ResultCode;
import com.shirj.pub.utils.ArrayUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * The implement of {@code ITradeService}.
 *
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
                if (AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0, 1))) {
                    long rsrvStr2 = Long.parseLong(account.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 + tradeAmount));
                }
                break;
            }
            //收入
            case 2: {
                account.setBalance(balance + tradeAmount);
                //如果是信用账户,还需要修改欠款
                if (AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0, 1))) {
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
                if (AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(account.getTag().substring(0, 1))) {
                    long rsrvStr2 = Long.parseLong(account.getRsrvStr2());
                    account.setRsrvStr2(String.valueOf(rsrvStr2 + tradeAmount + transFee));
                }
                if (AccountConst.ACCOUNT_TYPE.CREDIT.getValue().equals(transAccount.getTag().substring(0, 1))) {
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
        if (entity.getTradeTime() == null) {
            entity.setTradeTime(now());
            entity.setAcceptMonth(now().getMonthValue());
        }
        try {
            if (CommConst.TRADE_TYPE.TRANSFER.getValue().equals(tradeType)) {
                accountDAO.updateById(transAccount);
            }
            accountDAO.updateById(account);
            super.save(entity);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    @Override
    public ResultDTO delete(List<Trade> trades) {
        try {
            trades.forEach(this::removeById);
            return new ResultDTO(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw e;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultDTO modify(List<Trade> trades) {
        if (ArrayUtils.isEmpty(trades)) {
            return new ResultDTO(ResultCode.ERROR_DATA, "空的账单");
        }
        try {
            final Trade byId = this.getById(trades.get(0).getTradeId());
            this.delete(Collections.singletonList(byId));
            trades.forEach(this::save);
            return new ResultDTO(ResultCode.SUCCESS, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean removeById(Trade trade) {
        //删除原账单
        super.removeById(trade);
        //先进行退款,这里新增一条与原账单金额相反的账单
        Integer tradeAmount = trade.getTradeAmount() * -1;
        trade.setTradeAmount(tradeAmount);
        if (CommConst.TRADE_TYPE.TRANSFER.equals(trade.getTradeType())) {
            int transFee = Integer.parseInt(trade.getRsrvStr2());
            trade.setRsrvStr2(String.valueOf(transFee * -1));
        }
        this.save(trade);
        return super.removeById(trade);
    }

    @Override
    public ResultDTO getStat(Long userId, Integer year, Integer month) {
        //该月账单
        List<Trade> monthTrades = baseMapper.getFullMonthTrades(userId, year, month);
        //总支出与收入
        List<Map<String, Object>> stat = baseMapper.getStat(userId, year, month);

        StatDTO dto = new StatDTO();
        for (Map<String, Object> stringObjectMap : stat) {
            String tradeType = String.valueOf(stringObjectMap.get("TRADE_TYPE"));
            //实际数据库中以分为单位存储
            int amount = Integer.parseInt(String.valueOf(stringObjectMap.get("AMOUNT")));
            if (CommConst.TRADE_TYPE.EXPEND.equals(tradeType)) {
                dto.setExpend(amount);
            } else if (CommConst.TRADE_TYPE.INCOME.equals(tradeType)) {
                dto.setIncome(amount);
            } else if (CommConst.TRADE_TYPE.TRANSFER.equals(tradeType)) {
                dto.setTransfer(amount);
            }
        }
        dto.setMonthTrades(monthTrades);

        ResultDTO result = new ResultDTO(ResultCode.SUCCESS, "");
        result.getResult().put("stat", dto);
        return result;
    }

    @Override
    public List<MonthContrastDTO> getMonthContrast(Long userId, Integer year) {
        List<MonthContrastDTO> monthContrast = baseMapper.getMonthContrast(userId, year);
        monthContrast.sort(Comparator.comparing(MonthContrastDTO::getAcceptMonth));
        return monthContrast;
    }
}
