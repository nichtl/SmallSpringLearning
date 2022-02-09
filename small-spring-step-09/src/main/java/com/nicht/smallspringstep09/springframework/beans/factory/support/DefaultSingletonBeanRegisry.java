package com.nicht.smallspringstep09.springframework.beans.factory.support;

import com.nicht.smallspringstep09.springframework.beans.BeansException;
import com.nicht.smallspringstep09.springframework.beans.factory.DisposableBean;
import com.nicht.smallspringstep09.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public class DefaultSingletonBeanRegisry implements SingletonBeanRegistry {
    protected static final Object NULL_OBJECT = new Object();
    private Map<String, Object> singletonObjects = new HashMap<>();
    //新增销毁方法
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();
    /**
     * 单例注册表
     *
     * @param beanName
     */
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    static String encode(String word){
        HashMap<Character,Integer>  cout =  new HashMap<>();
        for (int i = 0,len=word.length(); i <len ; i++) {
            if(cout.containsKey(word.charAt(i))){
                cout.put(word.charAt(i),cout.get(word.charAt(i))+1);
                continue;
            }
            cout.put(word.charAt(i),1);
        }
        StringBuilder  sb  = new StringBuilder(word.length());
        for (int i = 0,len=word.length(); i <len ; i++) {

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
