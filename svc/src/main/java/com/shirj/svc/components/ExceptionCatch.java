package com.shirj.svc.components;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.ResultDTO;
import com.shirj.pub.consts.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public final class ExceptionCatch extends BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultDTO> exception(Exception e) {
        //记录日志
        log.error("catch exception: {}",e.getMessage(),e);
        return returnException();
    }
}

