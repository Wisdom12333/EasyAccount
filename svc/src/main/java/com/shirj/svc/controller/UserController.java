package com.shirj.svc.controller;


import com.shirj.api.core.controller.BaseController;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.dto.UserInfoDTO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.consts.ResultCode;
import com.shirj.pub.utils.MapUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
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

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        ResultDTO resultDTO = iUserService.login(user.getUsername(), user.getPassword());
        ResultCode resultCode = resultDTO.getResultCode();
        Map<String, Object> result = new HashMap<>(4);
        result.put("code", resultCode.getValue());
        result.put("message", resultDTO.getResultInfo());

        if (!ResultCode.SUCCESS.equals(resultCode)) {
            return returnException(result);
        } else {
            result.put("userId", MapUtils.getValue(resultDTO.getResult(), "USER_ID"));
            String token = MapUtils.getValue(resultDTO.getResult(), "TOKEN");
            result.put("token", token);
            return ResponseEntity.status(OK)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,HttpHeaders.AUTHORIZATION)
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(result);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            boolean flag = iUserService.save(user);
            if (flag) {
                return returnOk("success");
            } else {
                return returnException();
            }

        } catch (DuplicateKeyException e) {
            return returnException("用户名已存在!");
        } catch (Exception e) {
            return returnException();
        }
    }

    @GetMapping("/checkUsername")
    public ResponseEntity<Void> checkUsername(@RequestParam String username) {

        if (iUserService.checkUsername(username)) {
            return returnOk(null);
        } else {
            return returnException(null);
        }

    }

    @GetMapping("/userInfo")
    public ResponseEntity<ResultDTO> getUserInfo(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)|| CommConst.NULL.equals(userId)) {
            return returnException(new ResultDTO(ResultCode.ERROR_DATA, "用户标识为空"));
        }
        UserInfoDTO userInfo = iUserService.getUserInfo(Long.parseLong(userId));
        ResultDTO resultDTO = new ResultDTO(ResultCode.SUCCESS, null);
        resultDTO.getResult().put("userInfo", userInfo);
        return returnOk(resultDTO);
    }
}
