package com.shirj.svc.controller;


import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/queryByUserId")
    public User queryByUserId(@RequestParam(value = "userId") int userId){
        return userService.queryByUserId(1);
    }
}
