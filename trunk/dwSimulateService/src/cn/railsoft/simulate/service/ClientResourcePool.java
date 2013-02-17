package cn.railsoft.simulate.service;

import java.util.Vector;

public class ClientResourcePool {

	public ClientResourcePool() {
		super();
		initialize();
	}

	private Vector<Client> vpool = new Vector<Client>();

	private void initialize() {
		for (int i = 0; i < 3; i++) {
			vpool.add(new Client("user" + i, false));
		}
	}
	
	
	
}
