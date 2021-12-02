package com.shirj.svc;

import com.shirj.api.entity.User;
import com.shirj.api.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SvcApplicationTests {

    @Autowired
    IUserService iUserService;

    @Test
    public void Test(){
        User a = iUserService.queryByUserId(1L);
        System.out.println(a.toString());
    }
}
