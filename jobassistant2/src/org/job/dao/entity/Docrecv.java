package org.job.dao.entity;

import java.sql.Timestamp;

/**
 * Docrecv entity. @author MyEclipse Persistence Tools
 */

public class Docrecv implements java.io.Serializable {

	// Fields

	private Integer docid;
	private String transmitter;
	private Integer recvdate;
	private Timestamp opertimestamp;
	private String recvtag;
	private String memo;
	private String triggertime;
	private Boolean isok;
	private Timestamp oktimestamp;
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
	public Docrecv(Integer docid, String transmitter, Integer recvdate,
			Timestamp opertimestamp, String recvtag, String memo,
			String triggertime, Boolean isok, Timestamp oktimestamp,
			String okmemo) {
		this.docid = docid;
		this.transmitter = transmitter;
		this.recvdate = recvdate;
		this.opertimestamp = opertimestamp;
		this.recvtag = recvtag;
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

	public Integer getRecvdate() {
		return this.recvdate;
	}

	public void setRecvdate(Integer recvdate) {
		this.recvdate = recvdate;
	}

	public Timestamp getOpertimestamp() {
		return this.opertimestamp;
	}

	public void setOpertimestamp(Timestamp opertimestamp) {
		this.opertimestamp = opertimestamp;
	}

	public String getRecvtag() {
		return this.recvtag;
	}

	public void setRecvtag(String recvtag) {
		this.recvtag = recvtag;
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

	public Boolean getIsok() {
		return this.isok;
	}

	public void setIsok(Boolean isok) {
		this.isok = isok;
	}

	public Timestamp getOktimestamp() {
		return this.oktimestamp;
	}

	public void setOktimestamp(Timestamp oktimestamp) {
		this.oktimestamp = oktimestamp;
	}

	public String getOkmemo() {
		return this.okmemo;
	}

	public void setOkmemo(String okmemo) {
		this.okmemo = okmemo;
	}

}