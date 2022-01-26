package com.shirj.svc.controller;


import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.consts.ResultCode;
import com.shirj.pub.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

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
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        ResultDTO resultDTO = iUserService.login(user.getUsername(), user.getPassword());
        ResultCode resultCode = resultDTO.getResultCode();
        Map<String, Object> result = new HashMap<>(4);
        result.put("code", resultCode.getValue());
        result.put("message", resultCode.getResultInfo());

        if (!ResultCode.SUCCESS.equals(resultCode)) {
            return returnResult(result, BAD_REQ);
        } else {
            result.put("USER_ID", MapUtils.getValue(resultDTO.getResult(), "USER_ID"));
            result.put("token", MapUtils.getValue(resultDTO.getResult(), "TOKEN"));
            return returnOK(result);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            boolean flag = iUserService.save(user);
            if (flag) {
                return returnOK("success");
            } else {
                return returnException();
            }

        } catch (DuplicateKeyException e) {
            return returnResult("用户名已存在!", BAD_REQ);
        } catch (Exception e) {
            return returnException();
        }
    }

    @GetMapping("/checkUsername")
    public ResponseEntity<Void> checkUsername(@RequestParam String username) {

        if (iUserService.checkUsername(username)) {
            return returnOK(null);
        } else {
            return returnResult(null, BAD_REQ);
        }

    }
}
