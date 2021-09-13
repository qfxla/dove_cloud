package com.dove.breed.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: xbx
 */
@AllArgsConstructor
@Getter
public enum GoFastDfsEnum {

    UPLOAD_PATH("http://120.77.156.205:9800/upload"),
    DELETE_PATH("http://120.77.156.205:9800/delete");

    private String usr;
}