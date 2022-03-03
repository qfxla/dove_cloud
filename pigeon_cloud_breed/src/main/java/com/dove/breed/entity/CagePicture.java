package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 鸽笼图片表
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@Data
@TableName("t_cage_picture")
@ApiModel(value="CagePicture对象", description="鸽笼图片表")
public class CagePicture implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "鸽笼id")
        private String cageId;

        @ApiModelProperty(value = "图片地址")
        private String pic;

        @ApiModelProperty(value = "图片名称")
        private String picName;

        @ApiModelProperty(value = "上传时间")
        private Date time;

        @ApiModelProperty(value = "py处理完图片")
        private String processPic;

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


}
