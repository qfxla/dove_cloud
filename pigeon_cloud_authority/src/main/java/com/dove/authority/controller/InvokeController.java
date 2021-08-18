package com.dove.authority.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.dove.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author run
 * @since 2021/4/14 20:20
 */
@Api(tags = "服务调用相关接口")
@RestController
@CrossOrigin
@RequestMapping("/authority/invoke")
public class InvokeController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Value("${spring.redis.authority-invoke-key}")
    private String INVOKE_KEY;

    @ApiOperation("生成服务调用密钥")
    @GetMapping("")
    public Result generateSecretKey(){
        String uuid = IdWorker.get32UUID();
        redisTemplate.opsForValue().set(INVOKE_KEY, encoder.encode(uuid));
        return Result.success().data(uuid);
    }
}
