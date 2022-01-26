package com.shirj.svc.service.impl;

import com.shirj.api.core.service.impl.BaseServiceImpl;
import com.shirj.api.dao.UserDAO;
import com.shirj.api.dto.ResultDTO;
import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import com.shirj.pub.consts.CommConst;
import com.shirj.pub.consts.ResultCode;
import com.shirj.svc.components.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * The implement of {@code IUserService}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDAO, User> implements IUserService {

    @Override
    public ResultDTO login(final String username, final String password) {

        ResultDTO resultDTO;

        User user = baseMapper.getByUsername(username);
        if (null == user || !password.equals(user.getPassword())) {
            resultDTO = new ResultDTO(ResultCode.ERROR_DATA, "用户名或密码错误!");
            return resultDTO;
        }

        if (CommConst.INVALID.equals(user.getRemoveTag())) {
            resultDTO = new ResultDTO(ResultCode.ERROR_DATA, "用户已失效!");
            return resultDTO;
        }

        String token = TokenUtils.createToken(user);
        if (StringUtils.isBlank(token)) {
            resultDTO = new ResultDTO(ResultCode.SVC_EXCEPTION, "服务调用异常!");
            return resultDTO;
        }

        resultDTO = new ResultDTO(ResultCode.SUCCESS, "登录成功!");
        resultDTO.getResult().put("TOKEN", token);
        resultDTO.getResult().put("USER_ID", user.getUserId());

        return resultDTO;
    }

    @Override
    public boolean checkUsername(final String username) {

        return baseMapper.getByUsername(username) == null;

    }

    @Override
    public boolean save(User user) throws DuplicateKeyException {
        user.setCreateTime(now());
        user.setUpdateTime(now());
        return super.save(user);
    }
}
