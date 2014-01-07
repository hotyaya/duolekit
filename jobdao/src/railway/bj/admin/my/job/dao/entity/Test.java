package railway.bj.admin.my.job.dao.entity;

import java.sql.Timestamp;

import org.hibernate.Session;

import railway.bj.admin.my.job.dao.HibernateSessionFactory;

public class Test {

	public static void main(String[] args) {
		Doccatalog item = new Doccatalog();
		//item.setDocid();
		item.setType("OA");
		item.setDocsenddate(20140106);
		item.setDocsender("货运处");
		item.setDoccaption("sadfasdf");
		item.setDoccode("adfasf");
		item.setContact("asfdasdf");
		item.setPhone("asfdasdf");
		item.setBaseurl("asdfasdf");
		item.setUrl("asdfasf");
		item.setIndate(20140106);
		item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
		item.setIshidden(true);
		
		Docrecv recv = new Docrecv();
		recv.setDocid(3921341231241L);
		recv.setTransmitter("范处");
		recv.setRecvdate(20140107);
		recv.setOpertimestamp(new Timestamp(System.currentTimeMillis()));
		recv.setRecvTag("IM");
		recv.setMemo("测试一下");
		
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			DocrecvDAO dao =new DocrecvDAO();
			dao.save(recv);
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
