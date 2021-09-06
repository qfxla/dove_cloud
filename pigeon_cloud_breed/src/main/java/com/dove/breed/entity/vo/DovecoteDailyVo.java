package com.dove.breed.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
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

    @ExcelProperty(value = "id" ,index = 0)
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ExcelProperty(value = "垫蛋数" ,index = 1)
    @ApiModelProperty(value = "垫蛋数")
    private Integer matEggs;

    @ExcelProperty(value = "照蛋数" ,index = 2)
    @ApiModelProperty(value = "照蛋数")
    private Integer pictureEggs;

    @ExcelProperty(value = "抽蛋数" ,index = 3)
    @ApiModelProperty(value = "抽蛋数")
    private Integer takeEggs;

    @ExcelProperty(value = "单蛋数" ,index = 4)
    @ApiModelProperty(value = "单蛋数")
    private Integer singleEggs;

    @ExcelProperty(value = "未受精数" ,index = 5)
    @ApiModelProperty(value = "未受精数")
    private Integer unfertilizedEggs;

    @ExcelProperty(value = "修改时间" ,index = 6)
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

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
