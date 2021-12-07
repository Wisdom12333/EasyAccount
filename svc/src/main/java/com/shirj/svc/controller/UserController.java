package com.shirj.svc.controller;


import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of {@code User}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService){
        this.iUserService = iUserService;
    }

    @GetMapping("/queryByUserId")
    public User queryByUserId(@RequestParam(value = "userId") long userId){
        log.warn("有人请求了");
        return iUserService.getById(userId);
    }
}
