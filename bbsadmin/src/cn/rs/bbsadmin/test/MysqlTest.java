package cn.rs.bbsadmin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.bbsadmin.dao.Post;
import cn.rs.bbsadmin.dao.PostDAO;
import cn.rs.pub.TimeStampUtil;

public class MysqlTest extends TestCase {
	@SuppressWarnings("unchecked")
	public void test() throws ParseException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostDAO postdao = (PostDAO)context.getBean("PostDAO");
		
		Date date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("1970/01/01 08:00:00");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println("currentTime:" +format.format(calendar.getTime()));
        calendar.add(Calendar.DATE, -1);
        System.out.println("befor24Time:" +format.format(calendar.getTime()));
        long l2 = (format.parse(format.format(calendar.getTime())).getTime()-date2.getTime())/1000;
        
        List list = postdao.findAfterDateline(new TimeStampUtil().getTimestampBeforeNowByHours(50));
        System.out.println("36小时找到记录："+list.size());
		Object o;
		Iterator iterator= list.iterator();
		int i=0;
		while (iterator.hasNext()){
			i++;
			o = iterator.next();
			if (o instanceof Post){
				//((Post)o)
				System.out.println(""+i+((Post)o).getSubject());
			}
		}
	}
}
