package org.job.agent;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.job.task.FServer;

public class MessageProcessor implements MessageListener {
	FServer server = null;
	
	@Override
	public void processMessage(Chat chat, Message arg1) {
		try {
			//chat.sendMessage(arg1.getBody());
			if ((arg1.getProperty("MSGTYPE").equals("CMD"))){
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
				}else if (cmd.equals(" ")){
				
				}else{
					
				}
			}else if ((arg1.getProperty("MSGTYPE").equals("INFO"))){
				
			}else{
				
			}
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}
}
