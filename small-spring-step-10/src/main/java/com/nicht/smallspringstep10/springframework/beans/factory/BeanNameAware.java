package com.nicht.smallspringstep10.springframework.beans.factory;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/24
 * @Link
 */
public interface BeanNameAware extends Aware{
    //实现此接口，既能感知到所属的 BeanName
    void setBeanName(String name);
}
