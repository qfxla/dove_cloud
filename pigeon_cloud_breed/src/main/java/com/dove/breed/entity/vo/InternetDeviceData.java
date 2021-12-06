package com.dove.breed.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: xbx
 */
@Data
public class InternetDeviceData {

    private String devicePictureUrl;

    private Date captureTime;

    private String RH;

    private String temperature;

    private String atmosphericPressure;

    private String  lightIntensity;
}
