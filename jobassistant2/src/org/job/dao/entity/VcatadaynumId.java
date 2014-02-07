package org.job.dao.entity;

/**
 * VcatadaynumId entity. @author MyEclipse Persistence Tools
 */

public class VcatadaynumId implements java.io.Serializable {

	// Fields

	private Integer docsenddate;
	private Integer count0;

	// Constructors

	/** default constructor */
	public VcatadaynumId() {
	}

	/** full constructor */
	public VcatadaynumId(Integer docsenddate, Integer count0) {
		this.docsenddate = docsenddate;
		this.count0 = count0;
	}

	// Property accessors

	public Integer getDocsenddate() {
		return this.docsenddate;
	}

	public void setDocsenddate(Integer docsenddate) {
		this.docsenddate = docsenddate;
	}

	public Integer getCount0() {
		return this.count0;
	}

	public void setCount0(Integer count0) {
		this.count0 = count0;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VcatadaynumId))
			return false;
		VcatadaynumId castOther = (VcatadaynumId) other;

		return ((this.getDocsenddate() == castOther.getDocsenddate()) || (this
				.getDocsenddate() != null && castOther.getDocsenddate() != null && this
				.getDocsenddate().equals(castOther.getDocsenddate())))
				&& ((this.getCount0() == castOther.getCount0()) || (this
						.getCount0() != null && castOther.getCount0() != null && this
						.getCount0().equals(castOther.getCount0())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getDocsenddate() == null ? 0 : this.getDocsenddate()
						.hashCode());
		result = 37 * result
				+ (getCount0() == null ? 0 : this.getCount0().hashCode());
		return result;
	}

}