package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.sql.Blob;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 养殖基地信息表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_breed_base")
@ApiModel(value = "BreedBase对象", description = "养殖基地信息表")
public class BreedBase extends Model<BreedBase> {

    private static final long serialVersionUID = 1L;

    //养殖基地id
    @ApiModelProperty(value = "养殖基地id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //养殖基地所属企业id
    @ApiModelProperty(value = "养殖基地所属企业id")
    @TableField("enterprise_id")
    private Long enterpriseId;

    //养殖基地名称
    @ApiModelProperty(value = "养殖基地名称")
    @TableField("name")
    private String name;

    //养殖基地负责人名称
    @ApiModelProperty(value = "养殖基地负责人名称")
    @TableField("principal_name")
    private String principalName;

    //养殖基地简介
    @ApiModelProperty(value = "养殖基地简介")
    @TableField("introduction")
    private String introduction;

    //养殖基地所在省份
    @ApiModelProperty(value = "养殖基地所在省份")
    @TableField("province")
    private String province;

    //养殖基地所在省份
    @ApiModelProperty(value = "养殖基地所在省份")
    @TableField("city")
    private String city;

    //养殖基地所在详细地址
    @ApiModelProperty(value = "养殖基地所在详细地址")
    @TableField("detailed_address")
    private String detailedAddress;

    //养殖基地图片
    @ApiModelProperty(value = "养殖基地图片")
    @TableField("picture")
    private String picture;

    //养殖基地视频
    @ApiModelProperty(value = "养殖基地视频")
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

    //逻辑删除(0否,1是)
    @ApiModelProperty(value = "逻辑删除(0否,1是)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField("version")
    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
