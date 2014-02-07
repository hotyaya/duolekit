package org.job.task;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

public class FServer {
	public FServer(int port, String path) {
		super();
		this.port = port;
		this.path = path;
	}

	private int port = 1234;
	private String path ="c:/";
	private FtpServer server = null;
	
	public void startfs(){
		try {
			FtpServerFactory serverFactory = new FtpServerFactory();
			ListenerFactory factory = new ListenerFactory();
			PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
			// set the port of the listener
			factory.setPort(port);
			// replace the default listener
			serverFactory.addListener("default", factory.createListener());
			userManagerFactory.setFile(new File("u.properties"));				//设置属性
			userManagerFactory.setPath(path);
			userManagerFactory.setUserpassword("");
			serverFactory.setUserManager(userManagerFactory.createUserManager());	//使用属性建立用户
			// start the server
			server = serverFactory.createServer();         
			server.start();
			//server.stop();
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}
	
	public void stopfs(){
		if (server!=null) {
			server.stop();	
		}
	}
	
	public static void main(String[] args) {
		new FServer(1234,"c:/").startfs();
	}
	
}
