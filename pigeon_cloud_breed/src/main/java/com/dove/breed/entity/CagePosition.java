package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zcj
 * @since 2021-08-28
 */
@Data
@TableName("t_cage_position")
@ApiModel(value="CagePosition对象", description="")
public class CagePosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cage_id", type = IdType.ID_WORKER)
    private Long cageId;

    private Long baseId;

    private String bcNo;

    private String shack;

    private Integer rowNo;

    private Integer line;

    private Integer columnNo;


}
