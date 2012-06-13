package cn.rs.cr.test;

import java.text.ParseException;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.cr.dao.Softitem;
import cn.rs.cr.dao.SoftitemDAO;

public class Test extends TestCase {
	@SuppressWarnings("unchecked")
	public void test() throws ParseException{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SoftitemDAO softitemdao = (SoftitemDAO)context.getBean("SoftitemDAO");
		
		Softitem item = new Softitem();
		item.setRegisterid("1");
		item.setTypecode("xxx-xxx");
		item.setSoftname("软件1");
		item.setSoftbrief("1");
		item.setVersion("v1.0");
		item.setAuthor("lh");
		item.setPublishdate("2011-10-01");
		item.setRegisterdate("2012-06-12");
		softitemdao.save(item);
		System.out.println("OK  "+item.getSoftname());
//		CDBMemberDAO memberdao = (CDBMemberDAO)context.getBean("CDBMemberDAO");
//		CDBMember m =memberdao.findById(14828);
//		m.setUsername("liuhui1");
//		m.setGroupid(4);
//		if (m.getBday()==null) m.setBday(new Date(System.currentTimeMillis()));//有空值错误
//		//memberdao.getSessionFactory().openSession().beginTransaction();
//		memberdao.merge(m);
//		//memberdao.getSessionFactory().openSession().update(m);
//		//memberdao.save(m);
//		//		m.setUsername("liuhui");
////		memberdao.save(m);
//		System.out.println("OK  "+m.getUsername());
//
//		
//		CDBThreadDAO threaddao = (CDBThreadDAO)context.getBean("CDBThreadDAO");
//		CDBThread t = threaddao.findById(108398);
//		t.setSubject("我的测试！");
//		threaddao.merge(t);
//		System.out.println("OK  "+t);
		
//		PostDAO postdao = (PostDAO)context.getBean("PostDAO");
//		Date date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse("1970/01/01 08:00:00");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("currentTime:" +format.format(calendar.getTime()));
//        calendar.add(Calendar.DATE, -1);
//        System.out.println("befor24Time:" +format.format(calendar.getTime()));
//        long l2 = (format.parse(format.format(calendar.getTime())).getTime()-date2.getTime())/1000;
//        
        //List list = postdao.findAll();//.findAfterDateline(new TimeStampUtil().getTimestampBeforeNowByHours(50));
        
//        Post p = postdao.findById(6857076);
//        p.setSubject(p.getSubject()+"$");
//        postdao.merge(p);

        System.out.println("test！");
//        System.out.println("36小时找到记录："+list.size());
//		Object o;
//		Iterator iterator= list.iterator();
//		int i=0;
//		while (iterator.hasNext()){
//			i++;
//			o = iterator.next();
//			if (o instanceof Post){
//				System.out.println(""+i + ((Post)o).getAuthor() + ""+((Post)o).getSubject());
//			}
//		}
	}
}
