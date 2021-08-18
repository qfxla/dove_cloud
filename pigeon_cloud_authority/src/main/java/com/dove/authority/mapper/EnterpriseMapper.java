package com.dove.authority.mapper;

import com.dove.authority.entity.Enterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dove.authority.entity.vo.EnterpriseVo;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author run
 * @since 2021-03-21
 */
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    @Select("SELECT `name`, `abbreviation`, `business_registration_number`, `credential_validity_date`, `contact_name`" +
            ", `contact_information`, `management_type`, `legal_representative`, `management_character`" +
            ", `vegetables_commerce_department_coding`, `meat_commerce_department_coding`, `enterprise_dominant_code`" +
            ", `showcase_template`, `records_date`, `store_number`, `receive_address`, `longitude`, `latitude`" +
            ", `pictures`, `video`, `video_name`, `wechat_official_accounts`, `food_business_certificate`" +
            ", `food_manufacture_certificate`, `food_circulate_certificate`, `catering_service_license`" +
            ", `authentication`, `introduction` FROM t_authority_enterprise WHERE id = #{enterpriseId}")
    public EnterpriseVo getEnterpriseInfo(Long enterpriseId);
}
