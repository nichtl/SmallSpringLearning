package com.nicht.smallspringstep13.springframework.beans.factory.support;

import com.nicht.smallspringstep13.springframework.beans.BeansException;
import com.nicht.smallspringstep13.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/11/6
 * @Link
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    //Cglib 实例化
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null != constructor) return enhancer.create(constructor.getParameterTypes(), args);
        return enhancer.create();
    }
}
