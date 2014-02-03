package dao;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;


public class Test {

	public static void main(String[] args) {
		Doccatalog item = new Doccatalog();
		//item.setDocid();
		item.setType("TG");
		//item.setDocsenddate(20140106); 	//SQLite
		item.setDocsenddate(20140106 + "");
		item.setDocsender("货运处");
		item.setDoccaption("sadfasdf");
		item.setDoccode("adfasf");
		item.setContact("asfdasdf");
		item.setPhone("asfdasdf");
		item.setBaseurl("asdfasdf");
		item.setUrl("asdfasf");
		//item.setIndate(20140106);		//SQLite
		item.setIndate(20140106+"");
		//item.setIntimestamp(new Timestamp(System.currentTimeMillis()));	//SQLite
		item.setIntimestamp(new Timestamp(System.currentTimeMillis()).toString());
		//item.setIshidden(true);		//SQLite
		item.setIshidden("1");	//true	//SQLite
		
//		Docrecv recv = new Docrecv();
//		recv.setDocid(3921341231241L);
//		recv.setTransmitter("范处");
//		recv.setRecvdate(20140107);
//		recv.setOpertimestamp(new Timestamp(System.currentTimeMillis()));
//		recv.setRecvTag("IM");
//		recv.setMemo("测试一下");
		
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			DoccatalogDAO dao = new DoccatalogDAO();
			dao.save(item);
//			DocrecvDAO dao =new DocrecvDAO();
//			dao.save(recv);
			session.getTransaction().commit();
			System.out.println("s。");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
