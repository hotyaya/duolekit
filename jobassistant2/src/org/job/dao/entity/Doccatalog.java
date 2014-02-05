package org.job.dao.entity;

import java.sql.Timestamp;

/**
 * Doccatalog entity. @author MyEclipse Persistence Tools
 */

public class Doccatalog implements java.io.Serializable {

	// Fields

	private Integer docid;
	private String type;
	private Integer docsenddate;
	private String docsendtime;
	private String docsender;
	private String doccaption;
	private String doccode;
	private String contact;
	private String phone;
	private String baseurl;
	private String url;
	private Integer indate;
	private Timestamp intimestamp;
	private Boolean ishidden;

	// Constructors

	/** default constructor */
	public Doccatalog() {
	}

	/** full constructor */
	public Doccatalog(String type, Integer docsenddate, String docsendtime,
			String docsender, String doccaption, String doccode,
			String contact, String phone, String baseurl, String url,
			Integer indate, Timestamp intimestamp, Boolean ishidden) {
		this.type = type;
		this.docsenddate = docsenddate;
		this.docsendtime = docsendtime;
		this.docsender = docsender;
		this.doccaption = doccaption;
		this.doccode = doccode;
		this.contact = contact;
		this.phone = phone;
		this.baseurl = baseurl;
		this.url = url;
		this.indate = indate;
		this.intimestamp = intimestamp;
		this.ishidden = ishidden;
	}

	// Property accessors

	public Integer getDocid() {
		return this.docid;
	}

	public void setDocid(Integer docid) {
		this.docid = docid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDocsenddate() {
		return this.docsenddate;
	}

	public void setDocsenddate(Integer docsenddate) {
		this.docsenddate = docsenddate;
	}

	public String getDocsendtime() {
		return this.docsendtime;
	}

	public void setDocsendtime(String docsendtime) {
		this.docsendtime = docsendtime;
	}

	public String getDocsender() {
		return this.docsender;
	}

	public void setDocsender(String docsender) {
		this.docsender = docsender;
	}

	public String getDoccaption() {
		return this.doccaption;
	}

	public void setDoccaption(String doccaption) {
		this.doccaption = doccaption;
	}

	public String getDoccode() {
		return this.doccode;
	}

	public void setDoccode(String doccode) {
		this.doccode = doccode;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBaseurl() {
		return this.baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIndate() {
		return this.indate;
	}

	public void setIndate(Integer indate) {
		this.indate = indate;
	}

	public Timestamp getIntimestamp() {
		return this.intimestamp;
	}

	public void setIntimestamp(Timestamp intimestamp) {
		this.intimestamp = intimestamp;
	}

	public Boolean getIshidden() {
		return this.ishidden;
	}

	public void setIshidden(Boolean ishidden) {
		this.ishidden = ishidden;
	}

}