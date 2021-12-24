package com.shirj.api.entity;

import com.shirj.api.core.entity.BaseEntity;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

/**
 * The entity of table T_PARAM.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Setter
@Getter
@ToString
@Builder
@Alias("User")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long userId;

    private String username;

    private String password;

    private String eMail;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime endTime;

    private String removeTag;

}
