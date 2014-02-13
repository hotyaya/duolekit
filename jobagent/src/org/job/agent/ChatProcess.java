package org.job.agent;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import org.jivesoftware.smack.Chat;
import org.job.agent.interf.INotifyObject;

public class ChatProcess {
	Vector<INotifyObject> v = new Vector<INotifyObject>();
	Hashtable<String, ChatMessageCollection> threadidtable = new Hashtable<String, ChatMessageCollection>();
	//Hashtable<String, ChatMessageCollection> participanttable = new Hashtable<String, ChatMessageCollection>();

	public Vector<String> getThreadCollection(){
		Vector<String> v = new Vector();
		Enumeration enu = threadidtable.keys();
		while(enu.hasMoreElements()){
			v.add(enu.nextElement().toString().trim());
		}
		return v;
	} 

	public Vector<ChatMessageCollection> getCMCs(){
		Vector<ChatMessageCollection> v = new Vector();
		Enumeration enu = threadidtable.keys();
		while(enu.hasMoreElements()){
			v.add(threadidtable.get(enu.nextElement().toString().trim()));
		}
		return v;
	} 
	
	public void setChatMessageCollection(ChatMessageCollection cmc) {
		threadidtable.put(cmc.getThreadId(), cmc);
		//participanttable.put(cmc.getParticipant(), cmc);
		notifyChat();
	}

	public ChatMessageCollection getCMC(String threadid) {
		return threadidtable.get(threadid);
	}
	
	public Chat getCMCChat(String threadid) {
		return threadidtable.get(threadid).getChat();
	}

	public void addListener(INotifyObject ino) {
		v.add(ino);
	}

	public void notifyMessage() {
		for (int i = 0; i < v.size(); i++) {
			v.elementAt(i).notifyMessage();
		}
	}
	public void notifyChat() {
		for (int i = 0; i < v.size(); i++) {
			v.elementAt(i).notifyChat();
		}
	}
	

}