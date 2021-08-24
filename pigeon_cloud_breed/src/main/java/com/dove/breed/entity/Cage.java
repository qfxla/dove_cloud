package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 鸽笼表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_cage")
@ApiModel(value = "Cage对象", description = "鸽笼表")
public class Cage extends Model<Cage> {

    private static final long serialVersionUID = 1L;

    //主键
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    //鸽笼id:A1030123
    @ApiModelProperty(value = "鸽笼id:A1030123")
    @TableField("cage_id")
    private String cageId;

    //鸽笼状态
    @ApiModelProperty(value = "鸽笼状态")
    @TableField("state")
    private Boolean state;

    //鸽笼状态对应的异常
    @ApiModelProperty(value = "鸽笼状态对应的异常")
    @TableField("abnormal")
    private Boolean abnormal;

    //提示灯亮起状态
    @ApiModelProperty(value = "提示灯亮起状态")
    @TableField("xf")
    private Boolean xf;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    //逻辑删除
    @ApiModelProperty(value = "逻辑删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
