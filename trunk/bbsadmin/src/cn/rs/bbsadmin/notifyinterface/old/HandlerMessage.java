package cn.rs.bbsadmin.notifyinterface.old;

public class HandlerMessage {
	public HandlerMessage(int mode, int keyid, String field, int value) {
		super();
		this.mode = mode;
		this.keyid = keyid;
		this.field = field;
		this.value = value;
	}
	private int mode = 0;
	private int keyid = 0;
	private String field = "";
	private int value = 0;
	public int getMode() {
		return mode;
	}
	public int getKeyid() {
		return keyid;
	}
	public String getField() {
		return field;
	}
	public int getValue() {
		return value;
	}
}
