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
 * The entity of table T_USER.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Setter
@Getter
@ToString
@Builder
@Alias("User")
@TableName("T_USER")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 用户标识
     */
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String username;
    /**
     * 用户密码
     */
    @TableField("PASSWORD")
    private String password;
    /**
     * 用户邮箱
     */
    @TableField("E_MAIL")
    private String eMail;
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
     * 结束时间
     */
    @TableField("END_TIME")
    private LocalDateTime endTime;
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

}
