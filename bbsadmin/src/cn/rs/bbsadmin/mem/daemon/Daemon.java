package cn.rs.bbsadmin.mem.daemon;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.bbsadmin.keywords.KeywordsUtil;
import cn.rs.bbsadmin.mem.LocalCacheUtil;
import cn.rs.bbsadmin.notifyinterface.IReceiveNotice;
import cn.rs.bbsadmin.notifyinterface.ReceiveNoticeSet;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

public class Daemon  extends ReceiveNoticeSet implements Runnable ,IReceiveNotice{
	
	private boolean run = true;		//进程运行
	private boolean oper = true;	//在操作中	可根据时间段自动运行；
	private int interval = 240;		//单位为运行间隔秒
	private int iCheckLine = 60;	//向前140个小时以内的全部帖子 20120515改为分钟为单位  60分钟为
	
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private LocalCacheUtil localcacheutil = new LocalCacheUtil(iCheckLine,context);
	private DubiousInfoHandler dubiousInfoHandler = new DubiousInfoHandler(1,context); //1-审核
	private AutoContrast autocontrast = new AutoContrast(dubiousInfoHandler,localcacheutil,interval);
	private Thread thread = null;

	public Daemon() {
		autocontrast.addListener(this);//把自己加到查找器
		dubiousInfoHandler.addListener(this);//把自己加到处理器
		thread = new Thread(this);
	}

	public void run() {
		int itemp = 0;
		while (run) {
			if(oper){
				try {
					itemp ++; if (itemp>2000) System.exit(0);	//大概5天
					localcacheutil.initData();
					autocontrast.contrastByUser();
					autocontrast.contrastByKeyword();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			try{
				notifyMessageOut(new NoticeMessage("休眠" + interval + "秒"));
				Thread.sleep(interval * 1000);
				notifyMessageOut(new NoticeMessage("唤醒开始追踪"));
				notifyProgressOut(new ProgressMessage(0,0));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}
	}

	public void start() {
		thread.start();
	}

	@Override
	public boolean notifyMessage(NoticeMessage message) {
		notifyMessageOut(message);
		return true;
	}

	@Override
	public boolean notifyProgress(ProgressMessage progress) {
		notifyProgressOut(progress);
		return false;
	}

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public boolean isOper() {
		return oper;
	}

	public void setOper(boolean oper) {
		this.oper = oper;
	}
}
