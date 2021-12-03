package com.shirj.api.entity;

import com.shirj.pub.core.entity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Param extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String typeId;

    private int dataId;

    private String subParam1;

    private String subParam2;

    private String subParam3;

    private String subParam4;

    private String subParam5;

    private String subParam6;

    private String subParam7;

    private String subParam8;

    private String subParam9;

    private String subParam10;

}
