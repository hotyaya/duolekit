package cn.railsoft.im.client.demo;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

/**
 * Chat prototype using smack as client library. Server side are supported by
 * openfire.
 * 
 * @author promise
 * @date 2009-3-30
 * 
 */
public class FireChat2 {
	public static XMPPConnection con;
	public static Chat newChat;
	public static ChatManager chatmanager;

	public static void main(String[] args) throws InterruptedException {
		XMPPConnection.DEBUG_ENABLED = true;
		try {
			// Create a connection to server
			ConnectionConfiguration config = new ConnectionConfiguration("railsoft.cn", 5222);
			con = new XMPPConnection(config);

			// connect and login with the username and pwd on server
			con.connect();
			con.login("user2", "user2");
			System.out.println(con.getUser());
//			System.out.print(con.getRoster().getEntries().toArray()[0].toString());
			
			
			System.out.println("\n Authenticated = " + con.isAuthenticated());

			// add a listener to receive all messages
			addListener();
			chatmanager = con.getChatManager();
			newChat = chatmanager.createChat("user1@hotyaya-ge2w3vb",new MessageListener() {//all@broadcast.railsoft.cn
				public void processMessage(Chat chat, Message message) {
					System.out.println("I'm sending: "+ message.getBody());
				}
			});
			newChat.sendMessage("hi");
		} catch (XMPPException e) {
			e.printStackTrace();
		} finally {
			// 让线程休眠 然后再关闭连接
			Thread.sleep(36000);
			con.disconnect();
		}
	}

	private static void addListener() {
		// just need Messages
		PacketFilter filterMessage = new PacketTypeFilter(Message.class);

		PacketListener myListener = new PacketListener() {
			public void processPacket(Packet packet) {
				System.out.println("From: " + packet.getFrom() + "\n");
				System.out.println("Body: " + ((Message) packet).getBody());
				// when receiving prc's Message, just say something else again
				// and again, robot
				try {
					newChat.sendMessage("hi again");
				} catch (XMPPException e) {
					e.printStackTrace();
				}
			}
		};
		// register the listener to the connection
		con.addPacketListener(myListener, filterMessage);
	}
}
