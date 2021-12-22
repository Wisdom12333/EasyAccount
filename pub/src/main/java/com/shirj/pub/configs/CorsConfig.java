package com.shirj.pub.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        //添加CORS配置
        CorsConfiguration config = new CorsConfiguration();
        //允许的域
        config.addAllowedOrigin("http://localhost:8080");
        //是否发送Cookie
        config.setAllowCredentials(true);
        //允许的请求方式
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        //允许的头信息
        config.addAllowedHeader("*");

        //添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }

}
