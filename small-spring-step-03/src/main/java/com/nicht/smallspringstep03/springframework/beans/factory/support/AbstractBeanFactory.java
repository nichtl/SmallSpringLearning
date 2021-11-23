package com.nicht.smallspringstep03.springframework.beans.factory.support;

import com.nicht.smallspringstep03.springframework.beans.factory.BeanFactory;
import com.nicht.smallspringstep03.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.BeansException;


/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegisry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) throws BeansException;

    @Override
    public Object getBean(String name, Object... args) throws com.nicht.smallspringstep03.springframework.beans.factory.BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition,args);
    }
}
