package com.mjt.util;

import java.sql.Timestamp;

public class JsonScheduleNode {
	private Integer id;
	private String title;
	private String start;	
	private String end;
	private String description;
	private String color;
	private String remindertime;
	private Integer messagenotice;
	
	public Integer getMessagenotice() {
		return messagenotice;
	}
	public void setMessagenotice(Integer messagenotice) {
		this.messagenotice = messagenotice;
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
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getRemindertime() {
		return remindertime;
	}
	public void setRemindertime(String remindertime) {
		this.remindertime = remindertime;
	}
	

	
}
