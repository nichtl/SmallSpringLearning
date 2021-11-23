package com.nicht.smallspringstep03.springframework.beans.factory;

/**
 * @Author Nicht
 * @Description
 * @Time 2021/10/25
 * @Link
 */
public class BeansException extends  RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
