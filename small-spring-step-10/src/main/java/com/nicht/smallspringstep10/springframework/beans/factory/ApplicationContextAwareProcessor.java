package com.nicht.smallspringstep10.springframework.beans.factory;

import com.nicht.smallspringstep10.springframework.beans.BeansException;
import com.nicht.smallspringstep10.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep10.springframework.context.ApplicationContext;

/**由于 ApplicationContext 的获取并不能直接在创建 Bean 时候就可以拿到，所以
 需要在 refresh 操作时，把 ApplicationContext 写入到一个包装的
 BeanPostProcessor 中去，再由
 AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization
 方法调用。*/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 在Bean 对象执行初始化方法之前,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof  ApplicationContextAware) {
          ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    /**
     * 在Bean 对象执行初始化方法之后,执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
