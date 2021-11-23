package com.nicht.smallspringstep04.springframework.beans.factory.support;

import com.nicht.smallspringstep04.springframework.beans.BeansException;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public class DefaultListableBeanFactory extends  AbstractAutowireCapableBeanFactory implements  BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanDefinitionMap  = new ConcurrentHashMap<>();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
     beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition  beanDefinition   = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) throw  new BeansException("No  beanName  "+ beanName + " is defined");
        return  beanDefinition;
    }
}
