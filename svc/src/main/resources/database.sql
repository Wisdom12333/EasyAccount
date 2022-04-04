-- T_USER
create table if not exists t_user
(
    USER_ID     bigint auto_increment comment '用户标识'
        primary key,
    USER_NAME   varchar(40)  collate utf8mb4_bin   not null comment '用户名',
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
        unique (USER_ID, USER_NAME)
)
    comment '用户表';

create index T_USER_NAME_index
    on T_USER (USER_NAME);

-- T_ACCOUNT
create table if not exists t_account
(
    ACCOUNT_ID   bigint auto_increment comment '账户标识'
        primary key,
    ACCOUNT_NAME varchar(40)                        null comment '账户名称',
    USER_ID      bigint                             not null comment '用户标识',
    TAG          varchar(2)                         not null comment '账户类型',
    TAG_NAME     varchar(10)                        null comment '类型名称',
    BALANCE      bigint   default 0                 null comment '账户余额,默认以分为单位',
    REMARK       varchar(255)                       null comment '备注',
    IS_TOTAL     char     default '1'               not null comment '是否计入总资产,0-否,1-是',
    CREATE_TIME  datetime default CURRENT_TIMESTAMP null comment '生成时间',
    UPDATE_TIME  datetime                           null comment '更新时间',
    END_TIME     datetime                           null comment '结束时间',
    REMOVE_TAG   char     default '0'               not null comment '是否生效，0-有效，1-无效',
    RSRV_STR1    varchar(100)                       null comment '预留字段1',
    RSRV_STR2    varchar(100)                       null comment '预留字段2',
    RSRV_STR3    varchar(200)                       null comment '预留字段3',
    RSRV_STR4    varchar(200)                       null comment '预留字段4',
    RSRV_STR5    varchar(200)                       null comment '预留字段5',
    RSRV_TIME1   datetime                           null comment '预留时间1',
    RSRV_TIME2   datetime                           null comment '预留时间2',
    RSRV_TIME3   datetime                           null comment '预留时间3',
    constraint USER_ID_ACCOUNT
        foreign key (USER_ID) references t_user (USER_ID)
)
    comment '用户账户表';

-- T_TRADE
create table if not exists t_trade
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
    RSRV_STR1    varchar(100)                       null comment '预留字段1',
    RSRV_STR2    varchar(100)                       null comment '预留字段2',
    RSRV_STR3    varchar(200)                       null comment '预留字段3',
    RSRV_STR4    varchar(200)                       null comment '预留字段4',
    RSRV_STR5    varchar(200)                       null comment '预留字段5',
    constraint ACCOUNT_ID_TRADE
        foreign key (ACCOUNT_ID) references t_account (ACCOUNT_ID),
    constraint USER_ID_TRADE
        foreign key (USER_ID) references t_user (USER_ID)
)
    comment '用户账单表';

create index T_TRADE_MONTH_index
    on T_TRADE (ACCEPT_MONTH);

-- T_BUDGET
create table t_budget
(
    BUDGET_ID     bigint auto_increment comment '预算标识'
        primary key,
    USER_ID       bigint                             not null comment '用户标识',
    BUDGET_NAME   varchar(15)                        null comment '预算名称',
    BUDGET_AMOUNT bigint                             not null comment '预算额度',
    CREATE_TIME   datetime default CURRENT_TIMESTAMP null comment '生成时间'
)
    comment '用户预算表';

create index t_budget_USER_ID_BUDGET_NAME_index
    on t_budget (USER_ID, BUDGET_NAME);

-- T_PARAM
create table if not exists t_param
(
    TYPE_ID     varchar(20)  not null comment '类型标识',
    DATA_ID     int          not null comment '数据标识',
    SUB_PARAM1  varchar(50)  null comment '附属字段1',
    SUB_PARAM2  varchar(50)  null comment '附属字段2',
    SUB_PARAM3  varchar(50)  null comment '附属字段3',
    SUB_PARAM4  varchar(50)  null comment '附属字段4',
    SUB_PARAM5  varchar(100) null comment '附属字段5',
    SUB_PARAM6  varchar(100) null comment '附属字段6',
    SUB_PARAM7  varchar(100) null comment '附属字段7',
    SUB_PARAM8  varchar(100) null comment '附属字段8',
    SUB_PARAM9  varchar(200) null comment '附属字段9',
    SUB_PARAM10 varchar(200) null comment '附属字段10',
    primary key (TYPE_ID, DATA_ID)
)
    comment '静态参数表';

create index T_PARAM_TYPE_ID_index
    on t_param (TYPE_ID);
