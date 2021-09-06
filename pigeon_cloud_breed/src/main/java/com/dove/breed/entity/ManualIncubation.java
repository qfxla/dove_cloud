package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 人工孵化表
 * </p>
 *
 * @author zcj
 * @since 2021-09-04
 */
@Data
@TableName("t_manual_incubation")
@ApiModel(value="ManualIncubation对象", description="人工孵化表")
public class ManualIncubation implements Serializable {

    private static final long serialVersionUID = 1L;

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

        @ApiModelProperty(value = "乐观锁(版本号)")
        @TableField("version")
        @Version
        private Integer version;



}
