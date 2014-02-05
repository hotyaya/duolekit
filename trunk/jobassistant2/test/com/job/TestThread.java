package com.job;

import org.hibernate.Session;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.DoccatalogDAO;


public class TestThread {

	public static void main(String[] args) {
		
		try{
			new Thread(new WriteData()).start();
			for (int i=98000;i<1000000;i++){
				Session session = HibernateUtil.currentSession();
				session.beginTransaction();
				DoccatalogDAO dao =new DoccatalogDAO();
				System.out.println("read:"+i+":"+dao.findAll().size());
				session.getTransaction().commit();
				Thread.sleep(50);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			HibernateUtil.closeSession();
		}
	}
}
