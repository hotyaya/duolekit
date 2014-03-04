package org.job.crawler.register;

import org.apache.http.client.CookieStore;

public interface IRegister {

	public abstract void docrawler(String user,String pass,String domain,String serverip);

	public abstract CookieStore getCookieStore();

	public abstract boolean isRunok();

}