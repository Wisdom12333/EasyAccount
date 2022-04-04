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

/**
 * The entity of table T_ACCOUNT.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Setter
@Getter
@ToString
@Builder
@Alias("Account")
@TableName("T_ACCOUNT")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 账户标识
     */
    @TableId(value = "ACCOUNT_ID", type = IdType.AUTO)
    private Long accountId;
    /**
     * 账户名称
     */
    @TableField("ACCOUNT_NAME")
    private String accountName;
    /**
     * 用户标识
     */
    @TableField("USER_ID")
    private Long userId;
    /**
     * 账号类型
     */
    @TableField("TAG")
    private String tag;
    /**
     * 类型名称
     */
    @TableField("TAG_NAME")
    private String tagName;
    /**
     * 账户余额
     */
    @TableField("BALANCE")
    private Long balance;
    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;
    /**
     * 是否计入总资产
     */
    @TableField("IS_TOTAL")
    private String isTotal;
    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;
    /**
     * 生效标识,0有效
     */
    @TableField("REMOVE_TAG")
    private String removeTag;
    /**
     * 附属字段1
     */
    @TableField("RSRV_STR1")
    private String rsrvStr1;
    /**
     * 附属字段2
     */
    @TableField("RSRV_STR2")
    private String rsrvStr2;
    /**
     * 附属字段3
     */
    @TableField("RSRV_STR3")
    private String rsrvStr3;
    /**
     * 附属字段4
     */
    @TableField("RSRV_STR4")
    private String rsrvStr4;
    /**
     * 附属字段5
     */
    @TableField("RSRV_STR5")
    private String rsrvStr5;
    /**
     * 附属时间1
     */
    @TableField("RSRV_TIME1")
    private LocalDateTime rsrvTime1;
    /**
     * 附属时间2
     */
    @TableField("RSRV_TIME2")
    private LocalDateTime rsrvTime2;
    /**
     * 附属时间3
     */
    @TableField("RSRV_TIME3")
    private LocalDateTime rsrvTime3;

}
