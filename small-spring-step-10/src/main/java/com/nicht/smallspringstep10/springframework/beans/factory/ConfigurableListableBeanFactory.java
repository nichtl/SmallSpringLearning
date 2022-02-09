package com.nicht.smallspringstep10.springframework.beans.factory;

import com.nicht.smallspringstep10.springframework.beans.BeansException;
import com.nicht.smallspringstep10.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.nicht.smallspringstep10.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep10.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep10.springframework.beans.factory.config.ConfigurableBeanFactory;


/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;


    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
