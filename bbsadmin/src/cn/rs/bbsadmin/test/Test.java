package cn.rs.bbsadmin.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.bbsadmin.dao.Post;
import cn.rs.bbsadmin.dao.PostDAO;
import cn.rs.bbsadmin.mem.LocalCacheUtil;
import cn.rs.pub.CharFilter;
import cn.rs.pub.TimeStampUtil;

/**
 * 
 * 
 * 
 * @author Hui
 * 
 */

public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException  {
		// TODO Auto-generated method stub

//		Date date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("2012/05/01 20:41:00");
//
//		Date date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("1970/01/01 08:00:00");
//
//		long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
//
//		- date2.getTime() : date2.getTime() - date1.getTime();
//
//		System.out.println(l / 1000 + "秒");
//
//		System.out.println("1335819130" + "秒");
//		
//		long i = 1335819130;
//		
//		long ii = i*1000 + date2.getTime();
//		
//		System.out.println(new Timestamp(ii));
//		
//		System.out.println("The second method:");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("currentTime:" +format.format(calendar.getTime()));
//        calendar.add(Calendar.DATE, -1);
//        System.out.println("befor24Time:" +format.format(calendar.getTime()));
//        long l2 = (format.parse(format.format(calendar.getTime())).getTime()-date2.getTime())/1000;
//        System.out.println(l2+"");
        
        new LocalCacheUtil(120,new ClassPathXmlApplicationContext("applicationContext.xml"));

	}

	public static void testMysqlAndCode() throws ParseException{
        //new TimeStampUtil().test();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		PostDAO postdao = (PostDAO)context.getBean("PostDAO");
		
		Date date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("1970/01/01 08:00:00");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println("currentTime:" +format.format(calendar.getTime()));
        calendar.add(Calendar.DATE, -1);
        System.out.println("befor24Time:" +format.format(calendar.getTime()));
        long l2 = (format.parse(format.format(calendar.getTime())).getTime()-date2.getTime())/1000;
        
        List list = postdao.findAfterDateline(new TimeStampUtil().getTimestampBeforeNowByHours(70));
        System.out.println("36小时找到记录："+list.size());
		Object o;
		Iterator iterator= list.iterator();
		int i=0;
		Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		System.out.println();
		while (iterator.hasNext()){
			i++;
			o = iterator.next();
			if (o instanceof Post){
				//((Post)o)
				System.out.println(i+":"+((Post)o).getMessage()+"\n   :"+new CharFilter().clearForbiddenCharacter(((Post)o).getMessageString()));
			}
		}
		Timestamp ts2 = new Timestamp(System.currentTimeMillis());
		System.out.println("用时"+(ts2.getTime() - ts1.getTime())+"毫秒");
		//System.out.println(new CharFilter().clearForbiddenCharacter("中文fd我是中国人as "));
	}
	

}
