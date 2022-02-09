package com.nicht.smallspringstep10.springframework.context.support;

import com.nicht.smallspringstep10.springframework.beans.BeansException;
import com.nicht.smallspringstep10.springframework.beans.factory.ApplicationContextAwareProcessor;
import com.nicht.smallspringstep10.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.nicht.smallspringstep10.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.nicht.smallspringstep10.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep10.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.nicht.smallspringstep10.springframework.context.ApplicationEvent;
import com.nicht.smallspringstep10.springframework.context.ApplicationListener;
import com.nicht.smallspringstep10.springframework.context.ConfigurableApplicationContext;
import com.nicht.smallspringstep10.springframework.context.event.ApplicationEventMulticaster;
import com.nicht.smallspringstep10.springframework.context.event.ContextClosedEvent;
import com.nicht.smallspringstep10.springframework.context.event.ContextRefreshedEvent;
import com.nicht.smallspringstep10.springframework.context.event.SimpleApplicationEventMulticaster;
import com.nicht.smallspringstep10.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description 抽象应用上下文  继承DefaultResourceLoader 来处理 spring.xml资源加载
 *
 * @Author Administrator
 * @Time 2021/11/19
 * @Link
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;


    //模板方法 refresh
    @Override
    public void refresh() throws BeansException {
         /** 1. 创建 BeanFactory，并加载 BeanDefinition*/
         refreshBeanFactory();
         /** 2. 获取 BeanFactory */
         ConfigurableListableBeanFactory beanFactory = getBeanFactory();
         /** 3. 添加 ApplicationContextAwareProcessor，
         * 让继承自 ApplicationContextAware 的 Bean 对象都能感知所属的 ApplicationContext
         */
         beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
         /** 4.在 Bean 实例化之前，执行 BeanFactoryPostProcessor*/
         invokeBeanFactoryPostProcessors(beanFactory);
         /** 5.BeanPostProcessor 需要提前于其他 Bean 对象实例化之前执行注册操作 */
         registerBeanPostProcessors(beanFactory);
         // 6. 初始化事件发布者
         initApplicationEventMulticaster();
         // 7. 注册事件监听器
         registerListeners();
         /** 8.提前实例化单例 Bean 对象 */
         beanFactory.preInstantiateSingletons();
         //9.发布容器刷新完成事件
         finishRefresh();
    }

    private  void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory  = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);

    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    @Override
    public void publishEvent(ApplicationEvent event) {
           applicationEventMulticaster.multicastEvent(event);
    }

    protected abstract  void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }


    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }



}
