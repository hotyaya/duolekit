package com.test.Quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

	// ��ʵ��Job�ӿڷ���

	public void execute(JobExecutionContext jobCtx)
			throws JobExecutionException {

		 System.out.println(jobCtx.getTrigger().getJobKey()+" triggered. time is:" + (new Date()));

	}

}