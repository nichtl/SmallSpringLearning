package com.nicht.smallspringstep02.springframework.beans.factory.support;

import com.nicht.smallspringstep02.springframework.beans.factory.BeansException;
import com.nicht.smallspringstep02.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public abstract  class AbstractAutowireCapableBeanFactory  extends AbstractBeanFactory {


    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
     Object  bean  = null;
     try{
         bean = beanDefinition.getBeanClass().newInstance();
     }catch (InstantiationException | IllegalAccessException e){
        throw  new BeansException("InstantiationException",e);
     }
     addSingleton(beanName,bean);

    return  bean;

    }
}
