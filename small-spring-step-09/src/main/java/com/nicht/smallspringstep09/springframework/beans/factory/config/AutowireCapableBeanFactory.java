package com.nicht.smallspringstep09.springframework.beans.factory.config;

import com.nicht.smallspringstep09.springframework.beans.BeansException;
import com.nicht.smallspringstep09.springframework.beans.factory.BeanFactory;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/17
 * @Link
 */
public interface AutowireCapableBeanFactory  extends BeanFactory {
    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
