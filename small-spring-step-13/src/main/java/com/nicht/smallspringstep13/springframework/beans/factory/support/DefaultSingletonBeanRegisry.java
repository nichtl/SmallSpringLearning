package com.nicht.smallspringstep13.springframework.beans.factory.support;

import com.nicht.smallspringstep13.springframework.beans.BeansException;
import com.nicht.smallspringstep13.springframework.beans.factory.DisposableBean;
import com.nicht.smallspringstep13.springframework.beans.factory.config.SingletonBeanRegistry;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public class DefaultSingletonBeanRegisry implements SingletonBeanRegistry {
    protected static final Object NULL_OBJECT = new Object();
    // 一级缓存，普通对象
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    //二级缓存，提前暴漏对象，没有完全实例化的对象
    protected final Map<String, Object> earlySingletonObjects = new HashMap<>();
    // 三级缓存，存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

    //新增销毁方法
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();


    /**
     * 单例注册表
     *
     * @param beanName
     */
    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if(null == singletonObject){
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存中是否有对象，这个对象就是代理对象，因为只有代理对象才会放到三级缓存中
             if(null == singletonObject){
                 ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                 if(null!= singletonFactory) {
                   singletonObject = singletonFactory.getObject();
                   //把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存
                     earlySingletonObjects.put(beanName,singletonObject);
                     singletonFactories.remove(beanName);
                 }
             }
        }
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }


    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory){
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
        }
    }


    static String encode(String word) {
        HashMap<Character, Integer> cout = new HashMap<>();
        for (int i = 0, len = word.length(); i < len; i++) {
            if (cout.containsKey(word.charAt(i))) {
                cout.put(word.charAt(i), cout.get(word.charAt(i)) + 1);
                continue;
            }
            cout.put(word.charAt(i), 1);
        }
        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0, len = word.length(); i < len; i++) {

        }
        return word;
    }


    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();
        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
