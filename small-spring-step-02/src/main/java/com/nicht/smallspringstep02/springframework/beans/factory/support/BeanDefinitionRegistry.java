package com.nicht.smallspringstep02.springframework.beans.factory.support;

import com.nicht.smallspringstep02.springframework.beans.factory.config.BeanDefinition;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
