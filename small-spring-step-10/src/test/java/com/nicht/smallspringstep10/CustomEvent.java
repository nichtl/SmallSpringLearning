package com.nicht.smallspringstep10;

import com.nicht.smallspringstep10.springframework.context.ApplicationEvent;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/12/10
 * @Link
 */
public class CustomEvent  extends ApplicationEvent {
    private String name ;
    private String age;
    private String msg;


    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomEvent(Object source,String name,String age,String msg) {
        super(source);
        this.name=name;
        this.age =age;
        this.msg =msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
