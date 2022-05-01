package com.shirj.api.service;

import com.shirj.api.core.service.IBaseService;
import com.shirj.api.dto.MonthContrastDTO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Trade;

import java.util.List;

/**
 * The interface for {@code Trade} service.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public interface ITradeService extends IBaseService<Trade> {

    /**
     * Book and update Account info.
     *
     * @param entity a new trade
     * @return true if book success.
     */
    @Override
    boolean save(Trade entity);

    /**
     * Batch deletion.
     *
     * @param trades the trades need to delete
     * @return true if delete success
     */
    ResultDTO delete(List<Trade> trades);

    /**
     * Remove a trade by id.
     *
     * @param trade the trade to remove
     * @return true if remove success
     */
    @Override
    boolean removeById(Trade trade);

    /**
     * Get a user's statistic, put them into {@code StatDTO}.
     *
     * @param userId user's id
     * @param year   the year of statistic
     * @param month  the month of statistic,1-12
     * @return statistic in {@code StatDTO}
     */
    ResultDTO getStat(Long userId, Integer year, Integer month);

    /**
     * Get a user's month contrast info, put them into a list of {@code MonthContrastDTO}.
     *
     * @param userId user's id
     * @param year   the year of statistic
     * @return month contrast info in {@code MonthContrastDTO}
     */
    List<MonthContrastDTO> getMonthContrast(Long userId, Integer year);

    /**
     * Modify trades.
     *
     * @param trades trades
     * @return result
     */
    ResultDTO modify(List<Trade> trades);
}
