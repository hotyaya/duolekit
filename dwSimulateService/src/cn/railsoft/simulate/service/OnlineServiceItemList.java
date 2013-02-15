package cn.railsoft.simulate.service;

import java.util.Hashtable;

/**
 * 两层关键值
 * <name,key>
 * <key,ServiceItem>
 * 
 * @author Hui
 *
 */
public class OnlineServiceItemList {
	private Hashtable<String, String> namelist = new Hashtable<String, String>();
	private Hashtable<String, ServiceItem> list = new Hashtable<String, ServiceItem>();

}
