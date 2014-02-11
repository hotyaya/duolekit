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
	private Boolean islooked;
	private Boolean istodo;
	private Timestamp istodotime;
	private String todomemo;
	private Boolean isdoned;
	private Timestamp isdonedtime;
	private String donememo;

	// Constructors

	/** default constructor */
	public Doccatalog() {
	}

	/** full constructor */
	public Doccatalog(String type, Integer docsenddate, String docsendtime,
			String docsender, String doccaption, String doccode,
			String contact, String phone, String baseurl, String url,
			Integer indate, Timestamp intimestamp, Boolean ishidden,
			Boolean islooked, Boolean istodo, Timestamp istodotime,
			String todomemo, Boolean isdoned, Timestamp isdonedtime,
			String donememo) {
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
		this.islooked = islooked;
		this.istodo = istodo;
		this.istodotime = istodotime;
		this.todomemo = todomemo;
		this.isdoned = isdoned;
		this.isdonedtime = isdonedtime;
		this.donememo = donememo;
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

	public Boolean getIslooked() {
		return this.islooked;
	}

	public void setIslooked(Boolean islooked) {
		this.islooked = islooked;
	}

	public Boolean getIstodo() {
		return this.istodo;
	}

	public void setIstodo(Boolean istodo) {
		this.istodo = istodo;
	}

	public Timestamp getIstodotime() {
		return this.istodotime;
	}

	public void setIstodotime(Timestamp istodotime) {
		this.istodotime = istodotime;
	}

	public String getTodomemo() {
		return this.todomemo;
	}

	public void setTodomemo(String todomemo) {
		this.todomemo = todomemo;
	}

	public Boolean getIsdoned() {
		return this.isdoned;
	}

	public void setIsdoned(Boolean isdoned) {
		this.isdoned = isdoned;
	}

	public Timestamp getIsdonedtime() {
		return this.isdonedtime;
	}

	public void setIsdonedtime(Timestamp isdonedtime) {
		this.isdonedtime = isdonedtime;
	}

	public String getDonememo() {
		return this.donememo;
	}

	public void setDonememo(String donememo) {
		this.donememo = donememo;
	}

}