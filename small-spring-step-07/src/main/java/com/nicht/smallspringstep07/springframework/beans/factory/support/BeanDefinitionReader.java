package com.nicht.smallspringstep07.springframework.beans.factory.support;

import com.nicht.smallspringstep07.springframework.beans.BeansException;
import com.nicht.smallspringstep07.springframework.core.io.Resource;
import com.nicht.smallspringstep07.springframework.core.io.ResourceLoader;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */
// 之前测试都是手动创建beandefinition并注册 这里模仿spring
// 拆分为 beandefinitionReader 扫描器 注册实现了BeanDefinitionRegisitry已经有了
public interface BeanDefinitionReader {
    //获取注册器
    BeanDefinitionRegistry  getRegistry();
    ResourceLoader getResourceLoader();
    void loadBeanDefinitions(Resource resource) throws BeansException;
    void loadBeanDefinitions(Resource... resources) throws BeansException;
    void loadBeanDefinitions(String location) throws BeansException;
    void loadBeanDefinitions(String... locations) throws BeansException;



}
