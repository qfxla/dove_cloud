package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 月底饲料盘点表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_feed_stock")
@ApiModel(value = "FeedStock对象", description = "月底饲料盘点表")
public class FeedStock extends Model<FeedStock> {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //所属基地id
    @ApiModelProperty(value = "所属基地id")
    @TableField("base_id")
    private Long baseId;

    //所属鸽棚id
    @ApiModelProperty(value = "所属鸽棚编号")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //饲料类型
    @ApiModelProperty(value = "饲料类型")
    @TableField("feed_type")
    private String feedType;

    @ApiModelProperty(value = "进购数量")
    @TableField("stock_amount")
    private Integer stockAmount;

    @ApiModelProperty(value = "使用数量")
    @TableField("use_amount")
    private Integer useAmount;

    @ApiModelProperty(value = "剩余数量")
    @TableField("amount")
    private Integer amount;

    @ApiModelProperty(value = "规格")
    @TableField("specifications")
    private String specifications;

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
        return this.id;
    }

}
