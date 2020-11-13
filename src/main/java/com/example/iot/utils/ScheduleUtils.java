package com.example.iot.utils;

import com.example.iot.entity.IotDevice;
import com.example.iot.scheduler.ScheduleJob;
import org.quartz.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定时任务工具类
 *
 * @author liaotao
 * @date 2020年10月10日
 */
public class ScheduleUtils {
    private final static String JOB_NAME = "TASK_";
    /**
     * 任务调度参数key
     */
    public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

    /**
     * 获取触发器key
     */
    private static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    private static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("getCronTrigger异常，请检查qrtz开头的表，是否有脏数据", e);
        }
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, IotDevice iotDevice) {

        try {
        	//构建job
            JobDetail jobDetail = JobBuilder.newJob()
                    .ofType(ScheduleJob.class)
                    .withIdentity(getJobKey(iotDevice.getId()))
                    .build();

            //构建cron
            /*CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(iotDevice.getCron())
            		.withMisfireHandlingInstructionDoNothing();

            //根据cron，构建一个CronTrigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(iotDevice.getId()))
                    .withSchedule(scheduleBuilder)
                    .build();*/
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(iotDevice.getIntervalInSeconds())
                    .repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(getTriggerKey(iotDevice.getId()))
                    .withSchedule(simpleScheduleBuilder)
                    .startAt(new Date())//不设置，默认为当前时间
                    .build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(JOB_PARAM_KEY, iotDevice);

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            /*if(scheduleJob.getState() == ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getJobId());
            }*/
        } catch (SchedulerException e) {
            throw new RuntimeException("创建定时任务失败", e);
        }
    }

    /**
     * 更新定时任务(任务不变，触发器变动)
     */
    public static void updateScheduleJobForTrigger(Scheduler scheduler, IotDevice iotDevice) {
        try {
            TriggerKey triggerKey = getTriggerKey(iotDevice.getId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(iotDevice.getCron())
            		.withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduler, iotDevice.getId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put(JOB_PARAM_KEY, JsonUtils.toString(iotDevice));

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            /*if(scheduleJob.getState() == ScheduleStatus.PAUSE.getValue()){
            	pauseJob(scheduler, scheduleJob.getJobId());
            }*/

        } catch (SchedulerException e) {
            throw new RuntimeException("更新定时任务失败", e);
        }
    }

    /**
     * 更新定时任务(任务，触发器都变动)
     */
    public static void updateScheduleJob(Scheduler scheduler, IotDevice iotDevice) {
        deleteScheduleJob(scheduler,iotDevice.getId());
        createScheduleJob(scheduler, iotDevice);
    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, IotDevice iotDevice) {
        try {
        	//参数
        	JobDataMap dataMap = new JobDataMap();
        	dataMap.put(JOB_PARAM_KEY, JsonUtils.toString(iotDevice));

            scheduler.triggerJob(getJobKey(iotDevice.getId()), dataMap);
        } catch (SchedulerException e) {
            throw new RuntimeException("立即执行定时任务失败", e);
        }
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJobs(Scheduler scheduler, List<String> jobIds) {
        try {
            List<JobKey> jobKeys = jobIds.stream().map(jobId -> getJobKey(jobId)).collect(Collectors.toList());
            scheduler.deleteJobs(jobKeys);
        } catch (SchedulerException e) {
            throw new RuntimeException("删除定时任务失败", e);
        }
    }


}
