package com.example.iot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.iot.entity.IotDevice;
import com.example.iot.mapper.IotDeviceMapper;
import com.example.iot.service.IotDeviceService;
import com.example.iot.utils.JsonUtils;
import com.example.iot.utils.RamdomUtils;
import com.example.iot.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.Queue;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liaotao
 * @since 2020-11-13
 */
@Service
public class IotDeviceServiceImpl extends ServiceImpl<IotDeviceMapper, IotDevice> implements IotDeviceService {

    List<IotDevice> list;

    @Autowired
    Queue queue;
    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;


    @PostConstruct
    public void init() {
        list = baseMapper.selectList(null);
    }

    @Override
    public IotDevice info(String id) {
        return getById(id);
    }

    @Override
    public Boolean saveEntity(IotDevice iotDevice) {
        iotDevice.setId(UUIDUtil.getUUID());
        return save(iotDevice);
    }

    @Override
    public Boolean updateEntity(IotDevice iotDevice) {
        return saveOrUpdate(iotDevice);
    }

    @Override
    public Boolean delete(List<String> ids) {
        return removeByIds(ids);
    }

    @Override
    public void schedule() {
        list.stream().forEach(iotDevice -> {
            iotDevice.setValue(iotDevice.getValue().add(RamdomUtils.ramdomValue()));
            iotDevice.setTimestamp(System.currentTimeMillis());
        });

        if(list!=null&&list.size() > 0){
            list.stream().forEach(iotDevice -> {
                jmsMessagingTemplate.convertAndSend(queue, JsonUtils.toString(iotDevice));
            });
            saveOrUpdateBatch(list);
        }
    }
}
