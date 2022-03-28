package com.shirj.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shirj.api.core.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
@Alias("Budget")
@TableName("T_BUDGET")
public class Budget extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 预算标识
     */
    @TableId(value = "BUDGET_ID", type = IdType.AUTO)
    private Long budgetId;
    /**
     * 用户标识
     */
    @TableField(value = "USER_ID")
    private Long userId;
    /**
     * 预算名称
     */
    @TableField(value = "BUDGET_NAME")
    private String budgetName;
    /**
     * 预算金额
     */
    @TableField(value = "BUDGET_AMOUNT")
    private Long budgetAmount;
    /**
     * 创建时间
     */
    @TableField(value = "CREATE_TIME")
    private LocalDateTime createTime;
}
