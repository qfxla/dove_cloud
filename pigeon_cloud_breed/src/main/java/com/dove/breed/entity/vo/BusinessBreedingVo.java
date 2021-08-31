package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-21:27
 */
@Data
public class BusinessBreedingVo {
    //商家id
    @ApiModelProperty(value = "商家id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //商家名称
    @ApiModelProperty(value = "商家名称")
    @TableField("name")
    private String name;

    //商家联系方式
    @ApiModelProperty(value = "商家联系方式")
    @TableField("phone")
    private String phone;

    //联系人
    @ApiModelProperty(value = "联系人")
    @TableField("contacts")
    private String contacts;

    //商家类型(供应商/销售客户)
    @ApiModelProperty(value = "商家类型(供应商/销售客户)")
    @TableField("type")
    private String type;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
