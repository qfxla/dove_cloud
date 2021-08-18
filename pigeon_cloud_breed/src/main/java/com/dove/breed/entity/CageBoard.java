package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 板子鸽笼关联表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_cage_board")
@ApiModel(value = "CageBoard对象", description = "板子鸽笼关联表")
public class CageBoard extends Model<CageBoard> {

    private static final long serialVersionUID = 1L;

    //鸽笼id
    @ApiModelProperty(value = "鸽笼id")
    @TableId(value = "cage_id", type = IdType.ID_WORKER)
    private String cageId;

    //板子bc码+鸽笼在板子上对应编号
    @ApiModelProperty(value = "板子bc码+鸽笼在板子上对应编号")
    @TableField("bc_no")
    private String bcNo;


    @Override
    protected Serializable pkVal() {
        return this.cageId;
    }

}
