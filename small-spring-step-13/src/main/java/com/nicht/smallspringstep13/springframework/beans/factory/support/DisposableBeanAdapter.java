package com.nicht.smallspringstep13.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.nicht.smallspringstep13.springframework.beans.BeansException;
import com.nicht.smallspringstep13.springframework.beans.factory.DisposableBean;
import com.nicht.smallspringstep13.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Description 定义销毁方法适配器(接口和配置) 多个实现
 * @Author Administrator
 * @Time 2021/11/24
 * @Link
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private final String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }


    /**
     * Bean 销毁之前调用
     *
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        // 1. 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        //2.配置信息 destroy-method {判断是为了避免二次执行销毁}
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" +
                        destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }

}

