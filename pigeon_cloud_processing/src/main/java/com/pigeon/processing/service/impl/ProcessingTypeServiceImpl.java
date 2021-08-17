package com.pigeon.processing.service.impl;

import com.pigeon.processing.entity.ProcessingType;
import com.pigeon.processing.mapper.ProcessingTypeMapper;
import com.pigeon.processing.service.ProcessingTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 加工产品类型表 服务实现类
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Service
public class ProcessingTypeServiceImpl extends ServiceImpl<ProcessingTypeMapper, ProcessingType> implements ProcessingTypeService {

}
