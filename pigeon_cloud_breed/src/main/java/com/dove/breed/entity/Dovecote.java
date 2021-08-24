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
 * 鸽棚表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_dovecote")
@ApiModel(value = "Dovecote对象", description = "鸽棚表")
public class Dovecote extends Model<Dovecote> {

    private static final long serialVersionUID = 1L;

    //鸽棚id
    @ApiModelProperty(value = "鸽棚id")
    @TableId(value = "dovecote_id", type = IdType.AUTO)
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
    private Boolean deleted;

    //乐观锁(版本号)
    @ApiModelProperty(value = "乐观锁(版本号)")
    @TableField("version")
    @Version
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.dovecoteId;
    }

}
