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
 * 
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@TableName("t_cage")
@ApiModel(value="Cage对象", description="")
public class Cage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    private String cageId;

    private Integer state;

    private Integer abnormal;

    private Integer xf;

    private Date createTime;

    private Integer isDeleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(Integer abnormal) {
        this.abnormal = abnormal;
    }

    public Integer getXf() {
        return xf;
    }

    public void setXf(Integer xf) {
        this.xf = xf;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Cage{" +
        "id=" + id +
        ", cageId=" + cageId +
        ", state=" + state +
        ", abnormal=" + abnormal +
        ", xf=" + xf +
        ", createTime=" + createTime +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
