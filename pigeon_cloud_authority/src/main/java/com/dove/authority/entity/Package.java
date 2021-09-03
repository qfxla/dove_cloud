package com.dove.authority.entity;

/**
 * @author run
 * @since 2021/3/23 19:41
 */
public class Package <T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
