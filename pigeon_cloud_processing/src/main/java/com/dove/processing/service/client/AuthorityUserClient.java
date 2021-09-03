package com.dove.processing.service.client;

import com.dove.entity.Result;
import com.dove.processing.service.client.impl.AuthorityUserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 小亮
 * @date 2021/9/2  -  21:53
 */
@FeignClient(value = "dove-gateway-service" ,fallback = AuthorityUserFallback.class)
public interface AuthorityUserClient {
    /**
     * searchEnterpriseById
     *
     * @param id
     * @return
     */
    @GetMapping("/authority/enterprise/{id}")
    public Result searchEnterpriseById(@PathVariable("id") Long id);
}