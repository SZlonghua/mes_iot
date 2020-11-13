package com.example.iot.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.iot.entity.IotDevice;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liaotao
 * @since 2020-11-13
 */
public interface IotDeviceService extends IService<IotDevice> {

    /**
    * 详情
    * @param id
    *          主键
    * @return  详情
    */
    IotDevice info(String id);

    /**
    * 保存
    * @param iotDevice
    *          实体
    * @return  是否成功
    */
    Boolean saveEntity(IotDevice iotDevice);

    /**
    * 更新
    * @param iotDevice
    *          实体
    * @return  是否成功
    */
    Boolean updateEntity(IotDevice iotDevice);

    /**
    * 删除
    * @param ids
    *          主键
    * @return  是否成功
    */
    Boolean delete(List<String> ids);

    void schedule();

    void schedule(IotDevice iotDevice);
}
