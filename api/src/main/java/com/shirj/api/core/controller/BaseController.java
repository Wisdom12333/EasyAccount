package com.shirj.api.core.controller;

import com.shirj.api.dto.ResultDTO;
import com.shirj.pub.consts.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
public class BaseController {

    protected final static HttpStatus BAD_REQ = HttpStatus.BAD_REQUEST;
    protected final static HttpStatus OK = HttpStatus.OK;

    protected final static String EX_MESSAGE = "服务调用异常";
    protected final static String OK_MESSAGE = "成功";

    protected final static ResultDTO EXCEPTION_DTO = new ResultDTO(ResultCode.SVC_EXCEPTION, EX_MESSAGE);
    protected final static ResultDTO OK_DTO = new ResultDTO(ResultCode.SVC_EXCEPTION, OK_MESSAGE);

    protected final static ResponseEntity<ResultDTO> EXCEPTION = ResponseEntity.status(BAD_REQ).body(EXCEPTION_DTO);
    protected final static ResponseEntity<ResultDTO> OK_RESPONSE = ResponseEntity.ok(OK_DTO);

    protected final <T> ResponseEntity<T> returnException(final T message) {
        return ResponseEntity.status(BAD_REQ).body(message);
    }

    protected final ResponseEntity<ResultDTO> returnException() {
        return EXCEPTION;
    }

    protected final ResponseEntity<ResultDTO> returnException(String message) {
        return ResponseEntity.status(BAD_REQ).body(new ResultDTO(ResultCode.SVC_EXCEPTION, message));
    }

    protected final <T> ResponseEntity<T> returnOk(final T result) {
        return ResponseEntity.ok(result);
    }
    protected final ResponseEntity<ResultDTO> returnOk() {
        return OK_RESPONSE;
    }

    protected final ResponseEntity<ResultDTO> returnOk(final String message) {
        return ResponseEntity.ok(new ResultDTO(ResultCode.SUCCESS, message));
    }
}
