package org.job.agent;

import java.util.Vector;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;

@SuppressWarnings("rawtypes")
public class ChatMessageCollection {
	private Chat chat = null;
	private Vector vmesg = new Vector();

	public Vector getVmesg() {
		return vmesg;
	}

	public ChatMessageCollection(Chat chat) {
		super();
		this.chat = chat;
	}

	public String getThreadId() {
		return chat == null ? "null" : chat.getThreadID();
	}

	public String getParticipant() {
		return chat.getParticipant();
	}

	@SuppressWarnings("unchecked")
	public void putMessage(Message arg1) {
		vmesg.add(arg1);
		//if (vmesg.size()>20)vmesg.remove(0);
	}

	public Chat getChat() {
		return chat;
	}
	
}
