package org.job.dao.entity;

/**
 * Vdocrecv entity. @author MyEclipse Persistence Tools
 */

public class Vdocrecv implements java.io.Serializable {

	// Fields

	private VdocrecvId id;

	// Constructors

	/** default constructor */
	public Vdocrecv() {
	}

	/** full constructor */
	public Vdocrecv(VdocrecvId id) {
		this.id = id;
	}

	// Property accessors

	public VdocrecvId getId() {
		return this.id;
	}

	public void setId(VdocrecvId id) {
		this.id = id;
	}

}