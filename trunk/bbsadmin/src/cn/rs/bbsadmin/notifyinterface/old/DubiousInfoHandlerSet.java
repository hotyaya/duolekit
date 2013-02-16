package cn.rs.bbsadmin.notifyinterface.old;

import java.util.Vector;


public abstract class DubiousInfoHandlerSet {
	
	private Vector<IDubiousInfoHandler> vListeners = new Vector<IDubiousInfoHandler>();	//2012-05-05

	public void addListener(IDubiousInfoHandler irn){
		vListeners.add(irn);
	}
	
	public void notifyMessageOut(HandlerMessage message){
		for (int i=0;i<vListeners.size();i++){
			((IDubiousInfoHandler)vListeners.elementAt(i)).notifyMessage(message);
		}
	}
}
