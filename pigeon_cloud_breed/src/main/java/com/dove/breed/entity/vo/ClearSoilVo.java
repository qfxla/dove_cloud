package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 清粪信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-12
 */
@Data
@ApiModel(value = "ClearSoil对象", description = "清粪信息表")
public class ClearSoilVo implements Serializable {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;
    //基地编号
    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //清理时间
    @ApiModelProperty(value = "清理时间")
    private Date clearTime;

    //重量
    @ApiModelProperty(value = "重量")
    private Integer weight;

    //重量的单位
    @ApiModelProperty(value = "重量的单位")
    private String type;

    //操作人
    @ApiModelProperty(value = "操作人")
    private String operator;


}