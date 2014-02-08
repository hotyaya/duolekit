package org.job;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;

public class WriteData implements java.lang.Runnable{

	public static void main(String[] args) {
		
	}
	
	public void testw(){
		
		try{
			for (int i=99000;i<100000;i++){
				Session session = HibernateUtil.currentSession();
				Transaction tran = null;
				tran = session.beginTransaction();
				DoccatalogDAO dao =new DoccatalogDAO();
				Doccatalog item = new Doccatalog();
				//item.setDocid(i);
				item.setType("TG");
				item.setDocsenddate(20140106);
				item.setDocsender("货运处");
				item.setDoccaption(""+i);
				item.setDoccode("adfasf");
				item.setContact("asfdasdf");
				item.setPhone("asfdasdf");
				item.setBaseurl("asdfasdf");
				item.setUrl("asdfasf");
				item.setIndate(20140106);
				item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
				item.setIshidden(true);
				session.save(item);	//20140205
				//session.flush();
				//session.getTransaction().commit();
				tran.commit();
				Thread.sleep(100);
				System.out.println("write:"+i);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}

	}

	@Override
	public void run() {
		testw();
	}
	
	
}
