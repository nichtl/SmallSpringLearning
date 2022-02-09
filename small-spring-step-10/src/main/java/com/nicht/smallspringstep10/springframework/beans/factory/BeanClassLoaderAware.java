package com.nicht.smallspringstep10.springframework.beans.factory;

public interface BeanClassLoaderAware extends  Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
