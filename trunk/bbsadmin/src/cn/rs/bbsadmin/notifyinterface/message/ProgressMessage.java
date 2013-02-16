package cn.rs.bbsadmin.notifyinterface.message;

public class ProgressMessage {
	public ProgressMessage(int iTotle, int iProgress) {
		super();
		this.iTotle = iTotle;
		this.iProgress = iProgress;
	}
	private int iTotle = 0;
	private int iProgress = 0;
	public int getiTotle() {
		return iTotle;
	}
	public void setiTotle(int iTotle) {
		this.iTotle = iTotle;
	}
	public int getiProgress() {
		return iProgress;
	}
	public void setiProgress(int iProgress) {
		this.iProgress = iProgress;
	}
}
