package com.dove.processing.entity.Vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 加工厂入库信息表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_in_processing_info")
@ApiModel(value = "InProcessingInfo对象", description = "加工厂入库信息表")
public class InProcessingInfoBindBillVo extends Model<InProcessingInfoBindBillVo> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "id")
    private Long id;

    //与对应入库单绑定
    @ApiModelProperty(value = "与对应入库单绑定")
    @TableField("in_id")
    private Long inId;

    //产品编号
    @ApiModelProperty(value = "产品编号")
    @TableField("type_id")
    private Long typeId;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("material_name")
    private String materialName;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specification")
    private String specification;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("price")
    private BigDecimal price;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("count")
    private Integer count;

    //总价
    @ApiModelProperty(value = "总价")
    @TableField("total")
    private BigDecimal total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //来源（商家id）
    @ApiModelProperty(value = "来源（商家id）")
    @TableField("source")
    private Long source;

    //加工批次id
    @ApiModelProperty(value = "加工批次id")
    @TableId(value = "batch_id")
    private Long batchId;

    //入库仓库
    @ApiModelProperty(value = "入库仓库")
    @TableField("in_storage")
    private String inStorage;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total_money")
    private Integer totalMoney;

    //备注
    @ApiModelProperty(value = "出库备注")
    @TableField("total_remark")
    private String totalRemark;

    //入库时间
    @ApiModelProperty(value = "入库时间" ,example = "2019-06-24 14:18:35")
    @TableField("enter_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date enterTime;

}
