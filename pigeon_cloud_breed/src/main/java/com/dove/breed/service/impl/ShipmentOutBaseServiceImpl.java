package com.dove.breed.service.impl;

import com.dove.breed.entity.ShipmentOutBase;
import com.dove.breed.entity.vo.ShipmentOutBaseVo;
import com.dove.breed.mapper.ShipmentOutBaseMapper;
import com.dove.breed.service.ShipmentOutBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 基地出库信息表 服务实现类
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Service
public class ShipmentOutBaseServiceImpl extends ServiceImpl<ShipmentOutBaseMapper, ShipmentOutBase> implements ShipmentOutBaseService {

}
