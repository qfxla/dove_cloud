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
 * @since 2021-11-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_drinking_machine")
@ApiModel(value="DrinkingMachine对象", description="")
public class DrinkingMachine implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
        private Long id;

        @ApiModelProperty(value = "基地编号")
        private Long baseId;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "饮水机编号")
        private String machineNumber;

        @ApiModelProperty(value = "峰值扬程")
        private String peakHead;

        @ApiModelProperty(value = "峰值吸程")
        private String peakSuction;

        @ApiModelProperty(value = "峰值流量")
        private String peakFlow;

        @ApiModelProperty(value = "转速")
        private String rev;

        @ApiModelProperty(value = "启动时间(开启的时候字段显示当前启动时间，关闭的时候显示上一次启动时间)")
        private Date startTime;

        @ApiModelProperty(value = "停止时间（开启的时候字段显示为空，关闭的时候显示上一次停止时间）")
        private Date stopTime;

        @ApiModelProperty(value = "开关（0关，1开）")
        @TableField("is_open")
        private Boolean open;

        @ApiModelProperty(value = "企业id")
        private Long guige;

        @ApiModelProperty(value = "创建时间")
        @TableField(fill = FieldFill.INSERT)
        private Date gmtCreate;

        @ApiModelProperty(value = "更新时间")
        @TableField(fill = FieldFill.INSERT_UPDATE)
        private Date gmtModified;

        @ApiModelProperty(value = "逻辑删除(0否,1是)")
        @TableField("is_deleted")
        @TableLogic
        private Long deleted;

        @ApiModelProperty(value = "版本号")
        @Version
        private Integer version;


}
