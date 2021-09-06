package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-08-30-19:49
 */
@Data
public class DovecoteEntryBillVo {
    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    @TableField("dovecote_number")
    private String dovecoteNumber;

    //所属基地id
    @ApiModelProperty(value = "所属基地id")
    @TableField("base_id")
    private Long baseId;

<<<<<<< Updated upstream
=======
    //类型
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //数量
    @ApiModelProperty(value = "数量")
    @TableField("amount")
    private Integer amount;

>>>>>>> Stashed changes
    //经手人
    @ApiModelProperty(value = "经手人")
    @TableField("handler")
    private String handler;

<<<<<<< Updated upstream
    @ApiModelProperty(value = "类型")
    @TableField("type")
    private String type;

    //总数量
    @ApiModelProperty(value = "总数量")
    @TableField("amount")
    private Integer amount;

=======
>>>>>>> Stashed changes
    //总金额
    @ApiModelProperty(value = "总金额")
    @TableField("total")
    private Integer total;

    //备注
    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
