package com.shirj.svc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shirj, wisdom12333@iCloud.com
 */
@SpringBootApplication(scanBasePackages = "com.shirj")
@MapperScan("com.shirj.api.dao")
public class SvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SvcApplication.class, args);
    }

}
