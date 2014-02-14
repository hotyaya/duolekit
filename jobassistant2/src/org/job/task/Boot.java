package org.job.task;

import org.job.Application;
import org.job.agent.AgentLinker;
import org.job.ui.Mainwin;

public class Boot {
	public static void main(String[] args) {
		//1.准备阶段；
		Application.init0();
		if (Application.init()){
		}
		CrawlerProcess crawlerProcess =new CrawlerProcess();
		//2.加载电报系统和办公系统的密码，弹出对话框并延时确认，
		//3.启动界面，加载数据库中默认的数据取出；
		//4.启动界面; 
		Mainwin window = new Mainwin();
		crawlerProcess.addNotifyListener(window);
		new Thread(crawlerProcess).start(); 
		//5.启动轮训进程从网页上面取得数据；
		FServer server = new FServer(1233,"c:/");
		server.startfs();
		server.stopfs();
		//6.通知界面要进行轮训；禁用
		window.open();
		new AgentLinker("10.64.145.245","Compaq-PC","","","agent"); //Compaq-PC
	}
}
