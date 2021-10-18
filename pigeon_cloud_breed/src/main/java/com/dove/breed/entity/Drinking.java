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
 * 饮水信息表
 * </p>
 *
 * @author zcj
 * @since 2021-10-13
 */
@TableName("t_drinking")
@ApiModel(value="Drinking对象", description="饮水信息表")
public class Drinking implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "主键")
        @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "基地编号")
        private Long baseId;

        @ApiModelProperty(value = "开始时间")
        private Date startTime;

        @ApiModelProperty(value = "结束时间")
        private Date endTime;

        @ApiModelProperty(value = "操作人")
        private String operator;

        @ApiModelProperty(value = "企业id")
        private Long guige;

        @ApiModelProperty(value = "创建时间")
        private Date gmtCreate;

        @ApiModelProperty(value = "更新时间")
        private Date gmtModified;

        @ApiModelProperty(value = "逻辑删除")
        private Boolean isDeleted;

        @ApiModelProperty(value = "版本号")
        @Version
    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDovecoteNumber() {
        return dovecoteNumber;
    }

    public void setDovecoteNumber(String dovecoteNumber) {
        this.dovecoteNumber = dovecoteNumber;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Long getGuige() {
        return guige;
    }

    public void setGuige(Long guige) {
        this.guige = guige;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Drinking{" +
        "id=" + id +
        ", dovecoteNumber=" + dovecoteNumber +
        ", baseId=" + baseId +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", operator=" + operator +
        ", guige=" + guige +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", isDeleted=" + isDeleted +
        ", version=" + version +
        "}";
    }
}
