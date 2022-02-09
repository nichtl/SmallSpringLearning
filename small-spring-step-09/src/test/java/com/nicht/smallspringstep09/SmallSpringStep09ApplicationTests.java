package com.nicht.smallspringstep09;

import com.nicht.smallspringstep09.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSpringStep09ApplicationTests {

    @Test
    void contextLoads() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);
         // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);
        System.out.println(userService01.queryUserInfo());

    }

}
