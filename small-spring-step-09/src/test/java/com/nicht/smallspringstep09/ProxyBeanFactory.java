package com.nicht.smallspringstep09;

import com.nicht.smallspringstep09.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/7
 * @Link
 */
public class ProxyBeanFactory implements FactoryBean<IuserDao> {
    @Override
    public IuserDao getObject() throws Exception {
        InvocationHandler handler  = (proxy, method, args)  -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");
            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };


        return (IuserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IuserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IuserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
