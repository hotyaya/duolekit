package cn.railsoft.simulate.service;

import java.util.Vector;

import cn.railsoft.simulate.pub.Toolkit;

@SuppressWarnings("rawtypes")
public class ServiceItem implements Runnable {
	private boolean isrun = true;
	private String key = "";
	private String name = "";
	private Vector vRoute = new Vector();	//一系列的点的组合
	
	@Override
	public void run() {
		
		while (isrun) {
			
		}
	}
	
	public ServiceItem() {
		super();
		initialize();
	}

	/**
	 * 1。产生一个随机的KEY值 2.从数据库中加载模型 3.开行列车等待增加监听
	 */
	private void initialize() {
		//1.
		key = Toolkit.random4char();
		//2.
		
		//3.初始化加入监听
		
	}

	/**
	 * 
	 */
	public void addListener(){
		
	}
	
	
	@Override
	public String toString() {
		return key + "-" + name;
	}
}
