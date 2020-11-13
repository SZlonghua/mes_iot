package com.example.iot.scheduler;

import com.example.iot.entity.IotDevice;
import com.example.iot.service.IotDeviceService;
import com.example.iot.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.jms.Queue;
import java.util.Date;

@Slf4j
public class ScheduleJob extends QuartzJobBean {

    IotDeviceService iotDeviceService = SpringContextUtils.getBean(IotDeviceService.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        IotDevice iotDevice = (IotDevice)context.getMergedJobDataMap().get(ScheduleUtils.JOB_PARAM_KEY);
        log.info("时间:{}开始执行任务:{}", DateUtil.format(new Date()),iotDevice);
        iotDeviceService.schedule(iotDevice);
        log.info("时间:{}结束执行任务:{}", DateUtil.format(new Date()),iotDevice);
    }
}
