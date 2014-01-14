package railway.bj.admin.my.job.dao.entity;

import java.sql.Timestamp;

/**
 * VdocrecvId entity. @author MyEclipse Persistence Tools
 */

public class VdocrecvId implements java.io.Serializable {

	// Fields

	private Long docid;
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
	private String transmitter;
	private Integer recvdate;
	private Timestamp opertimestamp;
	private String recvTag;
	private String memo;
	private String triggertime;
	private Boolean isok;
	private Timestamp oktimestamp;
	private String okmemo;

	// Constructors

	/** default constructor */
	public VdocrecvId() {
	}

	/** minimal constructor */
	public VdocrecvId(Long docid, Timestamp intimestamp) {
		this.docid = docid;
		this.intimestamp = intimestamp;
	}

	/** full constructor */
	public VdocrecvId(Long docid, String type, Integer docsenddate,
			String docsendtime, String docsender, String doccaption,
			String doccode, String contact, String phone, String baseurl,
			String url, Integer indate, Timestamp intimestamp,
			Boolean ishidden, String transmitter, Integer recvdate,
			Timestamp opertimestamp, String recvTag, String memo,
			String triggertime, Boolean isok, Timestamp oktimestamp,
			String okmemo) {
		this.docid = docid;
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

	public Long getDocid() {
		return this.docid;
	}

	public void setDocid(Long docid) {
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VdocrecvId))
			return false;
		VdocrecvId castOther = (VdocrecvId) other;

		return ((this.getDocid() == castOther.getDocid()) || (this.getDocid() != null
				&& castOther.getDocid() != null && this.getDocid().equals(
				castOther.getDocid())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getDocsenddate() == castOther.getDocsenddate()) || (this
						.getDocsenddate() != null
						&& castOther.getDocsenddate() != null && this
						.getDocsenddate().equals(castOther.getDocsenddate())))
				&& ((this.getDocsendtime() == castOther.getDocsendtime()) || (this
						.getDocsendtime() != null
						&& castOther.getDocsendtime() != null && this
						.getDocsendtime().equals(castOther.getDocsendtime())))
				&& ((this.getDocsender() == castOther.getDocsender()) || (this
						.getDocsender() != null
						&& castOther.getDocsender() != null && this
						.getDocsender().equals(castOther.getDocsender())))
				&& ((this.getDoccaption() == castOther.getDoccaption()) || (this
						.getDoccaption() != null
						&& castOther.getDoccaption() != null && this
						.getDoccaption().equals(castOther.getDoccaption())))
				&& ((this.getDoccode() == castOther.getDoccode()) || (this
						.getDoccode() != null && castOther.getDoccode() != null && this
						.getDoccode().equals(castOther.getDoccode())))
				&& ((this.getContact() == castOther.getContact()) || (this
						.getContact() != null && castOther.getContact() != null && this
						.getContact().equals(castOther.getContact())))
				&& ((this.getPhone() == castOther.getPhone()) || (this
						.getPhone() != null && castOther.getPhone() != null && this
						.getPhone().equals(castOther.getPhone())))
				&& ((this.getBaseurl() == castOther.getBaseurl()) || (this
						.getBaseurl() != null && castOther.getBaseurl() != null && this
						.getBaseurl().equals(castOther.getBaseurl())))
				&& ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null
						&& castOther.getUrl() != null && this.getUrl().equals(
						castOther.getUrl())))
				&& ((this.getIndate() == castOther.getIndate()) || (this
						.getIndate() != null && castOther.getIndate() != null && this
						.getIndate().equals(castOther.getIndate())))
				&& ((this.getIntimestamp() == castOther.getIntimestamp()) || (this
						.getIntimestamp() != null
						&& castOther.getIntimestamp() != null && this
						.getIntimestamp().equals(castOther.getIntimestamp())))
				&& ((this.getIshidden() == castOther.getIshidden()) || (this
						.getIshidden() != null
						&& castOther.getIshidden() != null && this
						.getIshidden().equals(castOther.getIshidden())))
				&& ((this.getTransmitter() == castOther.getTransmitter()) || (this
						.getTransmitter() != null
						&& castOther.getTransmitter() != null && this
						.getTransmitter().equals(castOther.getTransmitter())))
				&& ((this.getRecvdate() == castOther.getRecvdate()) || (this
						.getRecvdate() != null
						&& castOther.getRecvdate() != null && this
						.getRecvdate().equals(castOther.getRecvdate())))
				&& ((this.getOpertimestamp() == castOther.getOpertimestamp()) || (this
						.getOpertimestamp() != null
						&& castOther.getOpertimestamp() != null && this
						.getOpertimestamp()
						.equals(castOther.getOpertimestamp())))
				&& ((this.getRecvTag() == castOther.getRecvTag()) || (this
						.getRecvTag() != null && castOther.getRecvTag() != null && this
						.getRecvTag().equals(castOther.getRecvTag())))
				&& ((this.getMemo() == castOther.getMemo()) || (this.getMemo() != null
						&& castOther.getMemo() != null && this.getMemo()
						.equals(castOther.getMemo())))
				&& ((this.getTriggertime() == castOther.getTriggertime()) || (this
						.getTriggertime() != null
						&& castOther.getTriggertime() != null && this
						.getTriggertime().equals(castOther.getTriggertime())))
				&& ((this.getIsok() == castOther.getIsok()) || (this.getIsok() != null
						&& castOther.getIsok() != null && this.getIsok()
						.equals(castOther.getIsok())))
				&& ((this.getOktimestamp() == castOther.getOktimestamp()) || (this
						.getOktimestamp() != null
						&& castOther.getOktimestamp() != null && this
						.getOktimestamp().equals(castOther.getOktimestamp())))
				&& ((this.getOkmemo() == castOther.getOkmemo()) || (this
						.getOkmemo() != null && castOther.getOkmemo() != null && this
						.getOkmemo().equals(castOther.getOkmemo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDocid() == null ? 0 : this.getDocid().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37
				* result
				+ (getDocsenddate() == null ? 0 : this.getDocsenddate()
						.hashCode());
		result = 37
				* result
				+ (getDocsendtime() == null ? 0 : this.getDocsendtime()
						.hashCode());
		result = 37 * result
				+ (getDocsender() == null ? 0 : this.getDocsender().hashCode());
		result = 37
				* result
				+ (getDoccaption() == null ? 0 : this.getDoccaption()
						.hashCode());
		result = 37 * result
				+ (getDoccode() == null ? 0 : this.getDoccode().hashCode());
		result = 37 * result
				+ (getContact() == null ? 0 : this.getContact().hashCode());
		result = 37 * result
				+ (getPhone() == null ? 0 : this.getPhone().hashCode());
		result = 37 * result
				+ (getBaseurl() == null ? 0 : this.getBaseurl().hashCode());
		result = 37 * result
				+ (getUrl() == null ? 0 : this.getUrl().hashCode());
		result = 37 * result
				+ (getIndate() == null ? 0 : this.getIndate().hashCode());
		result = 37
				* result
				+ (getIntimestamp() == null ? 0 : this.getIntimestamp()
						.hashCode());
		result = 37 * result
				+ (getIshidden() == null ? 0 : this.getIshidden().hashCode());
		result = 37
				* result
				+ (getTransmitter() == null ? 0 : this.getTransmitter()
						.hashCode());
		result = 37 * result
				+ (getRecvdate() == null ? 0 : this.getRecvdate().hashCode());
		result = 37
				* result
				+ (getOpertimestamp() == null ? 0 : this.getOpertimestamp()
						.hashCode());
		result = 37 * result
				+ (getRecvTag() == null ? 0 : this.getRecvTag().hashCode());
		result = 37 * result
				+ (getMemo() == null ? 0 : this.getMemo().hashCode());
		result = 37
				* result
				+ (getTriggertime() == null ? 0 : this.getTriggertime()
						.hashCode());
		result = 37 * result
				+ (getIsok() == null ? 0 : this.getIsok().hashCode());
		result = 37
				* result
				+ (getOktimestamp() == null ? 0 : this.getOktimestamp()
						.hashCode());
		result = 37 * result
				+ (getOkmemo() == null ? 0 : this.getOkmemo().hashCode());
		return result;
	}

}