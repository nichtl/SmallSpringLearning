package com.nicht.smallspringstep10.springframework.context.event;

import cn.hutool.core.thread.RejectPolicy;
import com.nicht.smallspringstep10.springframework.beans.factory.BeanFactory;
import com.nicht.smallspringstep10.springframework.context.ApplicationEvent;
import com.nicht.smallspringstep10.springframework.context.ApplicationListener;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/10
 * @Link
 */
public class SimpleApplicationEventMulticaster  extends  AbstractApplicationEventMulticaster{
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    /**
     * 一个广播事件的方法
     * multicastEvent 最终推送事件消息也会经过这个接口方法来处理谁接收处理事件
     * Multicast the given application event to appropriate listeners.
     *
     * @param event the event to multicast
     */
    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            getThreadPoolExecutor().execute(new ListenersRunable(listener,event));
        }
    }

    public class ListenersRunable  implements  Runnable{
        private ApplicationListener listener;
        private ApplicationEvent event;
        public ListenersRunable(ApplicationListener listener, ApplicationEvent event) {
            this.listener = listener;
            this.event = event;
        }
        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            listener.onApplicationEvent(event);
        }
    }
 }
