package railway.bj.admin.my.job.dao.entity;

import java.sql.Timestamp;

/**
 * Docrecv entity. @author MyEclipse Persistence Tools
 */

public class Docrecv implements java.io.Serializable {

	// Fields

	private Long docid;
	private String transmitter;
	private Integer recvdate;
	private Timestamp opertimestamp;
	private String recvTag;
	private String memo;
	private Boolean isok;

	// Constructors

	/** default constructor */
	public Docrecv() {
	}

	/** minimal constructor */
	public Docrecv(Long docid) {
		this.docid = docid;
	}

	/** full constructor */
	public Docrecv(Long docid, String transmitter, Integer recvdate,
			Timestamp opertimestamp, String recvTag, String memo, Boolean isok) {
		this.docid = docid;
		this.transmitter = transmitter;
		this.recvdate = recvdate;
		this.opertimestamp = opertimestamp;
		this.recvTag = recvTag;
		this.memo = memo;
		this.isok = isok;
	}

	// Property accessors

	public Long getDocid() {
		return this.docid;
	}

	public void setDocid(Long docid) {
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

	public Boolean getIsok() {
		return this.isok;
	}

	public void setIsok(Boolean isok) {
		this.isok = isok;
	}

}