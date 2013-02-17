package cn.railsoft.simulate.service;

public class Client {
	public Client(String clientname, boolean isuse) {
		super();
		this.clientname = clientname;
		this.isuse = isuse;
	}

	private String clientname = "";
	private boolean isuse = false;

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	public boolean isIsuse() {
		return isuse;
	}

	public void setIsuse(boolean isuse) {
		this.isuse = isuse;
	}
}
