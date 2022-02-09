package com.nicht.smallspringstep13.springframework.aop.framework;

import com.nicht.smallspringstep13.springframework.aop.AdvisedSupport;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/21
 * @Link
 */
public class ProxyFactory {
    /**
     * 其实这个代理工厂主要解决的是
     * 关于 JDK 和 Cglib 两种代理的选择问题，有了代
     * 理工厂就可以按照不同的创建需求进行控制。
     */
    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    public AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            return new Cglib2AopProxy(advisedSupport);
        }
        return new JdkDynamicAopProxy(advisedSupport);
    }

}
