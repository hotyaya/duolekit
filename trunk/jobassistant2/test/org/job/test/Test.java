package org.job.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;

public class Test {

	public static void main(String[] args) {
//		String databaseURL = "jdbc:firebirdsql:embedded:test2.fdb";
//		String user = "sysdba";
//		String password = "masterkey";
//		String driverName = "org.firebirdsql.jdbc.FBDriver";
//		try {
//			Class.forName(driverName);
//			Connection conn = DriverManager.getConnection(databaseURL, user,password);
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("select * from t1");
//			rs.next();
//			System.out.println(""+rs.getString(2));
//			rs.close();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		
		try{
//			System.out.println(""+new Timestamp(System.currentTimeMillis()));
//			Session session = HibernateSessionFactory.getSession();//HibernateUtil.currentSession();//
//				session.beginTransaction();
//				DoccatalogDAO dao =new DoccatalogDAO();
//				Doccatalog item = new Doccatalog();
//				item.setDocid(9285);
//				item.setType("OA");
//				item.setDocsenddate(20140106);
//				item.setDocsender("货运处");
//				item.setDoccaption("");
//				item.setDoccode("adfasf");
//				item.setContact("asfdasdf");
//				item.setPhone("asfdasdf");
//				item.setBaseurl("asdfasdf");
//				item.setUrl("asdfasf");
//				item.setIndate(20140106);
//				item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
//				item.setIshidden(true);
//				dao.save(item);
//				session.getTransaction().commit();
//				//Thread.sleep(100);
//				System.out.println("write:");
//				System.out.println(""+new Timestamp(System.currentTimeMillis()));

			JDateTime jdt = new JDateTime();
			System.out.println(Integer.parseInt(jdt.toString("YYYYMMDD")));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

}
