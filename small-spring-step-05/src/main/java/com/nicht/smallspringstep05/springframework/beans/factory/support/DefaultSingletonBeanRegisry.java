package com.nicht.smallspringstep05.springframework.beans.factory.support;

import com.nicht.smallspringstep05.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/19
 * @Link
 */
public class DefaultSingletonBeanRegisry implements SingletonBeanRegistry {
    private Map<String, Object> singletonObjects = new HashMap<>();
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

}
