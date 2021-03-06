package com.nicht.smallspringstep10;

import com.nicht.smallspringstep10.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/10
 * @Link
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(Thread.currentThread().toString());
       System.out.println("收到：" + event.getSource() + "消息;时间" + new Date());
       System.out.println("消息：" + event.getMsg() + ":" + event.getName());
    }
}
