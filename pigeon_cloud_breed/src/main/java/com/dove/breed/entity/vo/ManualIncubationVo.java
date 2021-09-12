package com.dove.breed.entity.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-09-09-20:09
 */
@Data
public class ManualIncubationVo {
    @ExcelProperty(value = "id" ,index = 0)
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ExcelProperty(value = "基地id" ,index = 1)
    @ApiModelProperty(value = "基地id")
    private Long baseId;

    @ExcelProperty(value = "鸽棚编号" ,index = 2)
    @ApiModelProperty(value = "鸽棚编号")
    private String dovecoteNumber;

    @ExcelProperty(value = "饲养员" ,index = 3)
    @ApiModelProperty(value = "饲养员")
    private String breeder;

    @ExcelProperty(value = "入孵数量" ,index = 4)
    @ApiModelProperty(value = "入孵数量")
    private Integer one;

    @ExcelProperty(value = "光蛋数量" ,index = 5)
    @ApiModelProperty(value = "光蛋数量")
    private Integer two;

    @ExcelProperty(value = "出仔数量" ,index = 6)
    @ApiModelProperty(value = "出仔数量")
    private Integer three;

    @ExcelProperty(value = "回头蛋数量" ,index = 7)
    @ApiModelProperty(value = "回头蛋数量")
    private Integer four;

    @ExcelProperty(value = "时段" ,index = 8)
    @ApiModelProperty(value = "时段(上午下午)")
    private String timeFrame;

    @ExcelProperty(value = "时间" ,index = 9)
    @ApiModelProperty(value = "人工填写的时间")
    private Date laborTime;

    @ExcelIgnore
    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ExcelIgnore
    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
}
