package cn.rdm.biz;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rdm.dao.DeviceDAO;

public class DeviceList {
	
	public Object[] test(){
		try{
			ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
			DeviceDAO devicedao = (DeviceDAO)context.getBean("DeviceDAO");
			//Device device = new Device();
			@SuppressWarnings("rawtypes")
			List devicelist = devicedao.findAll();
			return devicelist.toArray();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
