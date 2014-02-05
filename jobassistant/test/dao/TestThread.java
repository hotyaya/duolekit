package dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TestThread {

	public static void main(String[] args) {
		new Thread(new WriteData()).start();
		for (int i=950000;i<1000000;i++){
			try{
				Session session = HibernateUtil.currentSession();
				Transaction tran1 = session.beginTransaction();
				SQLQuery sq = session.createSQLQuery("select count(*) from Doccatalog");
				//DoccatalogDAO dao =new DoccatalogDAO();//dao.findAll().size()
				System.out.println("read:"+i+":"+ sq.list().get(0).toString() );
				tran1.commit();
				Thread.sleep(100);
			}catch(Exception ex){
				//ex.printStackTrace();
			}finally{
				HibernateUtil.closeSession();
			}
		}
	}
}
