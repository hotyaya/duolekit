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

			// �ٴ���һ��JobDetailʵ����ָ��SimpleJob

			//JobDetail jobDetail = new JobDetail("job1_1", "jGroup1",SimpleJob.class);

			 JobDetail job = org.quartz.JobBuilder.newJob(SimpleJob.class).withIdentity("job1_1", "jGroup1").build();
			
			// ��ͨ��SimpleTrigger������ȹ�������������ÿ2������һ�Σ�������100��

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

			// ��ͨ��SchedulerFactory��ȡһ��������ʵ��

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();
			//scheduler.scheduleJob(jobDetail, simpleTrigger);// �� ע�Ტ���е���
			scheduler.scheduleJob(job,trigger);
			scheduler.start();// �ݵ�������

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
