package com.shirj.svc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.BudgetDAO;
import com.shirj.api.dao.TradeDAO;
import com.shirj.api.dto.BudgetInfoDTO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Budget;
import com.shirj.api.service.IBudgetService;
import com.shirj.pub.consts.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The implement of {@code IBudgetService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class BudgetServiceImpl extends BaseServiceImpl<BudgetDAO, Budget> implements IBudgetService {

    @Resource
    private TradeDAO tradeDAO;

    @Override
    public ResultDTO getBudgetInfo(final Long userId) {
        BudgetInfoDTO dto = new BudgetInfoDTO(userId);
        dto.setExpend(tradeDAO.getExpend(userId));
        dto.setTrades(tradeDAO.getMonthTrades(userId, now().getYear(), now().getMonthValue()));
        List<Budget> budgets = this.getBaseMapper().selectList(new QueryWrapper<Budget>().lambda().eq(Budget::getUserId, userId));
        List<Budget> collect = budgets.stream().filter(x -> {
            if (StringUtils.isBlank(x.getBudgetName())) {
                dto.setTotalBudget(x.getBudgetAmount());
                return false;
            } else {
                return true;
            }
        }).collect(Collectors.toList());
        dto.setBudgets(collect);
        ResultDTO result = new ResultDTO(ResultCode.SUCCESS, null);
        result.getResult().put("budgetInfo", dto);
        return result;
    }

    @Override
    public void saveBudget(Budget entity) {
        super.saveOrUpdate(entity, new UpdateWrapper<Budget>().eq("USER_ID", entity.getUserId()).eq("BUDGET_NAME", entity.getBudgetName()));
    }
}
