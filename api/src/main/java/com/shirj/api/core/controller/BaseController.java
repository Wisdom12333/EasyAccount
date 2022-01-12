package com.shirj.api.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public <T> ResponseEntity<T> returnException(T result) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    public <T> ResponseEntity<T> returnResult(T result, HttpStatus status) {
        return ResponseEntity.status(status).body(result);
    }

    public <T> ResponseEntity<T> returnResult(T result) {
        return ResponseEntity.ok(result);
    }
}
