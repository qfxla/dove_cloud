package com.dove.authority.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author run
 * @since 2021/3/19 23:30
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest req, HttpServletResponse res) {
        this.request = req;
        this.response = res;
    }
}
