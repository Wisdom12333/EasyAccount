package com.shirj.svc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = {SvcApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SvcApplication.class})
@Slf4j
public class SvcApplicationTests {

    @Test
    public void Test() {
        //You can test it here.
        log.info("You can test it here.");
    }
}
