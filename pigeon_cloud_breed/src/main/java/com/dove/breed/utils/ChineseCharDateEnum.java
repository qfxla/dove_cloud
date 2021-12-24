package com.dove.breed.utils;

/**
 * @Author: xbx
 */
public enum ChineseCharDateEnum {
    CHAR_DATE_ENUM("天"),
    CHAR_WEEK_ENUM("周"),
    CHAR_MONTH_ENUM("月"),
    CHAR_YEAR_ENUM("年");

    private String dateName;

    ChineseCharDateEnum(String dateName) {
        setDateName(dateName);
    }

    ChineseCharDateEnum() {
    }

    public String getDateName() {
        return dateName;
    }

    public void setDateName(String dateName) {
        this.dateName = dateName;
    }
}
