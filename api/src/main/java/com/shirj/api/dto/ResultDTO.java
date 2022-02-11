package com.shirj.api.dto;

import com.shirj.pub.consts.ResultCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Data
public class ResultDTO {

    private Map<String, Object> result;

    private ResultCode resultCode;

    private String code;

    private String message;

    public ResultDTO(ResultCode resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
        this.code = resultCode.getValue();
        this.result = new HashMap<>();
    }
}
