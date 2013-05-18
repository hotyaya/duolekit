package com.mjt.testcase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mjt.entity.Group;
import com.mjt.entity.Schedule;
import com.mjt.entity.User;
import com.mjt.service.ScheduleService;
import com.mjt.service.UserService;

public class HibernateTest {
	@BeforeClass
	public static void beforeClass() {
		
	}
	@AfterClass
	public static void afterClass() {
	
	}
	
	 /**
	  *method 将字符串类型的日期转换为一个timestamp（时间戳记java.sql.Timestamp）
	   dateString 需要转换为timestamp的字符串
	   dataTime timestamp
	  */
	 public final static java.sql.Timestamp string2Time(String dateString) 
	  throws java.text.ParseException {
	   DateFormat dateFormat;
	  //dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss.SSS", Locale.ENGLISH);//设定格式
	  dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
	  dateFormat.setLenient(false);
	  java.util.Date timeDate = dateFormat.parse(dateString);//util类型
	  java.sql.Timestamp dateTime = new java.sql.Timestamp(timeDate.getTime());//Timestamp类型,timeDate.getTime()返回一个long型
	  return dateTime;
	 }
	@Test
	public void testSchedule() throws java.text.ParseException{
		int id = 1;
		String starttime = "2012-08-06 20:53:43";
		String endtime = "2012-08-08 23:56:12";
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");	     
 	    ScheduleService scheduleservice = (ScheduleService)ctx.getBean("scheduleservice");
 	    List<Schedule> list = scheduleservice.listEventsById(id,string2Time(starttime),string2Time(endtime));
 	   //System.out.println("这是测试");
 	    for(Schedule o : list) {
 	    	System.out.println(o.getId());
 	    	System.out.println(o.getTitle());
 	    	System.out.println(o.getStart());
 	    	System.out.println(o.getEnd());
 	    	System.out.println(o.getDescription());	    	
 	    	System.out.println(o.getColor()); 	    	
 	    	System.out.println("****************");
 	    }
 	    //System.out.println(System.currentTimeMillis());
	}
	@Test
	public void testGroup() {
		String info = "";
		User user = new User();
		user.setId(1);
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");	     
 	    UserService userservice = (UserService)ctx.getBean("userservice");
		List<Group> group = userservice.getGroups(user);
		info += "{totalCount :15,groups:[";
		for(Group temp:group) {
			info += "{img:'"+temp.getImg()+"',id:"+temp.getId()+",ctime:'"+temp.getCtime()+"',description:'"+temp.getDescription()+"',name:'"
			+temp.getName()+"',currentpeople:'"+temp.getCurrent()+"/"+temp.getCount()+"'},";
		}
		info += "]}";
		System.out.println(info);
	}
	@Test
	public void testQuitGroup() {
		
	}
	
}
