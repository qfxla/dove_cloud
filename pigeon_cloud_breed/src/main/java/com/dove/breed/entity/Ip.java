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
@TableName("t_ip")
@ApiModel(value="Ip对象", description="")
public class Ip implements Serializable {

    private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "板子的ip地址")
        @TableId(value = "ip", type = IdType.ID_WORKER)
    private String ip;

        @ApiModelProperty(value = "端口号")
        private String port;

    private String bc;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getBc() {
        return bc;
    }

    public void setBc(String bc) {
        this.bc = bc;
    }

    @Override
    public String toString() {
        return "Ip{" +
        "ip=" + ip +
        ", port=" + port +
        ", bc=" + bc +
        "}";
    }
}
