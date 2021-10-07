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
 * @since 2021-08-28
 */
@Data
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


}
