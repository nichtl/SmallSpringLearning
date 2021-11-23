package com.nicht.smallspringstep02;

import com.nicht.smallspringstep02.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep02.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSpringStep02ApplicationTests {

    @Test
    void contextLoads() {
        DefaultListableBeanFactory  beanFactory  = new DefaultListableBeanFactory();
        BeanDefinition  beanDefinition  = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService",beanDefinition);

        UserService userService  = (UserService) beanFactory.getBean("userService");//第一次获取 AbstractAutowireCapableBeanFactory 实例化
        UserService userService_Sington  = (UserService) beanFactory.getBean("userService");// 从DefaultSingletonBeanRegisry  singtonObject容器中获取
        userService.queryUserInfo();
        userService_Sington.queryUserInfo();
    }

}
