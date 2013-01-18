package cn.railsoft.schedule.dao.entity;

/**
 * Vjobitemmaxseq entity. @author MyEclipse Persistence Tools
 */

public class Vjobitemmaxseq implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8021730591801946113L;
	private Long maxseq;

	// Constructors

	/** default constructor */
	public Vjobitemmaxseq() {
	}

	/** full constructor */
	public Vjobitemmaxseq(Long maxseq) {
		this.maxseq = maxseq;
	}

	// Property accessors

	public Long getMaxseq() {
		return this.maxseq;
	}

	public void setMaxseq(Long maxseq) {
		this.maxseq = maxseq;
	}

}