package com.nicht.smallspringstep10.springframework.context.event;

import com.nicht.smallspringstep10.springframework.beans.BeansException;
import com.nicht.smallspringstep10.springframework.beans.factory.BeanFactory;
import com.nicht.smallspringstep10.springframework.beans.factory.BeanFactoryAware;
import com.nicht.smallspringstep10.springframework.context.ApplicationEvent;
import com.nicht.smallspringstep10.springframework.context.ApplicationListener;
import org.springframework.util.ClassUtils;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description  对事件广播器通用方法进行抽象抽离，避免每次都需要自己实现
 * @Author Administrator
 * @Time 2021/12/9
 * @Link
 */
public  abstract  class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {
    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();
    ThreadPoolExecutor threadPoolExecutor  = new ThreadPoolExecutor(2,100,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(), new ThreadPoolExecutor.DiscardOldestPolicy());
    private BeanFactory  beanFactory;

    /**
     * Add a listener to be notified of all events.
     * @param listener the listener to add
     */
    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add( (ApplicationListener<ApplicationEvent>) listener);
    }



    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
           applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    //寻找能够处理的listeners
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<ApplicationListener>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners)
        {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event){
        Class<? extends  ApplicationListener> listenerClass = applicationListener.getClass();
        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.getUserClass(listenerClass);
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className); // 获取参数全类名并实例化
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }
        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
        // isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，
        // 默认所有的类的终极父类都是 Object。如果 A.isAssignableFrom(B)结果是 true，证明 B 可以转换为 A,也就是 A 可以由 B 转换而来。
        return eventClassName.isAssignableFrom(event.getClass());  // 判断实例化后参数 是否和event同一类型
    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return threadPoolExecutor;
    }
}

