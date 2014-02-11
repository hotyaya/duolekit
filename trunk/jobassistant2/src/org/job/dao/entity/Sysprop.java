package org.job.dao.entity;

/**
 * Sysprop entity. @author MyEclipse Persistence Tools
 */

public class Sysprop implements java.io.Serializable {

	// Fields

	private String k;
	private String v;

	// Constructors

	/** default constructor */
	public Sysprop() {
	}

	/** minimal constructor */
	public Sysprop(String k) {
		this.k = k;
	}

	/** full constructor */
	public Sysprop(String k, String v) {
		this.k = k;
		this.v = v;
	}

	// Property accessors

	public String getK() {
		return this.k;
	}

	public void setK(String k) {
		this.k = k;
	}

	public String getV() {
		return this.v;
	}

	public void setV(String v) {
		this.v = v;
	}

}