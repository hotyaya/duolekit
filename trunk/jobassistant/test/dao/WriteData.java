package dao;

import java.sql.Timestamp;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;

public class WriteData implements java.lang.Runnable{
	
	public static void main(String[] args) {
		
	}
	
	public void testw(){
			for (int i=950000;i<1000000;i++){
				try{
					Session session = HibernateUtil.currentSession();
					Transaction tran = session.beginTransaction();
					DoccatalogDAO dao =new DoccatalogDAO();
					Doccatalog item = new Doccatalog();
					//item.setDocid(i);
					item.setType("OA");
					item.setDocsenddate("20140106");
					item.setDocsender("货运处");
					item.setDoccaption(""+i);
					item.setDoccode("adfasf");
					item.setContact("asfdasdf");
					item.setPhone("asfdasdf");
					item.setBaseurl("asdfasdf");
					item.setUrl("asdfasf");
					item.setIndate("20140106");
					item.setIntimestamp(new Timestamp(System.currentTimeMillis()).toString());
					item.setIshidden("1");
					session.save(item);
					//dao.save(item);
					//session.flush();
					//session.getTransaction().commit();
					tran.commit();
					Thread.sleep(50);
					System.out.println("write:"+i);
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					HibernateUtil.closeSession();
				}
			}
	}

	@Override
	public void run() {
		testw();
	}
	
	
}
