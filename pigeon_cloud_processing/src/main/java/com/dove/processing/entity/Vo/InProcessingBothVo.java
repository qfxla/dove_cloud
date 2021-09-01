package com.dove.processing.entity.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 加工厂入库单表
 * </p>
 *
 * @author WTL
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_in_processing_bill")
@ApiModel(value = "InProcessingBill对象", description = "加工厂入库单表")
public class InProcessingBothVo extends Model<InProcessingBothVo> {

    private static final long serialVersionUID = 1L;


    //入库仓库
    @ApiModelProperty(value = "入库仓库")
    @TableField("in_storage")
    @ExcelProperty(value = "入库仓库",index = 1)
    private String inStorage;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    @ExcelProperty(value = "总数量",index = 6)
    private Integer amount;

    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    @ExcelProperty(value = "总金额",index = 7)
    private Integer total;

    //备注
    @ApiModelProperty(value = "出库备注")
    @TableField("total_remark")
    @ExcelProperty(value = "出库备注",index = 8)
    private String totalRemark;

    //入库时间
    @ApiModelProperty(value = "入库时间")
    @TableField("enter_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @ExcelProperty(value = "入库时间",index = 9)
    private Date enterTime;

    //名称
    @ApiModelProperty(value = "名称")
    @TableField("material_name")
    @ExcelProperty(value = "名称",index = 0)
    private String materialName;

    //规格
    @ApiModelProperty(value = "规格")
    @TableField("specification")
    @ExcelProperty(value = "规格",index = 2)
    private String specification;

    //单价
    @ApiModelProperty(value = "单价")
    @TableField("price")
    @ExcelProperty(value = "单价",index = 3)
    private BigDecimal price;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("count")
    @ExcelProperty(value = "数量",index = 4)
    private Integer count;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    @ExcelProperty(value = "原料信息备注",index = 5)
    private String remark;
}
