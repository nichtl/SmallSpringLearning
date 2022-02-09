package com.nicht.smallspringstep10.springframework.beans.factory;

import com.nicht.smallspringstep10.springframework.beans.BeansException;
import com.nicht.smallspringstep10.springframework.context.ApplicationContext;

public interface ApplicationContextAware {
    //实现此接口，既能感知到所属的 ApplicationContext
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
