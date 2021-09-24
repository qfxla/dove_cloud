package com.dove.breed.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: xbx
 */
@AllArgsConstructor
@Getter
public enum MonitorEnum {

    MONITOR_URL("url","https://open.ys7.com/api/lapp/token/get"),
    MONITOR_USER("appKey","e2524e462bdb437f8911e09715a3ed95"),
    MONITOR_PASSWORD("appSecret","1b4c122c75ab3526de56be078bad3a05");

    private String key;
    private String value;
}