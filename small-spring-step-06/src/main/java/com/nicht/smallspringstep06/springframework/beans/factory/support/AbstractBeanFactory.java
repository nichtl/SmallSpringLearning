package com.nicht.smallspringstep06.springframework.beans.factory.support;

import com.nicht.smallspringstep06.springframework.beans.factory.BeanFactory;
import com.nicht.smallspringstep06.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep06.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep06.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegisry implements ConfigurableBeanFactory {
    /** BeanPostProcessors to apply in createBean */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition,Object... args) throws BeansException;

    @Override
    public Object getBean(String name, Object... args) throws com.nicht.smallspringstep06.springframework.beans.BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition,args);
    }
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
