package com.example.iot.nmodel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.iot.utils.CronUtils;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@Builder
public class IotDeviceModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String deviceId;
    private BigDecimal value;
    private long timestamp;
    private String macAddress;
    private String name;
    private String key = "count";
}
