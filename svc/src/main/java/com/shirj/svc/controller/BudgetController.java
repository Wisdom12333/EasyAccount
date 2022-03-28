package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Budget;
import com.shirj.api.service.IBudgetService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class BudgetController extends BaseController {

    private final IBudgetService iBudgetService;

    @Autowired
    public BudgetController(IBudgetService iBudgetService) {
        this.iBudgetService = iBudgetService;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<ResultDTO> getBudgetInfo(@Param("userId") final Long userId) {
        try {
            ResultDTO budgetInfo = iBudgetService.getBudgetInfo(userId);
            return returnOk(budgetInfo);
        } catch (Exception e) {
            return returnException("获取数据失败!");
        }
    }

    @PostMapping("/addBudget")
    public ResponseEntity<ResultDTO> addBudget(@RequestBody Budget budget) {
        try {
            //存在就进行更新,否则插入
            iBudgetService.saveBudget(budget);
            return returnOk();
        } catch (Exception e) {
            return returnException();
        }
    }
}
