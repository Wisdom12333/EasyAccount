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
    /**
     * 类型标识
     */
    @TableId(value = "TYPE_ID", type = IdType.INPUT)
    private String typeId;
    /**
     * 数据标识
     */
    @TableField("DATA_ID")
    private int dataId;
    /**
     * 参数1
     */
    @TableField("SUB_PARAM1")
    private String subParam1;
    /**
     * 参数2
     */
    @TableField("SUB_PARAM2")
    private String subParam2;
    /**
     * 参数3
     */
    @TableField("SUB_PARAM3")
    private String subParam3;
    /**
     * 参数4
     */
    @TableField("SUB_PARAM4")
    private String subParam4;
    /**
     * 参数5
     */
    @TableField("SUB_PARAM5")
    private String subParam5;
    /**
     * 参数6
     */
    @TableField("SUB_PARAM6")
    private String subParam6;
    /**
     * 参数7
     */
    @TableField("SUB_PARAM7")
    private String subParam7;
    /**
     * 参数8
     */
    @TableField("SUB_PARAM8")
    private String subParam8;
    /**
     * 参数9
     */
    @TableField("SUB_PARAM9")
    private String subParam9;
    /**
     * 参数10
     */
    @TableField("SUB_PARAM10")
    private String subParam10;

}
