package org.job.agent;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class JobAgent extends Thread {
	private boolean isrunnable = true;
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;
	public static ChatProcess chatProcess;
	public String user = "";
	public String pass = "";
	public String serveraddress = "";

	public static void main(String[] args) {
		new JobAgent("10.64.145.245", "Hui-PC", "agent", "bdesdk", "").start(); //Compaq-PC
	}

	public JobAgent(String serveraddress, String domain, String user,String pass, String agent) {
		this.serveraddress = serveraddress;
		this.user = user;
		this.pass = pass;
		chatProcess = new ChatProcess();//20140213
		ConnectionConfiguration config = new ConnectionConfiguration(serveraddress, 5222);
		con = new XMPPConnection(config);
		con.addConnectionListener(new ConnectionListener() {
			@Override
			public void connectionClosed() {
				System.out.println("connectionClosed");
			}

			@Override
			public void connectionClosedOnError(Exception arg0) {
				System.out.println("connectionClosedOnError");
			}

			@Override
			public void reconnectingIn(int arg0) {
				System.out.println("reconnectingIn");
			}

			@Override
			public void reconnectionFailed(Exception arg0) {
				System.out.println("reconnectionFailed");
			}

			@Override
			public void reconnectionSuccessful() {
				System.out.println("reconnectionSuccessful");
			}
		});
		XMPPConnection.addConnectionCreationListener(new ConnectionCreationListener() {
					@Override
					public void connectionCreated(Connection arg0) {
						System.out.println("connectionCreated");
					}
				});
		chatmanager = con.getChatManager();
		chatmanager.addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean createdLocally) {
				chatProcess.setChatMessageCollection(new ChatMessageCollection(chat));
				if (!createdLocally)
				chat.addMessageListener(new MessageListener() {
					@Override
					public void processMessage(Chat arg0, Message arg1) {
						ChatMessageCollection cmc =  chatProcess.getCMC(arg0.getThreadID());
						if (cmc!=null){
							cmc.putMessage(arg1);
							chatProcess.notifyMessage();
							//System.out.println("加入！" + arg1.getBody());
						} else{
							//20140217?????
							//chatProcess.setChatMessageCollection(new ChatMessageCollection(arg0));
							System.out.println("未加入！" + arg1.getBody());
						}
					}
				});
			}
		});
	}

	@Override
	public void run() {
		while (isrunnable) {
			try {
				if (!con.isConnected()) {
					connect();
					System.out.println("Authenticated:" + con.isAuthenticated());
				}
			} catch (XMPPException e) {
				e.printStackTrace();
			} finally {
				// con.disconnect();//让线程休眠 然后再关闭连接
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	boolean connect() throws XMPPException {
		con.connect();
		con.login(user, pass);
		return con.isAuthenticated();
	}
}
