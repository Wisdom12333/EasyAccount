package com.shirj.pub.configs;

import com.shirj.pub.components.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@Configuration
public class InterceptConfig implements WebMvcConfigurer {

    private final TokenInterceptor tokenInterceptor;

    @Autowired
    public InterceptConfig(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/user/login");
        excludePath.add("/user/register");
        excludePath.add("/user/logout");
        excludePath.add("/static/**");
        excludePath.add("/assets/**");

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);

        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
