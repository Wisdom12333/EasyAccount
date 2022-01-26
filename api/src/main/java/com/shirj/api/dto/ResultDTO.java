package com.shirj.api.dto;

import com.shirj.pub.consts.ResultCode;
import lombok.Data;

import java.util.Map;

@Data
public class ResultDTO {

    Map<String, Object> result;

    ResultCode resultCode;

    public ResultDTO(ResultCode resultCode, String resultInfo) {
        resultCode.setResultInfo(resultInfo);
        this.resultCode = resultCode;
    }
}
