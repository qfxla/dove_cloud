package com.dove.authority.service.impl;

import com.dove.authority.service.CaptchaService;
import com.dove.authority.utils.IpAddressUtil;
import com.dove.authority.utils.RedisUtil;
import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

/**
 * @author run
 * @since 2021/3/20 0:10
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${spring.redis.expire-time.captcha}")
    private Long EXPIRE_TIME;

    @Value("${spring.redis.max-count.access.captcha}")
    private Integer ACCESS_MAX_COUNT;

    @Value("${spring.redis.max-count.error.captcha}")
    private Integer ERROR_MAX_COUNT;

    @Value("${spring.redis.content-key.captcha}")
    private String CONTENT_KEY;

    @Value("${spring.redis.count-key.access.captcha}")
    private String ACCESS_COUNT_KEY;

    @Value("${spring.redis.count-key.error.captcha}")
    private String ERROR_COUNT_KEY;

    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public String createVerificationCode(HttpServletRequest request) {
        String ip = IpAddressUtil.getIpAddress(request);

        int count = redisUtil.getCountOfKey(ACCESS_COUNT_KEY + ip);

        if (count >= ACCESS_MAX_COUNT){
            throw new GlobalException(Result.error("访问次数已达到上限，请稍后再试"));
        }

        //先让redis内的验证码失效
        redisTemplate.delete(CONTENT_KEY + ip);
        // 生成文字验证码
        String text = producer.createText().toLowerCase();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            throw new GlobalException(Result.error("获取图片验证码失败，请重试"));
        }

        //将验证码放入redis,并设置有效期
        redisTemplate.opsForValue().set(CONTENT_KEY + ip, text);
        redisTemplate.expire(CONTENT_KEY + ip, EXPIRE_TIME, TimeUnit.SECONDS);


        //增加访问次数
        redisUtil.redisIncrement(ACCESS_COUNT_KEY + ip, EXPIRE_TIME);

        return Base64.getEncoder().encodeToString(outputStream.toByteArray());

    }


    @Override
    public void verifyCode(String ip, String code) {
        if (redisUtil.getCountOfKey(ERROR_COUNT_KEY + ip) >= ERROR_MAX_COUNT){
            throw new GlobalException(Result.error("错误次数已达上限，请稍后再试"));
        }

        String originCode = (String) redisTemplate.opsForValue().get(CONTENT_KEY + ip);

        if (!code.equals(originCode)){
            redisUtil.redisIncrement(ERROR_COUNT_KEY + ip, EXPIRE_TIME);
            throw new GlobalException(Result.error("验证码输入错误"));
        }
    }

}
