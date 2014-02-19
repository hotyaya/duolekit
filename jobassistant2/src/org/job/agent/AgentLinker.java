package org.job.agent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.Properties;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.job.Application;

public class AgentLinker extends Thread {
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;
	private static boolean isrun = true;

	public static void main(String[] args) {
		Application.init();
		new AgentLinker("10.64.145.245", "Hui-PC", "", "", "agent").start(); // Compaq-PC
	}

	public AgentLinker(String serveraddress, String domain, String user,
			String pass, String agent) {
		try {
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration(
					serveraddress, 5222);
			con = new XMPPConnection(config);

			// connect and login with the username and pwd on server
			con.connect();
			// con.login(user, pass);//20140208
			con.loginAnonymously();
			// System.out.print(con.getRoster().getEntries().toArray()[0].toString());
			System.out.println("\n Authenticated = " + con.isAuthenticated());
			// add a listener to receive all messages
			// addListener();
			chatmanager = con.getChatManager();
			newChat = chatmanager.createChat(agent + "@" + domain,new MessageProcessor());
			//sendsysteminfo();
		} catch (XMPPException e) {
			e.printStackTrace();
			isrun = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			// con.disconnect();
		}
	}
	
	private void sendsysteminfo() throws UnknownHostException, XMPPException{
		newChat.sendMessage("HOSTADDRESS:/"+ InetAddress.getLocalHost().getHostAddress());
		newChat.sendMessage("HOSTNAME:/"+ InetAddress.getLocalHost().getHostName());
		Message mesg = new Message();
		Properties pros0 = Application.getPros();
		Enumeration enu = pros0.keys();
		while (enu.hasMoreElements()) {
			String key=enu.nextElement().toString().trim();
			mesg.setProperty(key, pros0.getProperty(key).toString().trim());
			//System.out.println(":"+pros0.getProperty(key).toString().trim());
		}
		mesg.setBody("SYSTEM");
		newChat.sendMessage(mesg);
	}

	@Override
	public void run() {
		while (isrun) {
			try {
				Message hb = new Message();
				hb.setProperty("MSGTYPE", "HB");//为心跳类型；
				hb.setProperty("HOSTADDR",InetAddress.getLocalHost().getHostAddress());
				hb.setProperty("HOSTNAME",InetAddress.getLocalHost().getHostName());
				hb.setBody(new Timestamp(System.currentTimeMillis()).toString());
				if (newChat!=null)newChat.sendMessage(hb);
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
				isrun = false;
			}
		}
	}
}
