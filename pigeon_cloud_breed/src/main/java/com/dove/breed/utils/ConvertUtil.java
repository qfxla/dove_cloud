package com.dove.breed.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dove.entity.GlobalException;
import com.dove.entity.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcj
 * @creat 2021-08-21-17:13
 */
@Component
public class ConvertUtil {
    public <T,S> List<T> convert(List<S> source, Class<? extends T> targetClass){
        List<T> voList = new ArrayList<>();
        source.forEach(po -> {
            T vo = convert(po,targetClass);
            voList.add(vo);
        });
        return voList;
    }

    public <T,S> IPage<T> convert(IPage<S> source, Class<? extends T> targetClass){
        IPage<T> voiPage = new Page<>();
        BeanUtils.copyProperties(source,voiPage);
        List<T> records = new ArrayList<>();
        source.getRecords().forEach(po -> {
            T vo = convert(po,targetClass);
            records.add(vo);
        });
        return voiPage.setRecords(records);
    }

    public <T,S> T convert(S source,Class<? extends T> targetClass){
        if (source == null){
            return null;
        }
        T vo = create(targetClass);
        BeanUtils.copyProperties(source,vo);
        return vo;
    }

    private  <T> T create(Class<? extends T> targetClass){
        T vo = null;
        try {
            vo = targetClass.newInstance();
        } catch (Exception e) {
            throw new GlobalException(StatusCode.ERROR,"数据转换异常");
        }
        return vo;
    }
}
