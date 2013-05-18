package com.mjt.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Note entity. @author MyEclipse Persistence Tools
 */

public class Note implements java.io.Serializable {
	// Fields
	private Integer id;
	private String categoryid;
	private User user;
	private String title;
	private String article;
	private Timestamp displaytime;
	private Set<Group> groups = new HashSet(0);
	
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArticle() {
		return this.article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public Timestamp getDisplaytime() {
		return this.displaytime;
	}

	public void setDisplaytime(Timestamp displaytime) {
		this.displaytime = displaytime;
	}
	
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
}