package com.shirj.api.dao;

import com.shirj.api.core.dao.BaseDAO;
import com.shirj.api.dto.MonthContrastDTO;
import com.shirj.api.entity.Trade;
import lombok.NonNull;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * DAO of the {@code Trade}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Mapper
public interface TradeDAO extends BaseDAO<Trade> {

    /**
     * Get statistic of specified month and year.
     *
     * @param userId the user's id
     * @param year   year of date
     * @param month  month of date
     * @return basic statistic of the user
     */
    List<Map<String, Object>> getStat(@NonNull @Param("id") Long userId, @Param("year") Integer year, @Param("month") Integer month);

    /**
     * Get a user's expend current month.
     *
     * @param userId the user's id
     * @return this user's expend in current month
     */
    Integer getExpend(@NonNull @Param("id") Long userId);

    /**
     * Get expend trades of the user in specified month.
     *
     * @param userId the user's id
     * @param year   the year of date
     * @param month  the month of date
     * @return a list of expend {@code Trade} in specified month
     */
    List<Trade> getMonthTrades(@NonNull @Param("id") Long userId, @Param("year") Integer year, @Param("month") Integer month);

    /**
     * Get all types trades of the user in specified month.
     *
     * @param userId the user's id
     * @param year   the year of date
     * @param month  the month of date
     * @return a list of {@code Trade} in specified month
     */
    List<Trade> getFullMonthTrades(@NonNull @Param("id") Long userId, @Param("year") Integer year, @Param("month") Integer month);

    /**
     * Get month contrast info of the user in specified year.
     *
     * @param userId the user's id
     * @param year   the year of date
     * @return a list of {@code MonthContrastDTO} in specified year
     */
    List<MonthContrastDTO> getMonthContrast(@NonNull @Param("id") Long userId, @Param("year") Integer year);

}
