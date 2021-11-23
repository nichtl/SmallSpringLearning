package com.nicht.smallspringstep06.springframework.context.support;

import com.nicht.smallspringstep06.springframework.beans.BeansException;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public class ClassPathXmlApplicationContext  extends AbstractXmlApplicationContext{
    private String[] configLocations;
    public ClassPathXmlApplicationContext() {
    }

    /**
     * 从 XML 中加载 BeanDefinition，并刷新上下文
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocations) throws BeansException {
        this(new String[]{configLocations});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations =configLocations;
        refresh();
    }


    @Override
    public void registerShutdownHook() {

    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
