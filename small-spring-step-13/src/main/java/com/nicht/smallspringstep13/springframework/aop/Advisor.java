package com.nicht.smallspringstep13.springframework.aop;


import org.aopalliance.aop.Advice;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/21
 * @Link
 */
public interface Advisor {
    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
