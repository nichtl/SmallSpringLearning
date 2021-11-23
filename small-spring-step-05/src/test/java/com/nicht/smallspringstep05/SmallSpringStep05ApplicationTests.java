package com.nicht.smallspringstep05;

import com.nicht.smallspringstep05.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.nicht.smallspringstep05.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSpringStep05ApplicationTests {

    @Test
    void contextLoads() {
        DefaultListableBeanFactory  beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader =new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        UserService userService =  beanFactory.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }


}
