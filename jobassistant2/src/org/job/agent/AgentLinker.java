package org.job.agent;

import java.net.InetAddress;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class AgentLinker extends Thread {
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;

	public static void main(String[] args) {
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
			newChat = chatmanager.createChat(agent + "@" + domain,// "all@broadcast.railsoft.cn"
					new MessageListener() {// all@broadcast.hotyaya-ge2w3vb;//
											// user2@hotyaya-ge2w3vb
						public void processMessage(Chat chat, Message message) {
							try {
								System.out.println("recv: " + message.getBody());
								chat.sendMessage(message.getBody());
							} catch (XMPPException e) {
								e.printStackTrace();
							}
						}
					});
			newChat.sendMessage("hi");
			newChat.sendMessage("hi:"
					+ InetAddress.getLocalHost().getHostAddress());
			newChat.sendMessage("hi");
			newChat.sendMessage("hi");
			newChat.sendMessage("hi");
		} catch (XMPPException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			// con.disconnect();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				// newChat.sendMessage("test");// point.toString()
				// System.out.println("...");
				Thread.sleep(5000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
