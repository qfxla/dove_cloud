package com.pigeon.processing.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 加工厂表
 * </p>
 *
 * @author WTL
 * @since 2021-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dove_processing")
@ApiModel(value = "DoveProcessing对象", description = "加工厂表")
public class DoveProcessing extends Model<DoveProcessing> {

    private static final long serialVersionUID = 1L;

    //加工厂id
    @ApiModelProperty(value = "加工厂id")
    @TableId(value = "processing_id", type = IdType.ASSIGN_UUID)
    private Long processingId;

    //加工厂所属企业id
    @ApiModelProperty(value = "加工厂所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //加工厂名称
    @ApiModelProperty(value = "加工厂名称")
    @TableField("name")
    private String name;

    //加工厂负责人姓名
    @ApiModelProperty(value = "加工厂负责人姓名")
    @TableField("principal_name")
    private String principalName;

    //加工厂简介
    @ApiModelProperty(value = "加工厂简介")
    @TableField("introduction")
    private String introduction;

    //加工厂所在省份（默认广东省）
    @ApiModelProperty(value = "加工厂所在省份（默认广东省）")
    @TableField("provice")
    private String provice;

    //加工厂所在市区（默认梅州市）
    @ApiModelProperty(value = "加工厂所在市区（默认梅州市）")
    @TableField("city")
    private String city;

    //加工厂所在详细地址
    @ApiModelProperty(value = "加工厂所在详细地址")
    @TableField("detailed_address")
    private String detailedAddress;

    //加工厂图片
    @ApiModelProperty(value = "加工厂图片")
    @TableField("picture")
    private String picture;

    //加工厂视频
    @ApiModelProperty(value = "加工厂视频")
    @TableField("video")
    private String video;

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

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField("version")
    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.processingId;
    }

}
