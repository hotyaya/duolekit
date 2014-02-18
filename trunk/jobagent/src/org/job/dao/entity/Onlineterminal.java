package org.job.dao.entity;

import java.sql.Timestamp;

/**
 * Onlineterminal entity. @author MyEclipse Persistence Tools
 */

public class Onlineterminal implements java.io.Serializable {

	// Fields

	private String threadid;
	private String ip;
	private String hostname;
	private Timestamp lastonlinetime;
	private Boolean isactive;

	// Constructors

	/** default constructor */
	public Onlineterminal() {
	}

	/** minimal constructor */
	public Onlineterminal(String threadid, Timestamp lastonlinetime) {
		this.threadid = threadid;
		this.lastonlinetime = lastonlinetime;
	}

	/** full constructor */
	public Onlineterminal(String threadid, String ip, String hostname,
			Timestamp lastonlinetime, Boolean isactive) {
		this.threadid = threadid;
		this.ip = ip;
		this.hostname = hostname;
		this.lastonlinetime = lastonlinetime;
		this.isactive = isactive;
	}

	// Property accessors

	public String getThreadid() {
		return this.threadid;
	}

	public void setThreadid(String threadid) {
		this.threadid = threadid;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Timestamp getLastonlinetime() {
		return this.lastonlinetime;
	}

	public void setLastonlinetime(Timestamp lastonlinetime) {
		this.lastonlinetime = lastonlinetime;
	}

	public Boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

}