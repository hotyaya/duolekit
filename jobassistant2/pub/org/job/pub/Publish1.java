package org.job.pub;

import java.sql.Timestamp;
import java.util.List;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;
import org.job.dao.entity.Sysprop;
import org.job.dao.entity.SyspropDAO;


public class Publish1 {

	public static void main(String[] args) {
		try{
			String SYSVERSION = "0.8";
			String SUBVERSION = "1";
			String PUBDATE = new JDateTime().toString();
			
			Session session = HibernateSessionFactory.getSession();
			
			Transaction t= session.beginTransaction();
			DoccatalogDAO dao =new DoccatalogDAO();
//			List<Doccatalog> list = dao.findAll();
//			for (int i = 0; i < list.size(); i++) {
//				Doccatalog cata = (Doccatalog) list.get(i);
//				dao.delete(cata);
//			}
			t.commit();
			
//			t= session.beginTransaction();
			SyspropDAO sdao = new SyspropDAO();
//			Sysprop prop = sdao.findById("MAILDO");
//			prop.setV("FALSE");
//			sdao.merge(prop);
//			t.commit();
			
			t= session.beginTransaction();
			sdao = new SyspropDAO();
			Sysprop prop1 = sdao.findById("SYSVERSION");
			if (prop1 == null){
				prop1 = new Sysprop("SYSVERSION",SYSVERSION);
				sdao.merge(prop1);
			}else{
				prop1.setV(SYSVERSION);
				sdao.save(prop1);
			}
			t.commit();

			t= session.beginTransaction();
			sdao = new SyspropDAO();
			Sysprop prop2 = sdao.findById("SUBVERSION");
			if (prop2 == null){
				prop2 = new Sysprop("SUBVERSION",SUBVERSION);
				sdao.merge(prop2);
			}else{
				prop2.setV(SUBVERSION);
				sdao.save(prop2);
			}
			t.commit();

			t= session.beginTransaction();
			sdao = new SyspropDAO();
			Sysprop prop3 = sdao.findById("PUBDATE");
			if (prop3 == null){
				prop3 = new Sysprop("PUBDATE",PUBDATE);
				sdao.merge(prop3);
			}else{
				prop3.setV(PUBDATE);
				sdao.save(prop3);
			}
			t.commit();

			
//			Transaction t2=session.beginTransaction();
//			dao =new DoccatalogDAO();
//			for (int i=0;i<5;i++){
//				Doccatalog item = new Doccatalog();
//				item.setType("TG");
//				System.out.println(""+(20140101+i));
//				item.setDocsenddate(20140101+i);
//				item.setDocsender("XXX");
//				item.setDoccaption("标题"+i);
//				item.setDoccode("");
//				item.setContact("");
//				item.setPhone("");
//				item.setBaseurl("");
//				item.setUrl("");
//				item.setIndate(20140106);
//				item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
//				item.setIshidden(false);
//				item.setIslooked(false);
//				item.setIstodo(false);
//				item.setIstodotime(new Timestamp(System.currentTimeMillis()));//待办时间
//				item.setTodomemo("");
//				item.setIsdoned(false);
//				item.setIsdonedtime(new Timestamp(System.currentTimeMillis()));//办完时间
//				item.setDonememo("");
//				dao.save(item);
//			}
//			t2.commit();
			JDateTime jdt = new JDateTime();
			System.out.println(Integer.parseInt(jdt.toString("YYYYMMDD")));
			session.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
