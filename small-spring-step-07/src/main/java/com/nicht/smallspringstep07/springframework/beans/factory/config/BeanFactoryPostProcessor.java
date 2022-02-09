package com.nicht.smallspringstep07.springframework.beans.factory.config;

import com.nicht.smallspringstep07.springframework.beans.BeansException;
import com.nicht.smallspringstep07.springframework.beans.factory.ConfigurableListableBeanFactory;

/*
* 定义接口扩展类
* 在所有beandefinition 加载完成之后 实例化bean对象之前
* 提供一个允许修改 BeanDefinitio属性的机制
* */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
