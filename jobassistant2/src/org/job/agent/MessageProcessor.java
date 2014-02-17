package org.job.agent;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.job.Application;
import org.job.task.FServer;

public class MessageProcessor implements MessageListener {
	FServer server = null;
	
	@Override
	public void processMessage(Chat chat, Message arg1) {
		try {
			//chat.sendMessage(arg1.getBody());
			if (arg1.getProperty("MSGTYPE")!=null && (arg1.getProperty("MSGTYPE").equals("CMD"))){
				String cmd = arg1.getBody();
				if ((cmd.equals("SYSSTOP"))){
					//chat.sendMessage("system stop!");
					System.exit(0);
				}else if (cmd.equals("FA")){
					server = new FServer(Integer.parseInt(arg1.getProperty("FPORT").toString().trim()),
							arg1.getProperty("FDIR").toString().trim());
					server.startfs();
					chat.sendMessage("F-A-OK");
				}else if (cmd.equals("FO")){
					server.stopfs();
					server = null;
					chat.sendMessage("F-O-OK");
				}else if (cmd.equals("SYSPROP")){
					try {
						sendsysteminfo(chat);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					chat.sendMessage("SYSPROP-OK");
				}else{
					chat.sendMessage("ELSE-OK");
				}
			}else if (arg1.getProperty("MSGTYPE")!=null && (arg1.getProperty("MSGTYPE").equals("INFO"))){
				chat.sendMessage("recv:"+arg1.getBody()+"");
			}else{
				chat.sendMessage("else:");
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendsysteminfo(Chat chat) throws UnknownHostException, XMPPException{
		chat.sendMessage("HOSTADDRESS:/"+ InetAddress.getLocalHost().getHostAddress());
		chat.sendMessage("HOSTNAME:/"+ InetAddress.getLocalHost().getHostName());
		Message mesg = new Message();
		mesg.setProperty("MSGTYPE", "SYSINFO");
		Properties pros0 = Application.getPros();
		Enumeration enu = pros0.keys();
		while (enu.hasMoreElements()) {
			String key=enu.nextElement().toString().trim();
			mesg.setProperty(key, pros0.getProperty(key).toString().trim());
			//System.out.println(":"+pros0.getProperty(key).toString().trim());
		}
		mesg.setBody("SYSTEM");
		chat.sendMessage(mesg);
	}
	
	
}
