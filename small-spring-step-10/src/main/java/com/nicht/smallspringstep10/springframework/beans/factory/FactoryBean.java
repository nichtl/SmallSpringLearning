package com.nicht.smallspringstep10.springframework.beans.factory;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/7
 * @Link
 */
public interface FactoryBean<T>{
    T  getObject() throws  Exception; //用于扩展我们自己实现的 bean
    Class<?>  getObjectType();
    boolean   isSingleton();
}
