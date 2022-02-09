package com.nicht.smallspringstep07.springframework.beans.factory.support;

import com.nicht.smallspringstep07.springframework.core.io.DefaultResourceLoader;
import com.nicht.smallspringstep07.springframework.core.io.ResourceLoader;

/**
 * @Description  所有的beanDefinitionReader 扫描后都需要注册，
 * 因此将注册功能统一放入抽象类实现，这样不用再关心注册
 * @Author Administrator
 * @Time 2021/11/18
 * @Link
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final  BeanDefinitionRegistry registry;
    private final  ResourceLoader resourceLoader;


    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry,new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }



}
