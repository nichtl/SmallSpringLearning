package com.nicht.smallspringstep07.springframework.beans.factory;

import com.nicht.smallspringstep07.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */
public interface ListableBeanFactory  extends  BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
