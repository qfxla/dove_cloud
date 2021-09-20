package com.dove.breed.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zcj
 * @creat 2021-09-15-20:38
 */
@Data
public class CageRealVo {
    private Long cageId;

    private Integer state;

    private String abnormal;

    private Integer xf;

    private Date updateTime;

    private String bcNo;

    private Integer rowNo;

    private Integer line;

    private Integer columnNo;

    private String Position;

    //下面字段是近一个月异常
    private Integer totalAbnormal;

    private Integer singleEgg;

    private Integer oneUnfertilized;

    private Integer twoUnfertilized;

    private Integer oneDamaged;

    private Integer twoDamaged;

    private Integer oneBad;

    private Integer twoBad;
}
