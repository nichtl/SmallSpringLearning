package com.nicht.smallspringstep13.springframework.beans.factory.support;

import com.nicht.smallspringstep13.springframework.beans.factory.FactoryBean;
import com.nicht.smallspringstep13.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep13.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep13.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.nicht.smallspringstep13.springframework.util.ClassUtils;
import org.springframework.beans.BeansException;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    private final ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            // 如果是 FactoryBean，则需要调用 FactoryBean#getObject
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (object == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }

        return object;
    }

    /**
     * @param beanName
     * @return BeanDefinition
     * @throws BeansException
     * @Description getBeanDefition by beanName
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args) throws BeansException;

    @Override
    public Object getBean(String name, Object... args) throws com.nicht.smallspringstep13.springframework.beans.BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
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

    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }
}
