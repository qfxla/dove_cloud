package com.dove.breed.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dove.breed.entity.MonitorBase;
import com.dove.breed.entity.dto.MonitorBaseDto;
import com.dove.breed.entity.vo.MonitorBaseVo;
import com.dove.breed.mapper.MonitorBaseMapper;
import com.dove.breed.service.MonitorBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.MonitorEnum;
import com.dove.util.SecurityContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 摄像头信息 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Transactional()
@Service
@Slf4j
public class MonitorBaseServiceImpl extends ServiceImpl<MonitorBaseMapper, MonitorBase> implements MonitorBaseService {
    @Resource
    private MonitorBaseMapper monitorBaseMapper;

    private ConvertUtil convertUtil = new ConvertUtil();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean add(MonitorBaseDto monitorBaseDto){
        MonitorBase monitorVideo = convertUtil.convert(monitorBaseDto, MonitorBase.class);
        monitorVideo.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
        return monitorBaseMapper.insert(monitorVideo) >0;
    }

    @Override
    public void upData(Long id, MonitorBaseDto monitorBaseDto) {
        MonitorBase monitorVideo = convertUtil.convert(monitorBaseDto, MonitorBase.class);
        monitorVideo.setId(id);
        monitorBaseMapper.updateById(monitorVideo);
    }

    @Override
    public List<MonitorBaseVo> listByType(Long baseId, Integer type, String dovecoteNumber, Integer statusCode, Long enterpriseId) {
        return monitorBaseMapper.listByType(baseId, type, dovecoteNumber, statusCode, enterpriseId);
    }

    @Override
    public boolean updateToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        MultiValueMap<String, Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add(MonitorEnum.MONITOR_USER.getKey(), MonitorEnum.MONITOR_USER.getValue());
        postParameters.add(MonitorEnum.MONITOR_PASSWORD.getKey(), MonitorEnum.MONITOR_PASSWORD.getValue());
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(postParameters, headers);
        RestTemplate client = new RestTemplate();
        JSONObject s = client.postForObject(MonitorEnum.MONITOR_URL.getValue(), httpEntity, JSONObject.class);
        assert s != null;
        JSONObject data = s.getJSONObject("data");
        log.info(data.toString());
        String accessToken = data.getString("accessToken");
        redisTemplate.opsForValue().set("accessToken",accessToken,7, TimeUnit.DAYS);
        return true;
    }

    @Override
    public MonitorBaseVo getVoById(Long id) {
        MonitorBaseVo monitorBaseVo = monitorBaseMapper.getVoById(id);
        System.out.println("66");
        System.out.println(redisTemplate.hasKey("accessToken"));
        String accessToken = (String) redisTemplate.opsForValue().get("accessToken");
        monitorBaseVo.setUrl("ezopen://open.ys7.com/"+monitorBaseVo.getDeviceSerial()+"/"+monitorBaseVo.getAisle()+".live&autoplay=1");
        monitorBaseVo.setAccessToken(accessToken);
        monitorBaseVo.setTemplete(2);
        return monitorBaseVo;
    }
}