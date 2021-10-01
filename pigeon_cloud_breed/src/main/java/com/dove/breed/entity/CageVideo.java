package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 鸽笼视频表
 * </p>
 *
 * @author zcj
 * @since 2021-09-28
 */
@Data
@TableName("t_cage_video")
@ApiModel(value="CageVideo对象", description="鸽笼视频表")
public class CageVideo implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "鸽笼id")
        private Long cageId;

        @ApiModelProperty(value = "视频路径")
        private String video;

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

    public CageVideo(Long cageId, String video) {
        this.cageId = cageId;
        this.video = video;
    }

    public CageVideo() {
    }
}
