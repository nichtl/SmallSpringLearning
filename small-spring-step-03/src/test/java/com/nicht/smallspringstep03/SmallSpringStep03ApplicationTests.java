package com.nicht.smallspringstep03;

import com.nicht.smallspringstep03.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep03.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class SmallSpringStep03ApplicationTests {

    @Test
    void contextLoads() {
        DefaultListableBeanFactory  beanFactory  = new DefaultListableBeanFactory();

        BeanDefinition  beanDefinition  = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user",beanDefinition);

        User user  = (User) beanFactory.getBean("user","小明");
        System.out.println(user.getName());
    }

}
