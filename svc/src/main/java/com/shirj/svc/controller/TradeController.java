package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.service.ITradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trade")
@Slf4j
public class TradeController extends BaseController {

    private ITradeService iTradeService;

    @Autowired
    public TradeController(ITradeService iTradeService) {
        this.iTradeService = iTradeService;
    }

    @PostMapping("book")
    public ResponseEntity<String> bookTrade() {
        return returnOK("test");
    }
}
