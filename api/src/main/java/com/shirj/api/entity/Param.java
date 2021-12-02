package com.shirj.api.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Param implements Serializable {

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
