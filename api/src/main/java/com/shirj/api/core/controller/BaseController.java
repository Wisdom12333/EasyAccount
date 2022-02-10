package com.shirj.api.core.controller;

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

    protected final static ResponseEntity<String> EXCEPTION = ResponseEntity.status(BAD_REQ).body(EX_MESSAGE);

    protected final <T> ResponseEntity<T> returnException(final T result) {
        return ResponseEntity.status(BAD_REQ).body(result);
    }

    protected final ResponseEntity<String> returnException() {
        return EXCEPTION;
    }

    protected final <T> ResponseEntity<T> returnOk(final T result) {
        return ResponseEntity.ok(result);
    }
}
