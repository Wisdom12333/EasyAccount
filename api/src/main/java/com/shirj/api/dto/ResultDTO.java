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

    private String resultInfo;

    public ResultDTO(ResultCode resultCode, String resultInfo) {
        this.resultCode = resultCode;
        this.resultInfo = resultInfo;
        this.result = new HashMap<>();
    }
}
