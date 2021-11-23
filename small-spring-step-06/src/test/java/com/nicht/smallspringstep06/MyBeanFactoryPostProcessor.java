package com.nicht.smallspringstep06;

import com.nicht.smallspringstep06.springframework.beans.BeansException;
import com.nicht.smallspringstep06.springframework.beans.PropertyValue;
import com.nicht.smallspringstep06.springframework.beans.PropertyValues;
import com.nicht.smallspringstep06.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.nicht.smallspringstep06.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep06.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public class MyBeanFactoryPostProcessor  implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company","BeanFactoryPostProcessor BeanDefinition 加载完毕， 实例化之前修改BeanDefinition"));
    }
}
