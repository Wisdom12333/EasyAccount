package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.MonthContrastDTO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Trade;
import com.shirj.api.service.ITradeService;
import com.shirj.pub.consts.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ResultDTO> bookTrade(@RequestBody Trade trade) {
        try {
            boolean flag = iTradeService.save(trade);
            if (flag) {
                return returnOk();
            } else {
                return returnException();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return returnException();
        }
    }

    @PostMapping("delete")
    public ResponseEntity<ResultDTO> delete(@RequestBody List<Trade> trades){
        ResultDTO dto = iTradeService.delete(trades);
        if(ResultCode.SUCCESS.equals(dto.getResultCode())){
            return returnOk();
        }else return returnException();
    }

    @GetMapping("getStat")
    public ResponseEntity<ResultDTO> getStat(@RequestParam final Long userId, @RequestParam final Integer year, @RequestParam final Integer month){
        try {
            ResultDTO dto = iTradeService.getStat(userId, year, month);
            return returnOk(dto);
        } catch (Exception e) {
            return returnException();
        }
    }

    @GetMapping("getMonthContrast")
    public ResponseEntity<ResultDTO> getMonthContrast(@RequestParam final Long userId, @RequestParam final Integer year){
        try {
            List<MonthContrastDTO> monthContrast = iTradeService.getMonthContrast(userId, year);
            ResultDTO dto = new ResultDTO(ResultCode.SUCCESS,"");
            dto.getResult().put("monthContrast", monthContrast);
            return returnOk(dto);
        } catch (Exception e) {
            return returnException();
        }
    }

}
