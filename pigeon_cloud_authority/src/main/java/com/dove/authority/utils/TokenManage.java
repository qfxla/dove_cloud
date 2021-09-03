package com.dove.authority.utils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author run
 * @since 2021/3/20 16:46
 */
@Component
public class TokenManage {

    @Value("${jwt.expire-time}")
    private Long EXPIRE_TIME;

    @Value("${jwt.salt}")
    private String SALT;

    public String createToken(Long id) {
        long now = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                // JWT的唯一标识，回避重放攻击
                .setId(String.valueOf(id))
                // 签发时间
                .setIssuedAt(new Date(now))
                // 加密算法、密钥
                .signWith(SignatureAlgorithm.HS256, SALT)
                //设置token过期时间
                .setExpiration(new Date(now + EXPIRE_TIME * 1000));

        return builder.compact();
    }
}
