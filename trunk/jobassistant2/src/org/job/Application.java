package org.job;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.dao.HibernateUtil;
import org.job.dao.entity.Sysprop;
import org.job.dao.entity.SyspropDAO;

public class Application {
	static Properties pros = new Properties();

	public static String getV(String k) {
		return pros.getProperty(k.trim()).trim();
	}
	
	/**
	 * 启动验证
	 */
	public static void init0() {
		
	}
	
	public static boolean init() {
		try {
			Session session = HibernateUtil.currentSession();
			Transaction tran = null;
			tran = session.beginTransaction();
			SyspropDAO dao = new SyspropDAO();
			List list = dao.findAll();
			Iterator io = list.iterator();
			tran.commit();
			while (io.hasNext()) {
				Sysprop pro1 = (Sysprop) io.next();
				pros.put(pro1.getK(), pro1.getV());
			}
			init2();//20140214加入到属性中；
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static void init2(){
		Properties pro = System.getProperties();
		Enumeration enu = pro.keys();
		while (enu.hasMoreElements()) {
			String key=enu.nextElement().toString().trim();
			pros.put(key,pro.getProperty(key).toString().trim());
			//System.out.println(key+":"+pro.getProperty(key));
		}

	}

	public static Properties getPros() {
		return pros;
	}
	
	
}
