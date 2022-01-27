package com.shirj.svc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {SvcApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SvcApplication.class})
@Slf4j
public class SvcApplicationTests {

    @Test
    public void Test(){
        //You can test it here.
        log.info("You can test it here.");
    }
}
