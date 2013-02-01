package cn.railsoft.simulate.model;

import java.io.Serializable;

public class TrainDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065733133303349076L;
	private String checi = "";// 车次
	private long ichechang = 0;// 车长
	
	public String getCheci() {
		return checi;
	}
	public void setCheci(String checi) {
		this.checi = checi;
	}
	public long getIchechang() {
		return ichechang;
	}
	public void setIchechang(long ichechang) {
		this.ichechang = ichechang;
	}

}
