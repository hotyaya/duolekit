package org.job.task;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.agent.interf.INotifyDisconnect;
import org.job.agent.interf.INotifyObject;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.HibernateUtil;
import org.job.dao.entity.Onlineterminal;
import org.job.dao.entity.OnlineterminalDAO;

public class ActiveMonitor implements Runnable {
	Vector<INotifyDisconnect> v = new Vector<INotifyDisconnect>();
	
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
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			OnlineterminalDAO otdao = new  OnlineterminalDAO();
			List list = otdao.findAll();
			Iterator io = list.iterator();
			//Vector<Onlineterminal> vv = new Vector<Onlineterminal>();
			while (io.hasNext()) {
				Onlineterminal ot = (Onlineterminal) io.next();
				JDateTime jdt = new JDateTime(ot.getLastonlinetime());
				jdt.addSecond(isec);
				JDateTime jdtnow = new JDateTime();
				if (jdtnow.isBefore(jdt)){
//					System.out.println("库jdt加20秒:"+jdt);
//					System.out.println("jdtnow:"+jdtnow);
//					System.out.println("jdtnow.isBefore(jdt):"+jdtnow.isBefore(jdt)+"是在"+isec+"秒内");
				}else{
//					System.out.println("库jdt加20秒:"+jdt);
//					System.out.println("jdtnow:"+jdtnow);
//					System.out.println("jdtnow.isBefore(jdt):"+jdtnow.isBefore(jdt)+"是在"+isec+"秒内");
					//vv.add(ot);
					//otdao.delete(ot);
					ot.setIsactive(false);
					otdao.merge(ot);
					notifyDisconnect(ot.getThreadid());
				}
			}
			//for (int i=0;i<vv.size();i++){
				//session.delete(vv.elementAt(i));System.out.println("delete:删除成功！");
				//notifyDisconnect(vv.elementAt(i).getThreadid());
			//}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
	}
	
	public void addListener(INotifyDisconnect ino) {
		v.add(ino);
	}

	private void notifyDisconnect(String threadid) {
		for (int i = 0; i < v.size(); i++) {
			v.elementAt(i).notifyDisconnect(threadid);
		}
	}
	
}
