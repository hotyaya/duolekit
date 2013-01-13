package cn.railsoft.wtk.main.shared;

import java.sql.Timestamp;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MyO implements IsSerializable {
	private String str1 = "";
	private String str2 = "";
	private Timestamp ts  = null;
	public Timestamp getTs() {
		return ts;
	}
	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
	public String getStr1() {
		return str1;
	}
	public void setStr1(String str1) {
		this.str1 = str1;
	}
	public String getStr2() {
		return str2;
	}
	public void setStr2(String str2) {
		this.str2 = str2;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return str1+"|"+str2+"|"+(ts==null?"xxx":ts.toString());
	}
}
