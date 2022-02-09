package com.nicht.smallspringstep08.springframework.beans.factory;

public interface BeanClassLoaderAware extends  Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
