package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zcj
 * @since 2021-09-01
 */
@Data
@TableName("t_cage_real")
@ApiModel(value="CageReal对象", description="")
public class CageReal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cage_id", type = IdType.ID_WORKER)
    private Long cageId;

    private Integer state;

    private String abnormal;

    private Integer xf;

    private String bcNo;

    private Date updateTime;

    private Integer isDeleted;

}
