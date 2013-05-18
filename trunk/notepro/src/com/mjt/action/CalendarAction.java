package com.mjt.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mjt.entity.Schedule;
import com.mjt.entity.User;
import com.mjt.service.ScheduleService;
import com.mjt.util.JsonFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CalendarAction extends ActionSupport{
	
	private int id;	
	private String title;
	private Timestamp start;	
	private Timestamp end;
	private String description;
	private String color;
	private Timestamp remindertime;  
	private int messagenotice;   
	public ScheduleService scheduleservice;	
	private User uesr;
	
	
	public User getUserFormSession() {
		ActionContext actionContext = ActionContext.getContext();         
		Map session = actionContext.getSession();
		User u =(User)session.get("user");
		return u;		
	}
	public String listEvents() {
		int userid = this.getUserFormSession().getId();
		JsonFormat jsonformat = new JsonFormat();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		String str = jsonformat.formatSchedule(scheduleservice.listEventsById(userid, start, end)).toString();
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String addEvents() {
		Schedule temp = new Schedule();
		temp.setTitle(title);
		temp.setDescription(description);
		temp.setStart(start);
		temp.setEnd(end);
		temp.setColor(color);
		temp.setMessagenotice(messagenotice);
		if(messagenotice != 1) {
			temp.setRemindertime(null);
		}else {
			temp.setRemindertime(remindertime);
		}
		temp.setUser(getUserFormSession());
		//System.out.println(temp);
		int i = scheduleservice.addEvents(temp);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("[{\"id\":"+i+"}]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String updateEvents() {
		Schedule temp = new Schedule();
		temp.setId(id);
		temp.setTitle(title);
		temp.setDescription(description);
		temp.setStart(start);
		temp.setEnd(end);
		temp.setColor(color);		
		temp.setMessagenotice(messagenotice);
		if(messagenotice != 1) {
			temp.setRemindertime(null);
		}else {
			temp.setRemindertime(remindertime);
		}
		
		temp.setUser(getUserFormSession());
		scheduleservice.updateEvents(temp);
		return null;
	}
	
	public String deleteEvents() {
		scheduleservice.deleteEvents(id);
		//System.out.println("id为"+id+"的事件删除成功！");
		return null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public User getUesr() {
		return uesr;
	}
	public void setUesr(User uesr) {
		this.uesr = uesr;
	}
	public ScheduleService getScheduleservice() {
		return scheduleservice;
	}
	public void setScheduleservice(ScheduleService scheduleservice) {
		this.scheduleservice = scheduleservice;
	}
	public int getMessagenotice() {
		return messagenotice;
	}
	public void setMessagenotice(int messagenotice) {
		this.messagenotice = messagenotice;
	}
	public Timestamp getRemindertime() {
		return remindertime;
	}
	public void setRemindertime(Timestamp remindertime) {
		this.remindertime = remindertime;
	}	
}
