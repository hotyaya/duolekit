package cn.railsoft.schedule.dao.demo;

import java.sql.Timestamp;

import org.hibernate.Session;

import cn.railsoft.schedule.dao.HibernateSessionFactory;
import cn.railsoft.schedule.dao.entity.Jobitem;
import cn.railsoft.schedule.dao.entity.JobitemDAO;



public class Test {

	/**
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		Jobitem item = new Jobitem();
		/**
		 * 	private String id;
			private Timestamp createTime;
			private Integer userid;
			private Long seq;
			private Integer actionDate;
			private String ip;
			private String type;
			private String numPrefix;
			private String numNo;
			private String numSuffix;
			private String content1;
			private String content2;
			private String content3;
			private String content4;
			private String content5;
			private String source;
			private String sourceId;
			private String status;
			private Timestamp statusCreatetime;
			private String memo;
		 */
		item.setCreateTime(new Timestamp(System.currentTimeMillis()));
		item.setUserid(1);
		item.setSeq(new Long(1));
		item.setActionDate(20130115);
		item.setType("北京铁路局局文");
		item.setNumPrefix("京信息");
		item.setNumNo("[2013]");
		item.setNumSuffix("2"+"号");
		item.setContent1("1");
		item.setContent2("2");
		item.setContent3("3");
		item.setContent4("4");
		item.setContent5("5");
		item.setSource("李科");
		item.setSource("82391231234123");
		item.setStatus("办完");
		item.setStatusCreatetime(new Timestamp(System.currentTimeMillis()));
		item.setMemo("测试数据");
		
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			JobitemDAO jd =new JobitemDAO();
			jd.save(item);
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
