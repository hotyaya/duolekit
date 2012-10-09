package cn.rdm.biz;

/**
 * 工作的操作员（技工）（非本软件用户）
 * 本软件用户使用USER
 * 
 * @author Hui
 *
 */
public class Operator {
	private String opercode = "";
	private String opername = "";
	public String getOpercode() {
		return opercode;
	}
	public void setOpercode(String opercode) {
		this.opercode = opercode;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}

}
