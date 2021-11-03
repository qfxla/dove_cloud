package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zcj
 * @since 2021-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_operator")
@ApiModel(value = "Operator对象", description = "")
public class Operator extends Model<MonitorBase> {

    private static final long serialVersionUID = 1L;

    //id
    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //基地id
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    //鸽棚编号
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    //名字
    @ApiModelProperty(value = "名字")
    private String name;

    //手机号

    @ApiModelProperty(value = "手机号")
    private Long phone;

    //性别
    @ApiModelProperty(value = "性别")
    private Boolean sex;

    //企业id
    @ApiModelProperty(value = "企业id")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    //更新时间
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    @TableField("is_deleted")
    private Integer deleted;

    //乐观锁
    @ApiModelProperty(value = "乐观锁")
    @Version
    @TableField("version")
    private Integer version;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
