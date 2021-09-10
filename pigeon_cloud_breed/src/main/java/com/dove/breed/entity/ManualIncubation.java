package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 孵化机
 * </p>
 *
 * @author zcj
 * @since 2021-09-09
 */
@Data
@TableName("t_manual_incubation")
@ApiModel(value="ManualIncubation对象", description="孵化机")
public class ManualIncubation implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "基地id")
        private Long baseId;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "饲养员")
        private String breeder;

        @ApiModelProperty(value = "入孵数量")
        private Integer one;

        @ApiModelProperty(value = "光蛋数量")
        private Integer two;

        @ApiModelProperty(value = "出仔数量")
        private Integer three;

        @ApiModelProperty(value = "回头蛋数量")
        private Integer four;

        @ApiModelProperty(value = "时段(上午下午)")
        private String timeFrame;

        @ApiModelProperty(value = "人工填写的时间")
        private Date laborTime;

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


}
