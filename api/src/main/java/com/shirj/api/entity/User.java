package com.shirj.api.entity;

import com.shirj.api.core.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private long userId;

    private String userName;

    private String password;

    private String eMail;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime endTime;

    private String removeTag;

}
