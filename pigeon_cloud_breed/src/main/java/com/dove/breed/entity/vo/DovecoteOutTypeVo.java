package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:53
 */
@Data
public class DovecoteOutTypeVo {
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;

    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
