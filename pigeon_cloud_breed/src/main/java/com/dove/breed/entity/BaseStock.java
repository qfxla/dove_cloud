package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 基地库存表
 * </p>
 *
 * @author zcj
 * @since 2021-09-06
 */
@Data
@TableName("t_base_stock")
@ApiModel(value="BaseStock对象", description="基地库存表")
public class BaseStock implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "基地id")
        private Long baseId;

        @ApiModelProperty(value = "基地名称")
        private String baseName;

        @ApiModelProperty(value = "类型")
        private String type;

        @ApiModelProperty(value = "类型id")
        private Long typeId;

        @ApiModelProperty(value = "类型名称")
        private String typeName;

        @ApiModelProperty(value = "剩余量")
        private Integer amount;

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

}
