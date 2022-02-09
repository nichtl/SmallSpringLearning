package com.nicht.smallspringstep09.springframework.context.support;

import com.nicht.smallspringstep09.springframework.beans.BeansException;
import com.nicht.smallspringstep09.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.nicht.smallspringstep09.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Description 获取 Bean 工厂和加载资源
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public abstract class AbstractRefreshableApplicationContext extends  AbstractApplicationContext {
    private DefaultListableBeanFactory  beanFactory;
    @Override
    protected void refreshBeanFactory() throws BeansException {
      DefaultListableBeanFactory beanFactory =createBeanFactory();
      loadBeanDefinitions(beanFactory);
      this.beanFactory =beanFactory;
    }
    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
