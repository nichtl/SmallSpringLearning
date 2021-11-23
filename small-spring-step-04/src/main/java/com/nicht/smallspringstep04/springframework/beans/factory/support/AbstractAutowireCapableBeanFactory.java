package com.nicht.smallspringstep04.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.nicht.smallspringstep04.springframework.beans.BeansException;
import com.nicht.smallspringstep04.springframework.beans.PropertyValue;
import com.nicht.smallspringstep04.springframework.beans.PropertyValues;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanDefinition;
import com.nicht.smallspringstep04.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.BeanUtils;

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
       //填充属性
       applyPropertyValues(beanName,bean,beanDefinition);
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
    /*
    * Bean 属性填充
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
}
