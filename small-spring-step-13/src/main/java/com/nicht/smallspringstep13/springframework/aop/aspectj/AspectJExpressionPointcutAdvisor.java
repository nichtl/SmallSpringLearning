package com.nicht.smallspringstep13.springframework.aop.aspectj;

import com.nicht.smallspringstep13.springframework.aop.BeforeAdvice;
import com.nicht.smallspringstep13.springframework.aop.Pointcut;
import com.nicht.smallspringstep13.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Description AspectJExpressionPointcutAdvisor 实现了 PointcutAdvisor 接口，把切面
 * pointcut、拦截方法 advice 和具体的拦截表达式包装在一起。这样就可以在 xml
 * 的配置中定义一个 pointcutAdvisor 切面拦截器了
 * @Author Administrator
 * @Time 2021/12/21
 * @Link
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    // 解析的切面
    private AspectJExpressionPointcut pointcut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see MethodInterceptor
     * @see BeforeAdvice
     */
    @Override
    public Advice getAdvice() {
        return advice;
    }

    /**
     * Get the Pointcut that drives this advisor.
     */
    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }


}
