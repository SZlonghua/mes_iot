package com.example.iot.scheduler;

import com.example.iot.service.IotDeviceService;
import com.example.iot.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class Task {

    @Autowired
    IotDeviceService iotDeviceService;

    @Scheduled(cron = "*/10 * * * * ?")
    public void execute() {
        log.debug("start sheduler task:{}", DateUtil.format(new Date()));
        iotDeviceService.schedule();
        log.debug("end sheduler task:{}",DateUtil.format(new Date()));
    }

}
