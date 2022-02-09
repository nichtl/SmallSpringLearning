package com.nicht.smallspringstep13.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Description 方法匹配，找到表达式范围内匹配下的目标类和方法。在上文的案例中有所体现：methodMatcher.matches(method, targetObj.getClass())
 * @Author Administrator
 * @Time 2021/12/13
 * @Link
 */
public interface MethodMatcher {
    /**
     * Perform static checking whether the given method matches. If this
     *
     * @return whether or not this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
