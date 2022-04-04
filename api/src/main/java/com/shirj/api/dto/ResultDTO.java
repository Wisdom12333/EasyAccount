package com.shirj.api.dto;

import com.shirj.pub.consts.ResultCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * A dto of result info.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Data
public class ResultDTO {
    /**
     * 返回信息结果集
     */
    private Map<String, Object> result;
    /**
     * 返回结果编码
     */
    private ResultCode resultCode;
    /**
     * 返回结果代码
     */
    private String code;
    /**
     * 返回结果信息
     */
    private String message;

    public ResultDTO(ResultCode resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
        this.code = resultCode.getValue();
        this.result = new HashMap<>();
    }
}
