package com.nicht.smallspringstep13.springframework.context.event;

import com.nicht.smallspringstep13.springframework.context.ApplicationContext;
import com.nicht.smallspringstep13.springframework.context.ApplicationEvent;

/**
 * @Description ApplicationContextEvent 是定义事件的抽象类，所有的事件包括关闭、刷新，以及
 * 用户自己实现的事件，都需要继承这个类。
 * @Author Administrator
 * @Time 2021/12/8
 * @Link
 */
public class ApplicationContextEvent extends ApplicationEvent {

    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }


}
