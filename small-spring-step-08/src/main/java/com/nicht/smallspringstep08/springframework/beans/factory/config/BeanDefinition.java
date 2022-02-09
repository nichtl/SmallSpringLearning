package com.nicht.smallspringstep08.springframework.beans.factory.config;

import com.nicht.smallspringstep08.springframework.beans.PropertyValues;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;//记录属性集合
    /*新增bean 的初始化和销毁操作*/
    private String initMethodName;
    private String destroyMethodName;


    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues!=null? propertyValues:new PropertyValues();
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}