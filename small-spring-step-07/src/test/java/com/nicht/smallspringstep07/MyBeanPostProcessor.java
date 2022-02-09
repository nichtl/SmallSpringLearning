package com.nicht.smallspringstep07;

import com.nicht.smallspringstep07.springframework.beans.BeansException;
import com.nicht.smallspringstep07.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    /**
     * 在Bean 对象执行初始化方法之前,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("postProcessBeforeInitialization  bean实例化完毕 初始化操作之前 ");
        }
        return bean;
    }

    /**
     * 在Bean 对象执行初始化方法之后,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
