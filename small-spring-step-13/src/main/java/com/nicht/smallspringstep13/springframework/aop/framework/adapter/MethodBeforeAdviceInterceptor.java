package com.nicht.smallspringstep13.springframework.aop.framework.adapter;

import com.nicht.smallspringstep13.springframework.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/21
 * @Link
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    public void setAdvice(MethodBeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 执行 methodBeforeAdvice
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
