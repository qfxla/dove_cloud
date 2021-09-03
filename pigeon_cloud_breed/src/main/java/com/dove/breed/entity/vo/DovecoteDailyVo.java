package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-09-03-12:00
 */
@Data
public class DovecoteDailyVo {

    @ApiModelProperty(value = "基地id")
    @TableField("baseId")
    private Long baseId;

    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ApiModelProperty(value = "垫蛋数")
    private Integer matEggs;

    @ApiModelProperty(value = "照蛋数")
    private Integer pictureEggs;

    @ApiModelProperty(value = "抽蛋数")
    private Integer takeEggs;

    @ApiModelProperty(value = "单蛋数")
    private Integer singleEggs;

    @ApiModelProperty(value = "未受精数")
    private Integer unfertilizedEggs;

    public DovecoteDailyVo() {
    }

    public DovecoteDailyVo(Long baseId, String dovecoteNumber, Integer matEggs, Integer pictureEggs, Integer takeEggs, Integer singleEggs, Integer unfertilizedEggs, Integer damagedEggs, Integer badEggs) {

        this.baseId = baseId;
        this.dovecoteNumber = dovecoteNumber;
        this.matEggs = matEggs;
        this.pictureEggs = pictureEggs;
        this.takeEggs = takeEggs;
        this.singleEggs = singleEggs;
        this.unfertilizedEggs = unfertilizedEggs;
        this.damagedEggs = damagedEggs;
        this.badEggs = badEggs;
    }

    @ApiModelProperty(value = "总踩蛋数")
    private Integer damagedEggs;

    @ApiModelProperty(value = "臭蛋数")
    private Integer badEggs;

}
