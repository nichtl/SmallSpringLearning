package com.nicht.smallspringstep10.springframework.context;

import com.nicht.smallspringstep10.springframework.beans.factory.HierarchicalBeanFactory;
import com.nicht.smallspringstep10.springframework.beans.factory.ListableBeanFactory;
import com.nicht.smallspringstep10.springframework.core.io.ResourceLoader;

/**
 * @Description ApplicationContext 继承ListableBeanFactory 就拥有beanFactory的功能
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public interface ApplicationContext  extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {


}
