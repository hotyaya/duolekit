package com.mjt.service;

import java.sql.Timestamp;
import java.util.List;

import com.mjt.entity.Schedule;

public interface ScheduleService {
	public List<Schedule> listEventsById(int id,Timestamp start,Timestamp end);
	public int addEvents(Schedule schedule);
	public void updateEvents(Schedule schedule);
	public boolean deleteEvents(int id);
}
