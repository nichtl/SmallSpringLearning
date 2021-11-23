package com.nicht.smallspringstep06.springframework.beans.factory.config;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public interface SingletonBeanRegistry {
    /**
     * 单例注册表
     */
        Object getSingleton(String beanName);

}
