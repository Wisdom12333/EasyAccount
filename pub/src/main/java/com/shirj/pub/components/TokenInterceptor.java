package com.shirj.pub.components;

import com.shirj.pub.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An interceptor for token.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        response.setCharacterEncoding("utf-8");
        //获取并校验token，判断是否需要生成新的token。
        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)){
            if(TokenUtils.verify(token)){
                if(TokenUtils.checkRefresh(token)) {
                    //添加允许访问的自定义头信息
                    response.setHeader("Access-Control-Expose-Headers", "newToken");
                    response.setHeader("newToken",TokenUtils.refreshToken(token));
                }
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            response.sendError(401,"非法的token");
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

        return false;
    }
}
