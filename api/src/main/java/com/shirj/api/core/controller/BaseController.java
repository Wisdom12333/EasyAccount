package com.shirj.api.core.controller;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected final static HttpStatus SVC_EXCEPT = HttpStatus.INTERNAL_SERVER_ERROR;
    protected final static HttpStatus BAD_REQ = HttpStatus.BAD_REQUEST;

    protected final <T> ResponseEntity<T> returnException(final T result) {
        return ResponseEntity.status(SVC_EXCEPT).body(result);
    }

    protected final ResponseEntity<String> returnException() {
        return ResponseEntity.status(SVC_EXCEPT).body("服务调用异常");
    }

    protected final <T> ResponseEntity<T> returnResult(final T result, @NonNull final HttpStatus status) {
        return ResponseEntity.status(status).body(result);
    }

    protected final <T> ResponseEntity<T> returnOK(final T result) {
        return ResponseEntity.ok(result);
    }
}
