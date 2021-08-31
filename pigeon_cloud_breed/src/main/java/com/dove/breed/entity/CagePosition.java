package com.dove.breed.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("t_cage_position")
@ApiModel(value="CagePosition对象", description="")
public class CagePosition implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cage_id", type = IdType.ID_WORKER)
    private Long cageId;

    private Long baseId;

    private String bcNo;

    private String shack;

    private Integer row;

    private Integer line;

    private Integer column;


    public Long getCageId() {
        return cageId;
    }

    public void setCageId(Long cageId) {
        this.cageId = cageId;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

    public String getBcNo() {
        return bcNo;
    }

    public void setBcNo(String bcNo) {
        this.bcNo = bcNo;
    }

    public String getShack() {
        return shack;
    }

    public void setShack(String shack) {
        this.shack = shack;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "CagePosition{" +
        "cageId=" + cageId +
        ", baseId=" + baseId +
        ", bcNo=" + bcNo +
        ", shack=" + shack +
        ", row=" + row +
        ", line=" + line +
        ", column=" + column +
        "}";
    }
}
