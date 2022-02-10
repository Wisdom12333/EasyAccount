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

    private ResultCode code;

    private String message;

    public ResultDTO(ResultCode code, String message) {
        this.code = code;
        this.message = message;
        this.result = new HashMap<>();
    }
}
