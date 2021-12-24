package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.UserDAO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
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

        User user = getDao().getOneByLogin(username, password);
        if (null == user) {
            MapUtils.resultInfo(result, "1", "用户名或密码错误!");
            return result;
        }

        String token = TokenUtils.createToken(user);
        if (StringUtils.isBlank(token)) {
            MapUtils.resultInfo(result, "2", "服务调用异常!");
            return result;
        }

        result.put("TOKEN", token);
        result.put("USER_ID", user.getUserId());
        MapUtils.resultInfo(result, "0", "登录成功!");

        return result;
    }

    @Override
    public boolean checkUsername(String username) {

        User user = getDao().checkUsername(username);
        return user == null;

    }

    @Override
    public boolean save(User user) throws DuplicateKeyException {
        user.setCreateTime(TimeUtils.now());
        user.setUpdateTime(TimeUtils.now());
        return super.save(user);
    }
}
