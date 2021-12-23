package com.shirj.svc.controller;


import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @GetMapping("/queryByUserId")
    public User queryByUserId(@RequestParam(value = "userId") long userId) {
        log.warn("有人请求了");
        return iUserService.getById(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user){
        Map<String, Object> response = iUserService.Login(user.getUsername(), user.getPassword());
        Map<String, Object> result = new HashMap<>();
        String resultCode = MapUtils.getValue(response, "RESULT_CODE", "2");
        String resultInfo = MapUtils.getValue(response, "RESULT_INFO", "服务调用异常,请稍后重试!");
        result.put("code", resultCode);
        result.put("message", resultInfo);

        if(!"0".equals(resultCode)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }else{
            result.put("USER_ID", MapUtils.getValue(response, "USER_ID"));
            result.put("token", MapUtils.getValue(response, "TOKEN"));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        if(iUserService.save(user)) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("服务调用异常!");
    }
}
