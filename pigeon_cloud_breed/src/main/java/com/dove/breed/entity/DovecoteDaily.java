package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 鸽棚日结表
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@Data
@TableName("t_dovecote_daily")
@ApiModel(value="DovecoteDaily对象", description="鸽棚日结表")
public class DovecoteDaily implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "日结表id")
        @TableId(value = "id", type = IdType.AUTO)
    private Long id;

        @ApiModelProperty(value = "基地id")
        @TableField("base_id")
    private Long baseId;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "垫蛋数")
        private Integer matEggs;

        @ApiModelProperty(value = "照蛋数")
        private Integer pictureEggs;

        @ApiModelProperty(value = "抽蛋数")
        private Integer takeEggs;

        @ApiModelProperty(value = "单蛋数")
        private Integer singleEggs;

        @ApiModelProperty(value = "未受精数")
        private Integer unfertilizedEggs;

        @ApiModelProperty(value = "总踩蛋数")
        private Integer damagedEggs;

        @ApiModelProperty(value = "臭蛋数")
        private Integer badEggs;

        @ApiModelProperty(value = "创建时间")
        @TableField(value = "gmt_create", fill = FieldFill.INSERT)
        private Date gmtCreate;

        @ApiModelProperty(value = "修改时间")
        @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
        private Date gmtModified;

        //逻辑删除
        @ApiModelProperty(value = "逻辑删除")
        @TableField("is_deleted")
        @TableLogic
        private Boolean deleted;

        @ApiModelProperty(value = "乐观锁(版本号)")
        @TableField("version")
        @Version
        private Integer version;



}
