package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 鸽棚日结表
 * </p>
 *
 * @author zcj
 * @since 2021-09-02
 */
@TableName("t_dovecote_daily")
@ApiModel(value="DovecoteDaily对象", description="鸽棚日结表")
public class DovecoteDaily implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "日结表id")
        @TableId(value = "id", type = IdType.AUTO)
    private Long id;

        @ApiModelProperty(value = "基地id")
        @TableField("baseId")
    private String baseId;

        @ApiModelProperty(value = "鸽棚编号")
        private String dovecoteNumber;

        @ApiModelProperty(value = "垫蛋数")
        private Integer layEggs;

        @ApiModelProperty(value = "照蛋数")
        private Integer takeEggs;

        @ApiModelProperty(value = "单蛋数")
        private Integer singleEggs;

        @ApiModelProperty(value = "未受精数")
        private Integer unfertilizedEggs;

        @ApiModelProperty(value = "总踩蛋数")
        private Integer damagedEggs;

        @ApiModelProperty(value = "臭蛋数")
        private Integer badEggs;

        @ApiModelProperty(value = "创建时间")
        private Date gmtCreate;

        @ApiModelProperty(value = "更新时间")
        private Date gmtUpdate;

        @ApiModelProperty(value = "逻辑删除")
        private Boolean isDeleted;

        @ApiModelProperty(value = "乐观锁")
        @Version
    private Integer version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getDovecoteNumber() {
        return dovecoteNumber;
    }

    public void setDovecoteNumber(String dovecoteNumber) {
        this.dovecoteNumber = dovecoteNumber;
    }

    public Integer getLayEggs() {
        return layEggs;
    }

    public void setLayEggs(Integer layEggs) {
        this.layEggs = layEggs;
    }

    public Integer getTakeEggs() {
        return takeEggs;
    }

    public void setTakeEggs(Integer takeEggs) {
        this.takeEggs = takeEggs;
    }

    public Integer getSingleEggs() {
        return singleEggs;
    }

    public void setSingleEggs(Integer singleEggs) {
        this.singleEggs = singleEggs;
    }

    public Integer getUnfertilizedEggs() {
        return unfertilizedEggs;
    }

    public void setUnfertilizedEggs(Integer unfertilizedEggs) {
        this.unfertilizedEggs = unfertilizedEggs;
    }

    public Integer getDamagedEggs() {
        return damagedEggs;
    }

    public void setDamagedEggs(Integer damagedEggs) {
        this.damagedEggs = damagedEggs;
    }

    public Integer getBadEggs() {
        return badEggs;
    }

    public void setBadEggs(Integer badEggs) {
        this.badEggs = badEggs;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
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
        return "DovecoteDaily{" +
        "id=" + id +
        ", baseId=" + baseId +
        ", dovecoteNumber=" + dovecoteNumber +
        ", layEggs=" + layEggs +
        ", takeEggs=" + takeEggs +
        ", singleEggs=" + singleEggs +
        ", unfertilizedEggs=" + unfertilizedEggs +
        ", damagedEggs=" + damagedEggs +
        ", badEggs=" + badEggs +
        ", gmtCreate=" + gmtCreate +
        ", gmtUpdate=" + gmtUpdate +
        ", isDeleted=" + isDeleted +
        ", version=" + version +
        "}";
    }
}
