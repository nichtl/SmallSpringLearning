package com.nicht.smallspringstep13.springframework.beans.factory.config;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/16
 * @Link
 */
/*
 *循环依赖 坑未填
 *Bean 的引用*/
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }


}
