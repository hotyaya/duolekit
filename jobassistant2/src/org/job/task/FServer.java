package org.job.task;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;

public class FServer {

	private int port = 1234;
	private String path = "c:/";
	FtpServerFactory serverFactory = null;
	ListenerFactory factory = null;
	PropertiesUserManagerFactory userManagerFactory = null;
	private FtpServer server = null;

	public FServer(int port, String path) {
		super();
		this.port = port;
		this.path = path;
		try {
			serverFactory = new FtpServerFactory();
			factory = new ListenerFactory();
			userManagerFactory = new PropertiesUserManagerFactory();
			// set the port of the listener
			factory.setPort(port);
			// replace the default listener
			serverFactory.addListener("default", factory.createListener());
			userManagerFactory.setFile(new File("u.properties")); // 设置属性
			userManagerFactory.setPath(path);
			userManagerFactory.setUserpassword("");
			serverFactory
					.setUserManager(userManagerFactory.createUserManager()); // 使用属性建立用户
			// start the server
			server = serverFactory.createServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startfs() {
		try {
			if (server != null) {
				server.start();
			}
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}

	public void stopfs() {
		try {
			if (server != null) {
				server.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new FServer(1234, "c:/").startfs();
	}

}
