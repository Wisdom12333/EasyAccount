package com.shirj.svc.components;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

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
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isNotBlank(token)){
            if(TokenUtils.verify(token)){
                if(TokenUtils.checkRefresh(token)) {
                    //添加允许访问的自定义头信息,使前端能接收到
                    response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.AUTHORIZATION);
                    response.setHeader(HttpHeaders.AUTHORIZATION, TokenUtils.refreshToken(token));
                }
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            response.sendError(SC_UNAUTHORIZED, "非法的token");
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

        return false;
    }
}
