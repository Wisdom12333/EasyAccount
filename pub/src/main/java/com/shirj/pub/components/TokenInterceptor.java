package com.shirj.pub.components;

import com.alibaba.fastjson.JSONObject;
import com.shirj.pub.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        response.setCharacterEncoding("utf-8");

        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)){
            if(TokenUtils.verify(token)){
                if(TokenUtils.checkRefresh(token)) {
                    response.setHeader("NEW_TOKEN", TokenUtils.refreshToken(token));
                }
                return true;
            }
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            JSONObject json = new JSONObject();
            json.put("success","false");
            json.put("message","认证失败,非法的token");
            json.put("code","500");
            response.getWriter().append(json.toJSONString());
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }


        return false;

    }
}
