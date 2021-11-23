package com.nicht.smallspringstep04.springframework.beans;

/**
 * @Description
 * @Author Administrator
 * @Time 2021/11/16
 * @Link
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }



    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
