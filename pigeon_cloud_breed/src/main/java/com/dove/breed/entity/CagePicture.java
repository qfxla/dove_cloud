package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 鸽笼图片表
 * </p>
 *
 * @author zcj
 * @since 2021-09-23
 */
@TableName("t_cage_picture")
@ApiModel(value="CagePicture对象", description="鸽笼图片表")
public class CagePicture implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "鸽笼id")
        private Long cageId;

        @ApiModelProperty(value = "图片地址")
        private String pic;

        @ApiModelProperty(value = "py处理完图片")
        private String processPic;

        @ApiModelProperty(value = "创建时间")
        private Date gmtCreate;

        @ApiModelProperty(value = "修改时间")
        private Date gmtModified;

        @ApiModelProperty(value = "逻辑删除")
        private Boolean isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCageId() {
        return cageId;
    }

    public void setCageId(Long cageId) {
        this.cageId = cageId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getProcessPic() {
        return processPic;
    }

    public void setProcessPic(String processPic) {
        this.processPic = processPic;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CagePicture{" +
        "id=" + id +
        ", cageId=" + cageId +
        ", pic=" + pic +
        ", processPic=" + processPic +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
