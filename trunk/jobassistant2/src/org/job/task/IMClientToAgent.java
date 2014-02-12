package org.job.task;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class IMClientToAgent {
	public static XMPPConnection con;
	public static Chat chat;
	public static ChatManager chatmanager;

	public static void main(String[] args) {
		new IMClientToAgent("192.168.0.151", "Compaq-PC", "agent"); // Compaq-PC
	}

	public IMClientToAgent(String serveraddress, String domain, String agent) {
		try {
			ConnectionConfiguration config = new ConnectionConfiguration(
					serveraddress, 5222);
			con = new XMPPConnection(config);
			con.connect();
			con.loginAnonymously();
			con.addConnectionListener(new ConnectionListener(){
				@Override
				public void connectionClosed() {
				}
				@Override
				public void connectionClosedOnError(Exception arg0) {
				}
				@Override
				public void reconnectingIn(int arg0) {
				}
				@Override
				public void reconnectionFailed(Exception arg0) {
				}
				@Override
				public void reconnectionSuccessful() {
				}
			} );
			chatmanager = con.getChatManager();
			chat = chatmanager.createChat(agent + "@" + domain,//"all@broadcast.railsoft.cn"
					new MessageListener() {
						public void processMessage(Chat chat, Message message) {
							System.out.println("recv: " + message.getBody());
						}
					});
		} catch (XMPPException e) {
			e.printStackTrace();
		} finally {
			// con.disconnect();// 让线程休眠 然后再关闭连接
		}
	}
}
