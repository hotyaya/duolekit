package org.job.task;

import org.job.ui.Mainwin;

public class Boot {

	public static void main(String[] args) {
		//1.加载电报系统和办公系统的密码，弹出对话框并延时确认，
		
		//2.启动界面，加载数据库中默认的数据取出；
		
		//3.启动轮训进程从网页上面取得数据；
		new Thread(new CrawlerProcess()).start();
		//4.通知界面要进行轮训；禁用

		//5.启动界面; 
		Mainwin window = new Mainwin();
		window.open();
	}
}
