package com.shirj.svc.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cors config.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
@Configuration
public class CorsConfig {

    public static final String WEB_URL = "http://localhost:8080";

    @Bean
    public CorsFilter corsFilter() {
        //添加CORS配置
        CorsConfiguration config = new CorsConfiguration();
        //允许的域
        config.addAllowedOrigin(WEB_URL);
        //是否发送Cookie
        config.setAllowCredentials(true);
        //允许的请求方式
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.HEAD);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        //允许的头信息
        config.addAllowedHeader(CorsConfiguration.ALL);
        //添加自定义的响应头
        config.addExposedHeader(HttpHeaders.AUTHORIZATION);

        //添加映射路径
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        return new CorsFilter(configSource);
    }

}
