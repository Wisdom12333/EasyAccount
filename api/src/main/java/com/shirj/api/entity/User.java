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

    @TableId(value = "USER_ID", type = IdType.AUTO)
    private Long userId;
    @TableField("USER_NAME")
    private String username;
    @TableField("PASSWORD")
    private String password;
    @TableField("E_MAIL")
    private String eMail;
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;
    @TableField("END_TIME")
    private LocalDateTime endTime;
    @TableField("REMOVE_TAG")
    private String removeTag;
    @TableField("RSRV_STR1")
    private String rsrvStr1;
    @TableField("RSRV_STR2")
    private String rsrvStr2;
    @TableField("RSRV_STR3")
    private String rsrvStr3;
    @TableField("RSRV_STR4")
    private String rsrvStr4;
    @TableField("RSRV_STR5")
    private String rsrvStr5;

}
