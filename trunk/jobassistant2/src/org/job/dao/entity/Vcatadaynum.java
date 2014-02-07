package org.job.dao.entity;

/**
 * Vcatadaynum entity. @author MyEclipse Persistence Tools
 */

public class Vcatadaynum implements java.io.Serializable {

	// Fields

	private VcatadaynumId id;

	// Constructors

	/** default constructor */
	public Vcatadaynum() {
	}

	/** full constructor */
	public Vcatadaynum(VcatadaynumId id) {
		this.id = id;
	}

	// Property accessors

	public VcatadaynumId getId() {
		return this.id;
	}

	public void setId(VcatadaynumId id) {
		this.id = id;
	}

}