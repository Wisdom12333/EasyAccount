package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@RestController
@RequestMapping("trade")
@Slf4j
public class TradeController extends BaseController {

    private final ITradeService iTradeService;

    @Autowired
    public TradeController(ITradeService iTradeService) {
        this.iTradeService = iTradeService;
    }

    @PostMapping("book")
    public ResponseEntity<String> bookTrade(@RequestBody Trade trade) {
        try {
            boolean flag = iTradeService.save(trade);
            if (flag) {
                return returnOk(OK_MESSAGE);
            } else {
                return returnException();
            }
        } catch (Exception e) {
            return returnException();
        }
    }
}
