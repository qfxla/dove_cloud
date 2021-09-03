package com.dove.processing.service.client.impl;

import com.dove.entity.Result;
import com.dove.processing.service.client.AuthorityUserClient;
import org.springframework.stereotype.Component;

/**
 * @author 小亮
 * @date 2021/9/2  -  22:02
 */
@Component
public class AuthorityUserFallback implements AuthorityUserClient {

    /**先写着，以后要调用权限查看用户的信息,在调用*/
    @Override
    public Result searchEnterpriseById(Long id) {
        return Result.error("系统繁忙，获取用户信息失败，请稍后重试");
    }
}
