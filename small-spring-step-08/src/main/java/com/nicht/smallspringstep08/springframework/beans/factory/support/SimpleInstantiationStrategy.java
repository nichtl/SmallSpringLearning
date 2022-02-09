package com.nicht.smallspringstep08.springframework.beans.factory.support;

import com.nicht.smallspringstep08.springframework.beans.BeansException;
import com.nicht.smallspringstep08.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/11/6
 * @Link
 */
public class SimpleInstantiationStrategy  implements  InstantiationStrategy{
    //JDK 实例化
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if(null != constructor){
                return  clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else{
                return  clazz.getDeclaredConstructor().newInstance();
            }
        }catch (NoSuchMethodException | InstantiationException |IllegalAccessException | InvocationTargetException e){
            throw  new BeansException("Failed to instantiate ["+clazz.getName()+"]" +e);
        }
    }
}
