package my.outline.dao.entity;

/**
 * Tag entity. @author MyEclipse Persistence Tools
 */

public class Tag implements java.io.Serializable {

	// Fields

	private Integer tagid;
	private Integer ptagid;
	private String name;

	// Constructors

	/** default constructor */
	public Tag() {
	}

	/** minimal constructor */
	public Tag(String name) {
		this.name = name;
	}

	/** full constructor */
	public Tag(Integer ptagid, String name) {
		this.ptagid = ptagid;
		this.name = name;
	}

	// Property accessors

	public Integer getTagid() {
		return this.tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public Integer getPtagid() {
		return this.ptagid;
	}

	public void setPtagid(Integer ptagid) {
		this.ptagid = ptagid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}