package org.job.agent;

import java.sql.Timestamp;
import java.util.Vector;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.job.dao.HibernateUtil;
import org.job.dao.entity.Onlineterminal;
import org.job.dao.entity.OnlineterminalDAO;

@SuppressWarnings("rawtypes")
public class ChatMessageCollection {
	private Chat chat = null;
	private Vector vmesg = new Vector();

	public Vector getVmesg() {
		return vmesg;
	}

	public ChatMessageCollection(Chat chat) {
		super();
		this.chat = chat;
	}

	public String getThreadId() {
		return chat == null ? "null" : chat.getThreadID();
	}

	public String getParticipant() {
		return chat.getParticipant();
	}

	@SuppressWarnings("unchecked")
	public void putMessage(Message arg1) {
		if (arg1.getProperty("MSGTYPE") != null && (arg1.getProperty("MSGTYPE").equals("HB"))) {
			/**
			 * hb.setProperty("MSGTYPE", "HB");//为心跳类型；
			 * hb.setProperty("HOSTADDR"
			 * ,InetAddress.getLocalHost().getHostAddress());
			 * hb.setProperty("HOSTNAME"
			 * ,InetAddress.getLocalHost().getHostName()); hb.setBody(new
			 * Timestamp(System.currentTimeMillis()).toString());
			 */
			if (arg1.getProperty("HOSTADDR")!=null && arg1.getProperty("HOSTNAME")!=null && arg1.getBody()!=null){
				JDateTime jdt = new JDateTime(arg1.getBody().toString().trim());
				save(chat.getThreadID(),arg1.getProperty("HOSTNAME").toString().trim(),arg1.getProperty("HOSTADDR").toString().trim(),
						new Timestamp(jdt.getTimeInMillis()));
			}
			
		} else if (arg1.getProperty("MSGTYPE") != null
				&& (arg1.getProperty("MSGTYPE").equals("SYSINFO"))) {

		} else if (arg1.getProperty("MSGTYPE") != null
				&& (arg1.getProperty("MSGTYPE").equals("INFO"))) {

		} else {

		}
		vmesg.add(arg1);
		// if (vmesg.size()>20)vmesg.remove(0);
	}

	public Chat getChat() {
		return chat;
	}

	void save(String threadid, String hostname, String ip, Timestamp ts) {
		try {
			Session session = HibernateUtil.currentSession();
			Transaction tran = null;
			tran = session.beginTransaction();
			OnlineterminalDAO otdao = new  OnlineterminalDAO();
			Onlineterminal ot0 = otdao.findById(threadid);
			if (ot0!=null){
				session.delete(ot0);
			}
			Onlineterminal ot = new Onlineterminal();
			ot.setThreadid(threadid);
			ot.setHostname(hostname);
			ot.setIp(ip);
			ot.setLastonlinetime(ts);
			session.save(ot);
			tran.commit();
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
