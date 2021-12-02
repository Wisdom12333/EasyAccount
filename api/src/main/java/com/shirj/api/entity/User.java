package com.shirj.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
public class User {

    private long userId;

    private String userName;

    private String password;

    private String eMail;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime endTime;

    private String removeTag;

}
