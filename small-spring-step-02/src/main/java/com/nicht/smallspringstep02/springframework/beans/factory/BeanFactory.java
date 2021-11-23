package com.nicht.smallspringstep02.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
