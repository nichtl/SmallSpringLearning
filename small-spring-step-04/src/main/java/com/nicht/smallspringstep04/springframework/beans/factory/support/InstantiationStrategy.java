package com.nicht.smallspringstep04.springframework.beans.factory.support;

import com.nicht.smallspringstep04.springframework.beans.BeansException;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/11/6
 * @Link
 */
public interface InstantiationStrategy {
    //实例化策略接口
    Object instantiate(BeanDefinition beanDefinition, String  beanName, Constructor constructor,Object[] args) throws BeansException;
    /*
    * 1.这是自定义的实例化接口,正对不同的参数的构造函数做出一个统一的接口,
    * 2 Constructor是java.lang.reflect 下的类 里面包含了必要的类信息,有了这个参数,就可以找到对应符合参数的构造函数;
    * 3 arg 就是具体的参数
    * */
}
