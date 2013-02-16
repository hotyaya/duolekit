package cn.rs.bbsadmin.log;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import cn.rs.bbsadmin.notifyinterface.IReceiveNotice;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

public class LogDaemon implements IReceiveNotice{

	@Override
	public boolean notifyMessage(NoticeMessage message) {
		Logger.getRootLogger().info("LogDaemon=>"+new Timestamp(System.currentTimeMillis())+"\n"+message);
		return true;
	}

	@Override
	public boolean notifyProgress(ProgressMessage progress) {

		return true;
	}
}
