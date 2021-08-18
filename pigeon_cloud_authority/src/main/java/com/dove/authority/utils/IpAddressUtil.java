package com.dove.authority.utils;

import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.dove.entity.StatusCode;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author run
 * @since 2021/3/19 23:43
 */

public class IpAddressUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("xip");
        if (StringUtils.isEmpty(ip)) {
            throw new GlobalException(Result.error(StatusCode.ERROR, "非法访问"));
        }
        return ip;
    }
}