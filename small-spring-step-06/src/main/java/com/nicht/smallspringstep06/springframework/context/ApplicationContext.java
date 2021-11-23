package com.nicht.smallspringstep06.springframework.context;

import com.nicht.smallspringstep06.springframework.beans.BeansException;
import com.nicht.smallspringstep06.springframework.beans.factory.ListableBeanFactory;


import java.util.Map;

/**
 * @Description ApplicationContext 继承ListableBeanFactory 就拥有beanFactory的功能
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public interface ApplicationContext  extends ListableBeanFactory {


}
