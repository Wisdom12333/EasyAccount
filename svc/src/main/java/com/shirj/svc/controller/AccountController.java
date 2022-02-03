package com.shirj.svc.controller;

import com.shirj.api.core.controller.BaseController;
import com.shirj.api.entity.Account;
import com.shirj.api.service.IAccountService;
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
@RequestMapping("/account")
@Slf4j
public class AccountController extends BaseController {

    private final IAccountService iAccountService;

    @Autowired
    public AccountController(IAccountService iAccountService) {
        this.iAccountService = iAccountService;
    }

    @PostMapping("/addAccount")
    public ResponseEntity<String> addAccount(@RequestBody Account account) {
        log.error(account.toString());
        try {
            boolean flag = iAccountService.save(account);
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
