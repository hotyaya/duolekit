package org.job.task;

import java.util.Iterator;
import java.util.List;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.dao.HibernateUtil;
import org.job.dao.entity.Onlineterminal;
import org.job.dao.entity.OnlineterminalDAO;

public class ActiveMonitor implements Runnable {
	boolean isrunnable = true;
	int isec = 20;
	
	@Override
	public void run() {
		while (isrunnable){
			try{
				check(); 
				Thread.sleep(1000 * isec);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	void check() {
		try {
			Session session = HibernateUtil.currentSession();
			Transaction tran = null;
			tran = session.beginTransaction();
			OnlineterminalDAO otdao = new  OnlineterminalDAO();
			//Onlineterminal ot0 = otdao.findById(threadid);
			List list = otdao.findAll();
			Iterator io = list.iterator();
			while (io.hasNext()) {
				Onlineterminal ot = (Onlineterminal) io.next();
				JDateTime jdt = new JDateTime(ot.getLastonlinetime());
				jdt.addSecond(isec);
				JDateTime jdtnow = new JDateTime();
				if (jdtnow.isBefore(jdt)){
					System.out.println("库jdt加20秒:"+jdt);
					System.out.println("jdtnow:"+jdtnow);
					System.out.println("jdtnow.isBefore(jdt):"+jdtnow.isBefore(jdt)+"是在"+isec+"秒内");
				}else{
					session.delete(ot);
					System.out.println("delete:删除成功！");
				}
			}
			tran.commit();
			///session.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
