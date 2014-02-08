package org.im;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class ConnectToAgent extends Thread {
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;

	public static void main(String[] args) {
		new ConnectToAgent("127.0.0.1","Compaq-PC","","","agent").start(); //Compaq-PC
	}
	
	public ConnectToAgent(String serveraddress,String domain,String user,String pass,String agent) {
		try {
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration(serveraddress, 5222);
			con = new XMPPConnection(config);

			// connect and login with the username and pwd on server
			con.connect();
			//con.login(user, pass);//20140208
			con.loginAnonymously();
			// System.out.print(con.getRoster().getEntries().toArray()[0].toString());
			//System.out.println("\n Authenticated = " + con.isAuthenticated());
			// add a listener to receive all messages
			// addListener();
			chatmanager = con.getChatManager();
			newChat = chatmanager.createChat(agent+"@"+domain,//"all@broadcast.railsoft.cn"
					new MessageListener() {// all@broadcast.hotyaya-ge2w3vb;// user2@hotyaya-ge2w3vb
						public void processMessage(Chat chat, Message message) {
							System.out.println("recv: " + message.getBody());
						}
					});
			// newChat.sendMessage("hi");
		} catch (XMPPException e) {
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
				newChat.sendMessage("test");// point.toString()
				//System.out.println("...");
				Thread.sleep(50);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
