package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Blob;
import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:56
 */
@Data
public class DovecoteVo {
    private Long dovecoteId;

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

    //鸽棚图片
    @ApiModelProperty(value = "鸽棚图片")
    @TableField("picture")
    private String picture;

    //鸽棚视频
    @ApiModelProperty(value = "鸽棚视频")
    @TableField("video")
    private Blob video;

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

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
