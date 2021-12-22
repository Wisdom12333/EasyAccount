package com.shirj.pub.components;

import com.shirj.pub.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        response.setCharacterEncoding("utf-8");

        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)){
            return TokenUtil.verify(token);
        }

        return false;

    }
}
