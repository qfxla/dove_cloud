package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 鸽笼日结表
 * </p>
 *
 * @author zcj
 * @since 2021-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_cage_daily")
@ApiModel(value = "CageDaily对象", description = "鸽笼日结表")
public class CageDaily extends Model<CageDaily> {

    private static final long serialVersionUID = 1L;

    //主键id
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    //对应鸽笼表的id
    @ApiModelProperty(value = "对应鸽笼表的id")
    @TableField("cage_id")
    private Long cageId;

    //对应鸽棚表的id
    @ApiModelProperty(value = "对应鸽棚表的id")
    @TableField("dovecote_id")
    private Long dovecoteId;

    //光蛋次数累计
    @ApiModelProperty(value = "光蛋次数累计")
    @TableField("unfertilized_egg")
    private Integer unfertilizedEgg;

    //踩蛋次数累计
    @ApiModelProperty(value = "踩蛋次数累计")
    @TableField("damaged_egg")
    private Integer damagedEgg;

    //臭蛋个数累计
    @ApiModelProperty(value = "臭蛋个数累计")
    @TableField("bad_egg")
    private Integer badEgg;

    //产蛋周期
    @ApiModelProperty(value = "产蛋周期")
    @TableField("cycle")
    private Integer cycle;

    //接下来要进行的操作
    @ApiModelProperty(value = "接下来要进行的操作")
    @TableField("next_operation")
    private String nextOperation;

    //下一步操作时间
    @ApiModelProperty(value = "下一步操作时间")
    @TableField("next_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date nextTime;

    //饲养员
    @ApiModelProperty(value = "饲养员")
    @TableField("breeder")
    private String breeder;

    //垫蛋时间
    @ApiModelProperty(value = "垫蛋时间")
    @TableField("layegg_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date layeggTime;

    //所属企业id
    @ApiModelProperty(value = "所属企业id")
    @TableField("guige")
    private Long guige;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date gmtCreate;

    //修改时间
    @ApiModelProperty(value = "修改时间")
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
