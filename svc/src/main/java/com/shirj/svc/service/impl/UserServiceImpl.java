package com.shirj.svc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.AccountDAO;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.dao.UserDAO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.dto.UserInfoDTO;
import com.shirj.api.entity.Account;
import com.shirj.api.entity.Trade;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.consts.ResultCode;
import com.shirj.svc.components.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * The implement of {@code IUserService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements IUserService {

    @Resource
    private AccountDAO accountDAO;

    @Resource
    private TradeDAO tradeDAO;

    @Override
    public ResultDTO login(final String username, final String password) {

        ResultDTO resultDTO;

        User user = getBaseMapper().getByUsername(username);
        if (null == user || !password.equals(user.getPassword())) {
            resultDTO = new ResultDTO(ResultCode.ERROR_DATA, "用户名或密码错误!");
            return resultDTO;
        }

        String token = TokenUtils.createToken(user);
        if (StringUtils.isBlank(token)) {
            resultDTO = new ResultDTO(ResultCode.SVC_EXCEPTION, "服务调用异常!");
            return resultDTO;
        }

        resultDTO = new ResultDTO(ResultCode.SUCCESS, "登录成功!");
        resultDTO.getResult().put("TOKEN", token);
        resultDTO.getResult().put("USER_ID", user.getUserId());

        return resultDTO;
    }

    @Override
    public boolean checkUsername(final String username) {
        return getBaseMapper().getByUsername(username) == null;
    }

    @Override
    public boolean save(User user) throws DuplicateKeyException {
        user.setCreateTime(now());
        user.setUpdateTime(now());
        return super.save(user);
    }

    @Override
    public UserInfoDTO getUserInfo(long userId) {
        User user = getBaseMapper().getById(userId);
        UserInfoDTO userInfoDTO = copy(user, UserInfoDTO.class);

        List<Account> accounts = accountDAO.selectList(
                new QueryWrapper<Account>().lambda()
                        .eq(Account::getUserId, userId)
                        .eq(Account::getRemoveTag, "0"));
        List<Trade> trades = tradeDAO.selectList(
                new QueryWrapper<Trade>().lambda()
                        .eq(Trade::getUserId, userId)
                        .orderByDesc(Trade::getTradeTime)
                        .last("LIMIT 20"));
        //获取统计数据，当月支出与收入
        List<Map<String, Object>> stat = tradeDAO.getStat(userId);
        for (Map<String, Object> stringObjectMap : stat) {
            String tradeType = String.valueOf(stringObjectMap.get("TRADE_TYPE"));
            //实际数据库中以分为单位存储
            Double amount = (Double) stringObjectMap.get("AMOUNT");
            //转换为元传给界面
            if(CommConst.TRADE_TYPE.EXPEND.equals(tradeType)){
                userInfoDTO.setExpend(amount/100);
            }else if(CommConst.TRADE_TYPE.INCOME.equals(tradeType)){
                userInfoDTO.setIncome(amount/100);
            }
        }

        userInfoDTO.setAccounts(accounts);
        userInfoDTO.setRecentTrade(trades);
        return userInfoDTO;
    }
}
