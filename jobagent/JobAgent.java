package org.job.agent;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class JobAgent extends Thread {
	private boolean isrunnable = true;
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;

	public static void main(String[] args) {
		new JobAgent("10.64.145.245", "Hui-PC", "agent", "bdesdk", "").start(); // Compaq-PC
	}

	public JobAgent(String serveraddress, String domain, String user,
			String pass, String agent) {
		try {
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration(serveraddress, 5222);
			con = new XMPPConnection(config);
			// connect and login with the username and pwd on server
			con.connect();
			con.login(user, pass);// 20140208
			System.out.println("Authenticated = "+con.isAuthenticated());
			// con.loginAnonymously();
			// System.out.print(con.getRoster().getEntries().toArray()[0].toString());
			// System.out.println("\n Authenticated = " +
			// con.isAuthenticated());
			// add a listener to receive all messages
			// addListener();
			chatmanager = con.getChatManager();
			// newChat =
			// chatmanager.createChat(agent+"@"+serveraddress,//"all@broadcast.railsoft.cn"
			// new MessageListener() {// all@broadcast.hotyaya-ge2w3vb;//
			// user2@hotyaya-ge2w3vb
			// public void processMessage(Chat chat, Message message) {
			// System.out.println("recv: " + message.getBody());
			// }
			// });
			chatmanager.addChatListener(new ChatManagerListener() {
				@Override
				public void chatCreated(Chat chat, boolean createdLocally) {
					if (!createdLocally)
						chat.addMessageListener(new MessageListener() {
							@Override
							public void processMessage(Chat arg0, Message arg1) {
								System.out.println("" + arg1.getBody());
							}
						});
				}
			});
		} catch (XMPPException e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			// con.disconnect();
		}
	}

	@Override
	public void run() {
		while (isrunnable) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
