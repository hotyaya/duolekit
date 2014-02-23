package org.job.agent;

import java.net.InetAddress;
import java.sql.Timestamp;

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
	private MessageProcessor processor;
	private String domain;
	private String user;
	private String pass;
	private String agent;

	public static void main(String[] args) {
		Application.init();
		new AgentLinker("10.64.145.245", "Hui-PC", "", "", "agent").start(); // Compaq-PC
	}

	public AgentLinker(String serveraddress, String domain, String user,
			String pass, String agent) {
		try {
			processor = new MessageProcessor();
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration(
					serveraddress, 5222);
			con = new XMPPConnection(config);
			this.domain = domain;
			this.user = user;
			this.pass = pass;
			this.agent = agent;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// private void sendsysteminfo() throws UnknownHostException, XMPPException{
	// newChat.sendMessage("HOSTADDRESS:/"+
	// InetAddress.getLocalHost().getHostAddress());
	// newChat.sendMessage("HOSTNAME:/"+
	// InetAddress.getLocalHost().getHostName());
	// Message mesg = new Message();
	// Properties pros0 = Application.getPros();
	// Enumeration enu = pros0.keys();
	// while (enu.hasMoreElements()) {
	// String key=enu.nextElement().toString().trim();
	// mesg.setProperty(key, pros0.getProperty(key).toString().trim());
	// //System.out.println(":"+pros0.getProperty(key).toString().trim());
	// }
	// mesg.setBody("SYSTEM");
	// newChat.sendMessage(mesg);
	// }

	@Override
	public void run() {
		while (isrun) {
			if (!con.isConnected() || !con.isAuthenticated()) {
				try {
					// connect and login with the username and pwd on server
					con.connect();
					// con.login(user, pass);//20140208
					con.loginAnonymously();
					// System.out.print(con.getRoster().getEntries().toArray()[0].toString());
					// System.out.println("\n Authenticated = " +
					// con.isAuthenticated());
					// add a listener to receive all messages
					// addListener();
					chatmanager = con.getChatManager();
					newChat = chatmanager.createChat(agent + "@" + domain,
							processor);
					// sendsysteminfo();
				} catch (XMPPException e) {
					System.out.print("link_x");
					// e.printStackTrace();
					// isrun = false;
				} catch (Exception e) {
					System.out.print("link_e");
					// e.printStackTrace();
				} finally {
					// 让线程休眠 然后再关闭连接
					// con.disconnect();
				}
			}
			if (con.isAuthenticated()){
				try {
					Message hb = new Message();
					hb.setProperty("MSGTYPE", "HB");// 为心跳类型；
					hb.setProperty("HOSTADDR", InetAddress.getLocalHost().getHostAddress());
					hb.setProperty("HOSTNAME", InetAddress.getLocalHost().getHostName());
					hb.setBody(new Timestamp(System.currentTimeMillis()).toString());
					if (newChat != null)
						newChat.sendMessage(hb);
				} catch (Exception ex) {
					ex.printStackTrace();
					isrun = false;
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
