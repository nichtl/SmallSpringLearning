package com.nicht.smallspringstep10.springframework.beans.factory.config;

import com.nicht.smallspringstep10.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

        String SCOPE_SINGLETON = "singleton";

        String SCOPE_PROTOTYPE = "prototype";

        void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

        /**
         * 销毁单例对象
         */
        void destroySingletons();


}

