package com.nicht.smallspringstep03.springframework.beans.factory.support;

import com.nicht.smallspringstep03.springframework.beans.factory.BeansException;
import com.nicht.smallspringstep03.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public class AbstractAutowireCapableBeanFactory  extends AbstractBeanFactory {
   private  InstantiationStrategy  instantiationStrategy  = new CglibSubclassingInstantiationStrategy();


    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws org.springframework.beans.BeansException {
        return null;
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object... obj) throws BeansException {
     Object  bean  = null;
     try{
       bean = createBeanInstance(beanDefinition,beanName,obj);
     }catch (Exception e){
        throw  new BeansException("InstantiationException",e);
     }
     addSingleton(beanName,bean);
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

}
