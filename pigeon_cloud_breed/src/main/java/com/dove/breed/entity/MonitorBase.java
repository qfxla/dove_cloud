package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * 摄像头信息
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_monitor_base")
@ApiModel(value="MonitorBase对象", description="摄像头信息")
public class MonitorBase extends Model<MonitorBase> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "基地编号")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "视频名称")
    private String videoName;

    @ApiModelProperty(value = "视频类型(基地，鸽棚，投喂机)")
    private Integer type;

    @ApiModelProperty(value = "摄像头监控位置")
    private String monitoringLocation;

    @ApiModelProperty(value = "视频路径")
    private String videoUrl;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "设备序列号")
    private String deviceSerial;

    @ApiModelProperty(value = "设备验证码")
    private String validateCode;

    @ApiModelProperty(value = "设备使用状态")
    private Integer statusCode;

    @ApiModelProperty(value = "身份令牌")
    private String accessToken;

    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @ApiModelProperty(value = "是否已删除")
    @TableLogic
    @TableField("is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "乐观锁")
    @Version
    @TableField("is_deleted")
    private Integer version;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}