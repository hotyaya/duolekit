package cn.railsoft.jobhelper.sina;

public class SinaLoginMessage {
	private String email,nonceCode,serverTime,password;

	public SinaLoginMessage(String email, String nonceCode,
			String serverTime, String password) {
		this.email = email;
		this.nonceCode = nonceCode;
		this.serverTime = serverTime;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNonceCode() {
		return nonceCode;
	}

	public void setNonceCode(String nonceCode) {
		this.nonceCode = nonceCode;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
