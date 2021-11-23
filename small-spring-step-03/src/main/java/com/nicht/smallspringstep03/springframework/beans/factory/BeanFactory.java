package com.nicht.smallspringstep03.springframework.beans.factory;
import  com.nicht.smallspringstep03.springframework.beans.factory.BeansException;


/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
    //带参数实例化bean
    Object getBean(String name ,Object... args ) throws BeansException;
}
