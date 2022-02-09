package com.nicht.smallspringstep13.springframework.aop;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/21
 * @Link
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}
