package cn.railsoft.schedule.app.shared;

import java.sql.Timestamp;

/**
 * Jobitem entity. @author MyEclipse Persistence Tools
 */

public class Jobitem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1989357242643285668L;
	private String id;
	private Timestamp createTime;
	private Integer userid;
	private Long seq;
	private Integer actionDate;
	private String ip;
	private String type;
	private String numPrefix;
	private String numNo;
	private String numSuffix;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private String content5;
	private String source;
	private String sourceId;
	private String status;
	private Timestamp statusCreatetime;
	private String memo;

	// Constructors

	/** default constructor */
	public Jobitem() {
	}

	/** minimal constructor */
	public Jobitem(Timestamp createTime, Timestamp statusCreatetime) {
		this.createTime = createTime;
		this.statusCreatetime = statusCreatetime;
	}

	/** full constructor */
	public Jobitem(Timestamp createTime, Integer userid, Long seq,
			Integer actionDate, String ip, String type, String numPrefix,
			String numNo, String numSuffix, String content1, String content2,
			String content3, String content4, String content5, String source,
			String sourceId, String status, Timestamp statusCreatetime,
			String memo) {
		this.createTime = createTime;
		this.userid = userid;
		this.seq = seq;
		this.actionDate = actionDate;
		this.ip = ip;
		this.type = type;
		this.numPrefix = numPrefix;
		this.numNo = numNo;
		this.numSuffix = numSuffix;
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.content4 = content4;
		this.content5 = content5;
		this.source = source;
		this.sourceId = sourceId;
		this.status = status;
		this.statusCreatetime = statusCreatetime;
		this.memo = memo;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Long getSeq() {
		return this.seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public Integer getActionDate() {
		return this.actionDate;
	}

	public void setActionDate(Integer actionDate) {
		this.actionDate = actionDate;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumPrefix() {
		return this.numPrefix;
	}

	public void setNumPrefix(String numPrefix) {
		this.numPrefix = numPrefix;
	}

	public String getNumNo() {
		return this.numNo;
	}

	public void setNumNo(String numNo) {
		this.numNo = numNo;
	}

	public String getNumSuffix() {
		return this.numSuffix;
	}

	public void setNumSuffix(String numSuffix) {
		this.numSuffix = numSuffix;
	}

	public String getContent1() {
		return this.content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return this.content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return this.content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getContent4() {
		return this.content4;
	}

	public void setContent4(String content4) {
		this.content4 = content4;
	}

	public String getContent5() {
		return this.content5;
	}

	public void setContent5(String content5) {
		this.content5 = content5;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getStatusCreatetime() {
		return this.statusCreatetime;
	}

	public void setStatusCreatetime(Timestamp statusCreatetime) {
		this.statusCreatetime = statusCreatetime;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}