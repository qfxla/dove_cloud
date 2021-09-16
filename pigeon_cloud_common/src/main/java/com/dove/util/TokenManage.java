package com.dove.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @author run
 * @since 2021/3/20 16:46
 */
@Component
public class TokenManage {

    /**
     * 这个秘钥是防止JWT被篡改的关键
     */
    private final static String secretKey = "suyuan711!@#$%^";

    /**
     * 解析JWT
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        System.out.println("解析开启");
        // 如果是空字符串直接返回null
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置秘钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // 这里应该用日志输出，为了演示方便就直接打印了
            System.err.println("解析失败！");
        }
        return claims;
    }

}
