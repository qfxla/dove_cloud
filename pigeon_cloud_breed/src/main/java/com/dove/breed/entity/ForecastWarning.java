package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 预测预警表
 * </p>
 *
 * @author zcj
 * @since 2021-10-21
 */
@Data
@TableName("t_forecast_warning")
@ApiModel(value="ForecastWarning对象", description="预测预警表")
public class ForecastWarning implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "基地id")
        private Long baseId;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "鸽笼id")
        private Long cageId;

        @ApiModelProperty(value = "图片地址")
        private String picture;

        @ApiModelProperty(value = "视频地址")
        private String video;

        @ApiModelProperty(value = "视频名称")
        private String videoName;

        @ApiModelProperty(value = "事件起始时间")
        private Date startTime;

        @ApiModelProperty(value = "事件结束时间")
        private Date endTime;

        @ApiModelProperty(value = "信息(鸽子受惊..)")
        private String information;

        @ApiModelProperty(value = "企业id")
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


}
