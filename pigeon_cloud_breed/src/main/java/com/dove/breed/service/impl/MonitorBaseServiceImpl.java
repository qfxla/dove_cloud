package com.dove.breed.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dove.breed.entity.MonitorBase;
import com.dove.breed.entity.dto.MonitorBaseDto;
import com.dove.breed.entity.vo.MonitorBaseVo;
import com.dove.breed.mapper.MonitorBaseMapper;
import com.dove.breed.service.MonitorBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dove.breed.utils.ConvertUtil;
import com.dove.breed.utils.MonitorEnum;
import com.dove.util.SecurityContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.Date;
import java.util.List;

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
public class MonitorBaseServiceImpl extends ServiceImpl<MonitorBaseMapper, MonitorBase> implements MonitorBaseService {
    @Resource
    private MonitorBaseMapper monitorBaseMapper;

    private ConvertUtil convertUtil = new ConvertUtil();

    @Override
    public boolean add(MonitorBaseDto monitorBaseDto){
        MonitorBase monitorVideo = convertUtil.convert(monitorBaseDto, MonitorBase.class);
        monitorVideo.setId(null);
        monitorVideo.setGuige(SecurityContextUtil.getUserDetails().getEnterpriseId());
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
        String accessToken = data.getString("accessToken");
        monitorVideo.setAccessToken(accessToken);
        String url1="https://open.ys7.com/ezopen/h5/iframe_se?url=ezopen://"+monitorVideo.getValidateCode()+"@open.ys7.com/"+monitorVideo.getDeviceSerial()+"/1.live&autoplay=0&audio=1&accessToken="+monitorVideo.getAccessToken()+"&templete=2";
        monitorVideo.setVideoUrl(url1);
        return monitorBaseMapper.insert(monitorVideo) >0;
    }

    @Override
    public void upData(MonitorBaseDto monitorBaseDto) {
        MonitorBase monitorVideo = convertUtil.convert(monitorBaseDto, MonitorBase.class);
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
        String accessToken = data.getString("accessToken");
        monitorVideo.setAccessToken(accessToken);
        String url1="https://open.ys7.com/ezopen/h5/iframe_se?url=ezopen://"+monitorVideo.getValidateCode()+"@open.ys7.com/"+monitorVideo.getDeviceSerial()+"/1.live&autoplay=0&audio=1&accessToken="+monitorVideo.getAccessToken()+"&templete=2";
        monitorVideo.setVideoUrl(url1);
        monitorBaseMapper.updateById(monitorVideo);
    }

    @Override
    public List<MonitorBaseVo> list(Long enterpriseId) {
        return monitorBaseMapper.selectList(enterpriseId);
    }

    @Override
    public List<MonitorBaseVo> listByType(Long baseId, Integer type, String dovecoteNumber, Integer statusCode, Long enterpriseId) {
        return monitorBaseMapper.listByType(baseId, type, dovecoteNumber, statusCode, enterpriseId);
    }

    @Override
    public boolean updateToken(Long id) {
        MonitorBase monitorBase = baseMapper.selectById(id);
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
        String accessToken = data.getString("accessToken");
        monitorBase.setAccessToken(accessToken);
        String url1="https://open.ys7.com/ezopen/h5/iframe_se?url=ezopen://"+monitorBase.getValidateCode()+"@open.ys7.com/"+monitorBase.getDeviceSerial()+"/1.live&autoplay=0&audio=1&accessToken="+monitorBase.getAccessToken()+"&templete=2";
        monitorBase.setVideoUrl(url1);
        return monitorBaseMapper.updateById(monitorBase) > 0;
    }

    @Override
    public List<MonitorBaseVo> getVoById(Long id) {
        return monitorBaseMapper.getVoById(id);
    }
}