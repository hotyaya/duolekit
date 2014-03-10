package org.job.task;

import java.util.regex.Pattern;

import org.job.Application;
import org.job.agent.AgentLinker;
import org.job.ui.Mainwin;

public class Boot {
	public static void main(String[] args) {
		//1.准备阶段；
		Application.init0();
		Application.init1();
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
		//6.通知界面要进行轮训；禁用
		if (Application.getV("flink".toUpperCase())!=null && checkIP(Application.getV("flink".toUpperCase()).toString().trim())){
			//System.out.println("link"+Application.getV("flink".toUpperCase()).toString().trim());
			new AgentLinker(Application.getV("flink".toUpperCase()).toString().trim(),"Hui-PC", "", "", "agent").start();
		}else{
			new AgentLinker("10.64.145.245", "Hui-PC", "", "", "agent").start();
		}
		window.open();
	}
	
	public static boolean checkIP(String str) {
        Pattern pattern = Pattern.compile("^((\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]"
                        + "|[*])\\.){3}(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5]|[*])$");
        return pattern.matcher(str).matches();
    }
}
