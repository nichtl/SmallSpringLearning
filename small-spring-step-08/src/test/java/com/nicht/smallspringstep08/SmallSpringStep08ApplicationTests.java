package com.nicht.smallspringstep08;

import com.nicht.smallspringstep08.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class smallspringstep08ApplicationTests {

    @Test
    void contextLoads() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware： "+userService.getApplicationContext());
        System.out.println("BeanFactoryAware："+userService.getBeanFactory());
    }

}
