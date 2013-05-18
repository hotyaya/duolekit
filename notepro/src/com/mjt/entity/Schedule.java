package com.mjt.entity;
import java.sql.Timestamp;

public class Schedule {
	private Integer id;
	private String title;
	private Timestamp start;	
	private Timestamp end;
	private String description;
	private String color;
	private Timestamp remindertime;
	//private Integer allday;
	private Integer messagenotice;
	private User user;
	
	public Timestamp getRemindertime() {
		return remindertime;
	}
	public void setRemindertime(Timestamp remindertime) {
		this.remindertime = remindertime;
	}
	public Integer getMessagenotice() {
		return messagenotice;
	}
	public void setMessagenotice(Integer messagenotice) {
		this.messagenotice = messagenotice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
//	public Integer getAllday() {
//		return allday;
//	}
//	public void setAllday(Integer allday) {
//		this.allday = allday;
//	}	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	

	
	
	
}
