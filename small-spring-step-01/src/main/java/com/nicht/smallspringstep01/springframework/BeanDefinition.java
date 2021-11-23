package com.nicht.smallspringstep01.springframework;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }
}
