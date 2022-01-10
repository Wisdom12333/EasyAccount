
create table if not exists T_USER
(
    USER_ID     bigint auto_increment comment '用户标识'
        primary key,
    USER_NAME   varchar(40)                        not null comment '用户名',
    PASSWORD    varchar(20)                        not null comment '用户密码',
    E_MAIL      varchar(40)                        not null comment '用户邮箱',
    CREATE_TIME datetime default CURRENT_TIMESTAMP null comment '生成时间',
    UPDATE_TIME datetime                           null comment '更新时间',
    END_TIME    datetime                           null comment '结束时间',
    REMOVE_TAG  char     default '0'               null comment '是否生效，0-有效，1-无效',
    RSRV_STR1   varchar(100)                       null comment '预留字段1',
    RSRV_STR2   varchar(100)                       null comment '预留字段2',
    RSRV_STR3   varchar(200)                       null comment '预留字段3',
    RSRV_STR4   varchar(200)                       null comment '预留字段4',
    RSRV_STR5   varchar(200)                       null comment '预留字段5',
    constraint USER_NAME
        unique (USER_NAME)
);

create index T_USER_ID_NAME_index
    on T_USER (USER_ID, USER_NAME);

