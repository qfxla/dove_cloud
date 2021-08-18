package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商家表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_business_processing")
@ApiModel(value = "BusinessProcessing对象", description = "商家表")
public class BusinessProcessingVo extends Model<BusinessProcessingVo> {

    private static final long serialVersionUID = 1L;

    //商家id
    @ApiModelProperty(value = "商家id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private Long name;

    //商家联系方式
    @ApiModelProperty(value = "商家联系方式")
    @TableField("phone")
    private String phone;

    //联系人
    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;

    //商家类型（供应商或者销售客户）
    @ApiModelProperty(value = "商家类型（供应商或者销售客户）")
    @TableField("type")
    private String type;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

}
