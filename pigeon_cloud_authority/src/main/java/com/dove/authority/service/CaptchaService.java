package com.dove.authority.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author run
 * @since 2021/3/20 0:10
 */
public interface CaptchaService {

    public String createVerificationCode(HttpServletRequest request);

    public void verifyCode(String ip, String code);
}
