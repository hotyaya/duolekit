package com.test.Quartz;

import static org.quartz.DateBuilder.evenHourDate;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
	
public class SimpleTriggerRunner {

	public static void main(String args[]) {

		try {

			// ①创建一个JobDetail实例，指定SimpleJob

			//JobDetail jobDetail = new JobDetail("job1_1", "jGroup1",SimpleJob.class);

			 JobDetail job = org.quartz.JobBuilder.newJob(SimpleJob.class).withIdentity("job1_1", "jGroup1").build();
			
			// ②通过SimpleTrigger定义调度规则：马上启动，每2秒运行一次，共运行100次

//			SimpleTrigger simpleTrigger = new SimpleTrigger("trigger1_1",
//					"tgroup1");

			Trigger trigger = newTrigger()
		    .withIdentity("trigger8") // because group is not specified, "trigger8" will be in the defaul
		    //.startAt(evenHourDate(null)) // get the next even-hour (minutes and seconds zero ("00:00"))
		    .withSchedule(simpleSchedule().withIntervalInSeconds(1).repeatForever()).build();
			//.withSchedule(simpleSchedule().withIntervalInHours(2).repeatForever()).build();
			// note that in this example, 'forJob(..)' is not called 
			//  - which is valid if the trigger is passed to the scheduler along with the job  
//		    scheduler.scheduleJob(trigger, job);
//			simpleTrigger.setStartTime(new Date());
//			simpleTrigger.setRepeatInterval(2000);
//			simpleTrigger.setRepeatCount(100);

			// ③通过SchedulerFactory获取一个调度器实例

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			//scheduler.scheduleJob(jobDetail, simpleTrigger);// ④ 注册并进行调度
			scheduler.scheduleJob(job,trigger);
			scheduler.start();// ⑤调度启动

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
