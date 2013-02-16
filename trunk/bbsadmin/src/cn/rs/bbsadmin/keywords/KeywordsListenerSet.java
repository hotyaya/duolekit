package cn.rs.bbsadmin.keywords;

import java.util.Vector;

import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;
import cn.rs.bbsadmin.notifyinterface.old.HandlerMessage;

public abstract class KeywordsListenerSet {
	
	private Vector<IKeyWordsChanged> vListeners = new Vector<IKeyWordsChanged>();	//2012-05-05

	public void addListener(IKeyWordsChanged irn){
		vListeners.add(irn);
	}
	
	public void notifyKeyWordsChanged(NoticeMessage message){
		for (int i=0;i<vListeners.size();i++){
			((IKeyWordsChanged)vListeners.elementAt(i)).changeKeyWords(true);
		}
	}
}
