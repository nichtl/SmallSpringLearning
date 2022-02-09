package com.nicht.smallspringstep13.springframework.beans.factory;

public interface BeanClassLoaderAware extends Aware {
    void setBeanClassLoader(ClassLoader classLoader);
}
