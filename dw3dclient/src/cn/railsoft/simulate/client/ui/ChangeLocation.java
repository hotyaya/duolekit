package cn.railsoft.simulate.client.ui;

import javax.swing.JTextPane;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import cn.railsoft.simulate.model.PointDemo;

public class ChangeLocation extends Thread {
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;
	public static JTextPane jtp = null;
	
	public ChangeLocation(JTextPane jtp) {
		this.jtp = jtp;
		try {
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration(
					"railsoft.cn", 5222);
			con = new XMPPConnection(config);

			// connect and login with the username and pwd on server
			con.connect();
			con.login("user1", "user1");
			System.out.println(con.getUser());
			// System.out.print(con.getRoster().getEntries().toArray()[0].toString());

			System.out.println("\n Authenticated = " + con.isAuthenticated());

			// add a listener to receive all messages
			// addListener();
			chatmanager = con.getChatManager();
			newChat = chatmanager.createChat("all@broadcast.railsoft.cn",
					new MessageListener() {// all@broadcast.hotyaya-ge2w3vb;
											// user2@hotyaya-ge2w3vb
						public void processMessage(Chat chat, Message message) {
							System.out.println("ing: " + message.getBody());
							if (ChangeLocation.jtp !=null) ChangeLocation.jtp.setText("收到位置:"+message.getBody()+"\n"+ChangeLocation.jtp.getText());
						}
					});
			//newChat.sendMessage("hi");
		} catch (XMPPException e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			//con.disconnect();
		}
	}

	PointDemo point = new PointDemo();
	@Override
	public void run() {
		while (true) {
			try {
				point.run();
				newChat.sendMessage(point.toString());
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
