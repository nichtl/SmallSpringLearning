package com.nicht.smallspringstep10;

import com.nicht.smallspringstep10.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSpringStep10ApplicationTests {

    @Test
    void contextLoads() {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
    applicationContext.publishEvent(new CustomEvent(applicationContext, "张三", "25","wdnmd"));
    applicationContext.publishEvent(new CustomEvent(applicationContext, "李四", "66","wdnmd"));
    applicationContext.registerShutdownHook();
    }

}
