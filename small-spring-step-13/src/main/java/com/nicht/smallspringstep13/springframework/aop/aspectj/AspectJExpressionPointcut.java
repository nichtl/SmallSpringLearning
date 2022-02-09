package com.nicht.smallspringstep13.springframework.aop.aspectj;

import com.nicht.smallspringstep13.springframework.aop.ClassFilter;
import com.nicht.smallspringstep13.springframework.aop.MethodMatcher;
import com.nicht.smallspringstep13.springframework.aop.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/13
 * @Link
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {
    private static final Set<PointcutPrimitive> SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointcut(String expression) {
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMITIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }


    /**
     * Should the pointcut apply to the given interface or target class?
     *
     * @param clazz the candidate target class
     * @return whether the advice should apply to the given target class
     */
    @Override
    public boolean matches(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    /**
     * Perform static checking whether the given method matches. If this
     *
     * @param method
     * @param targetClass
     * @return whether or not this method matches statically
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }

    /**
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never <code>null</code>)
     */
    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never <code>null</code>)
     */
    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }


}
