package cn.rs.bbsadmin.notifyinterface.message;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeMessage implements Serializable {
	
	SimpleDateFormat format = new SimpleDateFormat("dd HH:mm:ss");
	private int i_MODE = 1;			//换行处理消息 2.为不换行处理
	private int i_SHOW_SCREEN =1;	//在屏幕上显示
	private int i_TOLOG =1;			//记录到日志
	
	
	public NoticeMessage(Timestamp now, String message,int i_MODE) {
		super();
		this.now = now;
		this.message = message;
		this.i_MODE = i_MODE;
	}
	public NoticeMessage(Timestamp now, String message) {
		super();
		this.now = now;
		this.message = message;
	}
	public NoticeMessage(String message) {
		super();
		this.now = new Timestamp(System.currentTimeMillis());
		this.message = message;
	}
	
	public NoticeMessage(String message,int i_MODE) {
		super();
		this.now = new Timestamp(System.currentTimeMillis());
		this.message = message;
		this.i_MODE = i_MODE;
	}
	
	private Timestamp now;
	private String message;
	public Timestamp getNow() {
		return now;
	}
	public String getMessage() {
		String msg = "";
		switch(i_MODE){
		case 1:
			msg = message;
			break;
		case 2://暂未处理
			
			msg = "<"+message+">";
			break;
		case 3://暂未处理 
			msg = message;
			break;
		}
		return msg;
	}
	@Override
	public String toString() {
		String strtag= "";
		if (i_MODE==1){
			strtag = "\n";
		}else {
			strtag = "、";
		}
		return ""+((i_MODE==1)?format.format(new Date(now.getTime())):"") + "-" + message + ""+strtag;
	}
	public int getI_MODE() {
		return i_MODE;
	}
	public int getI_SHOW_SCREEN() {
		return i_SHOW_SCREEN;
	}
	public int getI_TOLOG() {
		return i_TOLOG;
	}
}
