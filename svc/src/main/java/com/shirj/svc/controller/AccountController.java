package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.Account;
import com.shirj.api.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController extends BaseController {

    private final IAccountService iAccountService;

    @Autowired
    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<ResultDTO> addAccount(@RequestBody Account account) {
        try {
            boolean flag = iAccountService.save(account);
            if (flag) {
                return returnOk();
            } else {
                return returnException();
            }
        } catch (Exception e) {
            return returnException();
        }
    }

    @GetMapping("/deleteAccount")
    public ResponseEntity<ResultDTO> deleteAccount(@RequestParam Long accountId) {
        return iAccountService.deleteAccount(accountId) ? returnOk() : returnException();
    }

    @GetMapping("/queryAccount")
    public ResponseEntity<ResultDTO> queryAccount(@RequestParam Long accountId) {
        Account account = iAccountService.getById(accountId);
        String result = StringUtils.isBlank(account.getAccountName()) ?
                account.getTagName() : account.getAccountName();
        return returnOk(result);
    }
}
