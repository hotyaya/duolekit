package org.job.dao.entity;

/**
 * Docrecv entity. @author MyEclipse Persistence Tools
 */

public class Docrecv implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7894534673109345370L;
	private Integer docid;
	private String transmitter;
	private String recvdate;
	private String opertimestamp;
	private String recvTag;
	private String memo;
	private String triggertime;
	private String isok;
	private String oktimestamp;
	private String okmemo;

	// Constructors

	/** default constructor */
	public Docrecv() {
	}

	/** minimal constructor */
	public Docrecv(Integer docid) {
		this.docid = docid;
	}

	/** full constructor */
	public Docrecv(Integer docid, String transmitter, String recvdate,
			String opertimestamp, String recvTag, String memo,
			String triggertime, String isok, String oktimestamp, String okmemo) {
		this.docid = docid;
		this.transmitter = transmitter;
		this.recvdate = recvdate;
		this.opertimestamp = opertimestamp;
		this.recvTag = recvTag;
		this.memo = memo;
		this.triggertime = triggertime;
		this.isok = isok;
		this.oktimestamp = oktimestamp;
		this.okmemo = okmemo;
	}

	// Property accessors

	public Integer getDocid() {
		return this.docid;
	}

	public void setDocid(Integer docid) {
		this.docid = docid;
	}

	public String getTransmitter() {
		return this.transmitter;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}

	public String getRecvdate() {
		return this.recvdate;
	}

	public void setRecvdate(String recvdate) {
		this.recvdate = recvdate;
	}

	public String getOpertimestamp() {
		return this.opertimestamp;
	}

	public void setOpertimestamp(String opertimestamp) {
		this.opertimestamp = opertimestamp;
	}

	public String getRecvTag() {
		return this.recvTag;
	}

	public void setRecvTag(String recvTag) {
		this.recvTag = recvTag;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTriggertime() {
		return this.triggertime;
	}

	public void setTriggertime(String triggertime) {
		this.triggertime = triggertime;
	}

	public String getIsok() {
		return this.isok;
	}

	public void setIsok(String isok) {
		this.isok = isok;
	}

	public String getOktimestamp() {
		return this.oktimestamp;
	}

	public void setOktimestamp(String oktimestamp) {
		this.oktimestamp = oktimestamp;
	}

	public String getOkmemo() {
		return this.okmemo;
	}

	public void setOkmemo(String okmemo) {
		this.okmemo = okmemo;
	}

}