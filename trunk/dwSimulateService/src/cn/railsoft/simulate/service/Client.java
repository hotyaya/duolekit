package cn.railsoft.simulate.service;

public class Client {

	public Client(String clientname, boolean used) {
		super();
		this.clientname = clientname;
		this.used = used;
	}

	private String clientname = "";
	private boolean used = false;

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}
