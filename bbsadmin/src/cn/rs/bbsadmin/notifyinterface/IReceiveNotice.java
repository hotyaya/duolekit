package cn.rs.bbsadmin.notifyinterface;

import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

public interface IReceiveNotice {
	public boolean notifyMessage(NoticeMessage message);
	public boolean notifyProgress(ProgressMessage progress);	
}
