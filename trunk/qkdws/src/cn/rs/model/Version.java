package cn.rs.model;

/**
 * Version entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Version implements java.io.Serializable {

	// Fields

	private Integer id;
	private String currentItem;
	private Integer currentVersion;
	private String publishDate;
	private String description;

	// Constructors

	/** default constructor */
	public Version() {
	}

	/** full constructor */
	public Version(String currentItem, Integer currentVersion,
			String publishDate, String description) {
		this.currentItem = currentItem;
		this.currentVersion = currentVersion;
		this.publishDate = publishDate;
		this.description = description;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrentItem() {
		return this.currentItem;
	}

	public void setCurrentItem(String currentItem) {
		this.currentItem = currentItem;
	}

	public Integer getCurrentVersion() {
		return this.currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}