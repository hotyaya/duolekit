package com.mjt.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.mjt.dao.BaseDAO;
import com.mjt.entity.Schedule;
import com.mjt.service.ScheduleService;

public class ScheduleServiceImpl implements ScheduleService {
	BaseDAO basedao;
	public BaseDAO getBasedao() {
		return basedao;
	}
	public void setBasedao(BaseDAO basedao) {
		this.basedao = basedao;
	}
	
	
	public List<Schedule> listEventsById(int id, Timestamp start, Timestamp end) {
		// TODO Auto-generated method stub
		String hql="from Schedule as e where e.user.id='"+id+"' and e.start <'"+end+"'and e.end > '"+start+"'";
		return  basedao.query(hql);
	}
	public int addEvents(Schedule schedule) {
		// TODO Auto-generated method stub
		return basedao.saveEntity(schedule);
		
	}
	public void updateEvents(Schedule schedule) {
		// TODO Auto-generated method stub
		basedao.saveOrUpdate(schedule);
	}
	public boolean deleteEvents(int id) {
		basedao.delById(Schedule.class, id);
		return true;
	}

}
