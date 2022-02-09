package com.nicht.smallspringstep08.springframework.beans.factory.support;

import com.nicht.smallspringstep08.springframework.beans.BeansException;
import com.nicht.smallspringstep08.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.nicht.smallspringstep08.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public class DefaultListableBeanFactory extends  AbstractAutowireCapableBeanFactory implements  BeanDefinitionRegistry, ConfigurableListableBeanFactory  {
    private Map<String, BeanDefinition> beanDefinitionMap  = new ConcurrentHashMap<>();
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
     beanDefinitionMap.put(beanName,beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition  beanDefinition   = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) throw  new BeansException("No  beanName  "+ beanName + " is defined");
        return  beanDefinition;
    }

    /**
     * 判断是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    /**
     * 按照类型返回 Bean 实例
     * @param type
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }



    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }


}
