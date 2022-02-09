package com.nicht.smallspringstep10.springframework.context;

import java.util.EventObject;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/8
 * @Link
 */
public abstract class ApplicationEvent  extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
