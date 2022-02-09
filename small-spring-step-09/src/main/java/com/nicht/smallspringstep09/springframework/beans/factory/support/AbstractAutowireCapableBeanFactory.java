package com.nicht.smallspringstep09.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.nicht.smallspringstep09.springframework.beans.BeansException;
import com.nicht.smallspringstep09.springframework.beans.PropertyValue;
import com.nicht.smallspringstep09.springframework.beans.PropertyValues;
import com.nicht.smallspringstep09.springframework.beans.factory.*;
import com.nicht.smallspringstep09.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.nicht.smallspringstep09.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep09.springframework.beans.factory.config.BeanPostProcessor;
import com.nicht.smallspringstep09.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public abstract class AbstractAutowireCapableBeanFactory  extends AbstractBeanFactory implements AutowireCapableBeanFactory {
   private  InstantiationStrategy  instantiationStrategy  = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... obj) throws BeansException {
     Object  bean  = null;
     try{
       bean = createBeanInstance(beanDefinition,beanName,obj);
       //填充属性
       applyPropertyValues(beanName,bean,beanDefinition);
       bean = initializeBeann(beanName,bean,beanDefinition);
     }catch (Exception e){
        throw  new BeansException("InstantiationException",e);
     }
        // 判断 SCOPE_SINGLETON、SCOPE_PROTOTYPE
        if (beanDefinition.isSingleton()) {
            addSingleton(beanName, bean);
        }
     //注册bean的销毁方法
     registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);
    return  bean;
    }




    protected   Object  createBeanInstance(BeanDefinition  beanDefinition,String beanName,Object args[] ){
        Constructor constructortoUser = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();

        for(Constructor ctor :declaredConstructors ){
            //这里只是简单的根据 参数个数进行判断，实际上还需要对类型进行判断(spring 细化到对类型进行判断)
            if(null != args && ctor.getParameterTypes().length == args.length){
                constructortoUser = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition,beanName,constructortoUser,args);
    }
    /*
    * Bean属性填充
    * */
    protected void applyPropertyValues(String beanName, Object bean,BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues =beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue: propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if(value instanceof BeanReference){
                    // A 依赖 B，获取 B 的实例化  、、循环依赖 坑未填
                    BeanReference beanReference = (BeanReference)  value;
                    value = getBean(beanReference.getBeanName());
                }
                BeanUtil.setFieldValue(bean,name,value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
    public Object initializeBeann(String beanName, Object bean, BeanDefinition beanDefinition){
        // invokeAwareMethods
        if(bean instanceof Aware) {
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if(bean  instanceof BeanClassLoaderAware) {
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if(bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }

        }




        //1.执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean,beanName);

        // 实例化待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        // 执行 Bean 对象的初始化方法
        try {
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", e);
        }

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean,beanName);

        return  wrappedBean;
    }
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        // 1. 实现接口 InitializingBean
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }
        // 2. 配置信息 init-method {判断是为了避免二次执行销毁}
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            //当前是调用改bean内的方法，不能调用其他bean的方法
            Method initMethod = beanDefinition.getBeanClass().getMethod(initMethodName);
            if (null == initMethod) {
                throw new BeansException("Could not find an init method named '" +
                        initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
    }

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result =existingBean; //当前bean
        for (BeanPostProcessor processor: getBeanPostProcessors()) {
            //执行在
            Object current = processor.postProcessBeforeInitialization(result,beanName);
            if(null==current) return  result;
            result=current;
        }
        return result;
    }

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result =existingBean; //当前bean
        for (BeanPostProcessor processor: getBeanPostProcessors()) {
            //执行
            Object current = processor.postProcessAfterInitialization(result,beanName);
            if(null==current) return  result;
            result=current;
        }
        return result;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if(!beanDefinition.isSingleton()) return; //非Singleton 类型的 Bean 不执行销毁方法
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }


}
