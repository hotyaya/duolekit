package cn.rs.bbsadmin.test;

import java.awt.EventQueue;

import cn.rs.bbsadmin.gui.BBSAdmin;
import cn.rs.bbsadmin.log.LogDaemon;
import cn.rs.bbsadmin.mem.daemon.Daemon;

public class Starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BBSAdmin window = new BBSAdmin();
					window.getFrame().setLocationRelativeTo(null);
					window.getFrame().setVisible(true);

//					MainWin window = new MainWin();
//					window.open();//在循环中了...
					
					Daemon daemon = new Daemon();
					daemon.addListener(window);
					daemon.addListener(new LogDaemon());
					daemon.start();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
