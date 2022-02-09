package com.nicht.smallspringstep09.springframework.context.support;

import com.nicht.smallspringstep09.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.nicht.smallspringstep09.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public abstract  class AbstractXmlApplicationContext  extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory,this);
        String [] configLocations= getConfigLocations();
        if (null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }


    }
    protected abstract String[] getConfigLocations();
}
