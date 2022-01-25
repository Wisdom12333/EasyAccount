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

/**
 * The entity of table T_PARAM.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Getter
@Setter
@Builder
@ToString
@Alias("Param")
@TableName("T_PARAM")
public class Param extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @TableId(value = "TYPE_ID", type = IdType.INPUT)
    private String typeId;
    @TableField("DATA_ID")
    private int dataId;
    @TableField("SUB_PARAM1")
    private String subParam1;
    @TableField("SUB_PARAM2")
    private String subParam2;
    @TableField("SUB_PARAM3")
    private String subParam3;
    @TableField("SUB_PARAM4")
    private String subParam4;
    @TableField("SUB_PARAM5")
    private String subParam5;
    @TableField("SUB_PARAM6")
    private String subParam6;
    @TableField("SUB_PARAM7")
    private String subParam7;
    @TableField("SUB_PARAM8")
    private String subParam8;
    @TableField("SUB_PARAM9")
    private String subParam9;
    @TableField("SUB_PARAM10")
    private String subParam10;

}
