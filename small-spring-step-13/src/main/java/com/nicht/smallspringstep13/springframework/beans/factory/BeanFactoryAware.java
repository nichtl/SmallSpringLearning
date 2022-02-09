package com.nicht.smallspringstep13.springframework.beans.factory;

import com.nicht.smallspringstep13.springframework.beans.BeansException;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/24
 * @Link
 */
public interface BeanFactoryAware extends Aware {
    //实现此接口，既能感知到所属的 BeanFactory
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
