package com.nicht.smallspringstep04;

import com.nicht.smallspringstep04.springframework.beans.PropertyValue;
import com.nicht.smallspringstep04.springframework.beans.PropertyValues;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanReference;
import com.nicht.smallspringstep04.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmallSpringStep04ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public  void test_BeanFactory(){
        //1 初始化 BeanFactory
        DefaultListableBeanFactory  beanFactory = new DefaultListableBeanFactory();
        //2 UserDao 注册
        beanFactory.registerBeanDefinition("userDao",new BeanDefinition(UserDao.class));
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId","10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao",new BeanReference("userDao")));
        // UserService 注入 bean
        BeanDefinition  beanDefinition =new BeanDefinition(UserService.class,propertyValues);
        beanFactory.registerBeanDefinition("userService",beanDefinition);
        //UserService 获取bean
        UserService userService = (UserService)  beanFactory.getBean("userService");
        userService.queryUserInfo();

    }

}
