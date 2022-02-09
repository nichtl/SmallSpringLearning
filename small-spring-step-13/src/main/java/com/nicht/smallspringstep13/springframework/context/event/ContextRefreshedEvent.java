package com.nicht.smallspringstep13.springframework.context.event;

/**
 * @Description  ContextClosedEvent、ContextRefreshedEvent，分别是 Spring 框架自己实现的两
 * 个事件类，可以用于监听刷新和关闭动作。
 * @Author Administrator
 * @Time 2021/12/9
 * @Link
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
