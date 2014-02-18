package org.job.task;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.job.agent.JobAgent;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Onlineterminal;
import org.job.dao.entity.OnlineterminalDAO;
import org.job.ui.AgentWin;

public class Boot {

	public static void main(String[] args) {
		try {
			JobAgent jobAgent = new JobAgent("10.64.145.245", "Hui-PC","agent", "bdesdk", ""); // Compaq-PC
			AgentWin window = new AgentWin();
			ActiveMonitor am = new ActiveMonitor();
			JobAgent.chatProcess.addListener(window);// //加上消息的监听
			am.addListener(JobAgent.chatProcess);// 加上断开连接的监听
			new Thread(am).start();
			jobAgent.start();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	static {
		Session session = null;
		try {
			session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			OnlineterminalDAO otdao = new OnlineterminalDAO();
			List list = otdao.findAll();
			Iterator io = list.iterator();
			while (io.hasNext()) {
				Onlineterminal ot = (Onlineterminal) io.next();
				otdao.delete(ot);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
