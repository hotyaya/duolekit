package cn.rs.bbsadmin.notifyinterface;

import java.util.Vector;

import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;
import cn.rs.bbsadmin.notifyinterface.old.HandlerMessage;

public abstract class ReceiveNoticeSet {
	
	private Vector<IReceiveNotice> vListeners = new Vector<IReceiveNotice>();	//2012-05-05

	public void addListener(IReceiveNotice irn){
		vListeners.add(irn);
	}
	
	public void notifyMessageOut(NoticeMessage message){
		for (int i=0;i<vListeners.size();i++){
			((IReceiveNotice)vListeners.elementAt(i)).notifyMessage(message);
		}
	}
	public void notifyProgressOut(ProgressMessage progress){
		for (int i=0;i<vListeners.size();i++){
			((IReceiveNotice)vListeners.elementAt(i)).notifyProgress(progress);
		}
	}
	
	

}
