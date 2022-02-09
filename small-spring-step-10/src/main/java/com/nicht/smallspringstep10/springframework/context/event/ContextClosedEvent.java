package com.nicht.smallspringstep10.springframework.context.event;

/**
 * @Description  ContextClosedEvent、ContextRefreshedEvent，分别是 Spring 框架自己实现的两
 * 个事件类，可以用于监听刷新和关闭动作。
 * @Author Administrator
 * @Time 2021/12/8
 * @Link
 */
public class ContextClosedEvent  extends  ApplicationContextEvent{

    public ContextClosedEvent(Object source) {
        super(source);
    }


}
