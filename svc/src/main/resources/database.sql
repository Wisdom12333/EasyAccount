-- T_USER
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
)
    comment '用户表';

create index T_USER_ID_NAME_index
    on T_USER (USER_ID, USER_NAME);

-- T_ACCOUNT
create table t_account
(
    ACCOUNT_ID   bigint auto_increment comment '账户标识'
        primary key,
    ACCOUNT_NAME varchar(40)                        null comment '账户名称',
    USER_ID      bigint                             not null comment '用户标识',
    TAG          varchar(1)                         not null comment '账户类型',
    BALANCE      bigint   default 0                 null comment '账户余额,默认以分为单位',
    REMARK       varchar(255)                       null comment '备注',
    IS_TOTAL     char     default '1'               not null comment '是否计入总资产,0-否,1-是',
    CREATE_TIME  datetime default CURRENT_TIMESTAMP null comment '生成时间',
    UPDATE_TIME  datetime                           null comment '更新时间',
    END_TIME     datetime                           null comment '结束时间',
    REMOVE_TAG   char                               not null comment '是否生效，0-有效，1-无效',
    RSRV_STR1    varchar(100)                       null comment '预留字段1',
    RSRV_STR2    varchar(100)                       null comment '预留字段2',
    RSRV_STR3    varchar(200)                       null comment '预留字段3',
    RSRV_STR4    varchar(200)                       null comment '预留字段4',
    RSRV_STR5    varchar(200)                       null comment '预留字段5',
    constraint USER_ID_ACCOUNT
        foreign key (USER_ID) references t_user (USER_ID)
)
    comment '用户账户表';

-- T_TRADE
create table t_trade
(
    TRADE_ID     bigint auto_increment comment '账单标识'
        primary key,
    USER_ID      bigint                             not null comment '用户标识',
    ACCOUNT_ID   bigint                             not null comment '账户标识',
    TRADE_TYPE   char                               not null comment '账单类型',
    ACCEPT_MONTH int                                not null comment '入账月份',
    TRADE_NAME   varchar(15)                        null comment '账单名',
    TRADE_ICON   int                                null comment '账单图标',
    TRADE_AMOUNT int                                not null comment '账单金额',
    REMARK       varchar(255)                       null comment '备注',
    TRADE_TIME   datetime default CURRENT_TIMESTAMP null comment '入账时间',
    IS_RE_TRADE  char     default '0'               null comment '是否重复账单,0-否,1-是',
    constraint ACCOUNT_ID_TRADE
        foreign key (ACCOUNT_ID) references t_account (ACCOUNT_ID),
    constraint USER_ID_TRADE
        foreign key (USER_ID) references t_user (USER_ID)
)
    comment '用户账单表';
