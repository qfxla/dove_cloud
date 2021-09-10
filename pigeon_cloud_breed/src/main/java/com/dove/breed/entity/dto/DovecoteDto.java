package com.dove.breed.entity.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zcj
 * @creat 2021-08-30-20:01
 */
@Data
public class DovecoteDto {
    //鸽棚编号A1,B2..
    @ApiModelProperty(value = "鸽棚编号A1,B2..")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //所属养殖基地id
    @ApiModelProperty(value = "所属养殖基地id")
    @TableField("base_id")
    private Long baseId;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //鸽棚负责人
    @ApiModelProperty(value = "鸽棚负责人")
    @TableField("director")
    private String director;

    //鸽棚负责人联系电话
    @ApiModelProperty(value = "鸽棚负责人联系电话")
    @TableField("contact_number")
    private String contactNumber;

    //存栏状况(半仓,满仓..)
    @ApiModelProperty(value = "存栏状况(半仓,满仓..)")
    @TableField("living_conditions")
    private String livingConditions;

    //投产种鸽数量
    @ApiModelProperty(value = "投产种鸽数量")
    @TableField("into_production")
    private Integer intoProduction;

    //总笼数
    @ApiModelProperty(value = "总笼数")
    @TableField("cage_amount")
    private Integer cageAmount;

    //空笼数
    @ApiModelProperty(value = "空笼数")
    @TableField("cage_empty")
    private Integer cageEmpty;
}
