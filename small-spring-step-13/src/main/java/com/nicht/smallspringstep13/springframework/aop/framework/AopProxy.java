package com.nicht.smallspringstep13.springframework.aop.framework;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/13
 * @Link
 */
public interface AopProxy {
    //定义一个标准接口，用于获取代理类。因为具体实现代理的方式可以有 JDK 方
    //式，也可以是 Cglib 方式，所以定义接口会更加方便管理实现类。
    Object getProxy();
}
