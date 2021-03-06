package com.example.iot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.iot.utils.CronUtils;
import com.example.iot.utils.RamdomUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author liaotao
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class IotDevice implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private String id;
    private String deviceId;
    private BigDecimal value;
    @TableField(exist = false)
    private long timestamp;
    @TableField(exist = false)
    private String macAddress;
    @TableField(exist = false)
    private String name;
    @TableField(exist = false)
    private String key = "count";
    @TableField(exist = false)
    private String cron = CronUtils.radomCron();
    @TableField(exist = false)
    private Integer intervalInSeconds = RamdomUtils.ramdomValue().intValue();


}
