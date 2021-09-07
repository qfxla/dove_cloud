package com.dove.authority.controller;

import com.dove.authority.service.CaptchaService;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author run
 * @since 2021/3/20 0:07
 */
@Api(tags = "图片验证码相关的接口")
@RestController
@CrossOrigin
@RequestMapping("/authority/captcha")
public class CaptchaController extends BaseController {

    @Autowired
    private CaptchaService captchaService;


    /**
     * 获取图形验证码
     */
    @GetMapping("")
    @ApiOperation("获取图片验证码")
    public Result captcha() {
        return Result.success().data(captchaService.createVerificationCode(request));
    }
}