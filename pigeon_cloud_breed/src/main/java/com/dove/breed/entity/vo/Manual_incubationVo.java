package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-09-05-15:16
 */
@Data
public class Manual_incubationVo {
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "饲养员名字")
    private String breederName;

    @ApiModelProperty(value = "类型")
    private int type;

    @ApiModelProperty(value = "上午数量")
    private Integer amNumber;

    @ApiModelProperty(value = "下午数量")
    private Integer pmNumber;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
