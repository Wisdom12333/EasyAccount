package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.UserDAO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.utils.MapUtils;
import com.shirj.pub.utils.TimeUtils;
import com.shirj.pub.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * The implement of {@code IUserService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements IUserService {

    @Override
    @Autowired
    public void setDao(UserDAO userDAO) {
        super.setDao(userDAO);
    }

    @Override
    public Map<String, Object> Login(String username, String password) {

        Map<String, Object> result = new HashMap<>();

        User user = getDao().getByUsername(username);
        if (null == user || !password.equals(user.getPassword())) {
            MapUtils.resultInfo(result, CommConst.ERROR_DATA, "用户名或密码错误!");
            return result;
        }

        if (CommConst.INVALID.equals(user.getRemoveTag())) {
            MapUtils.resultInfo(result, CommConst.ERROR_DATA, "用户已失效!");
            return result;
        }

        String token = TokenUtils.createToken(user);
        if (StringUtils.isBlank(token)) {
            MapUtils.resultInfo(result, CommConst.SVC_EXCEPTION, "服务调用异常!");
            return result;
        }

        result.put("TOKEN", token);
        result.put("USER_ID", user.getUserId());
        MapUtils.resultInfo(result, CommConst.SUCCESS, "登录成功!");

        return result;
    }

    @Override
    public boolean checkUsername(String username) {

        return getDao().getByUsername(username) == null;

    }

    @Override
    public boolean save(User user) throws DuplicateKeyException {
        user.setCreateTime(TimeUtils.now());
        user.setUpdateTime(TimeUtils.now());
        return super.save(user);
    }
}
