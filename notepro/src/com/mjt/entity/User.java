package com.mjt.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {
	// Fields
	private Integer id;
	private String name;
	private String password;
	private String mailbox;
	private String qq;
	private Set notes = new HashSet(0);
	private Set groups = new HashSet(0);
	
	
	public Set getGroups() {
		return groups;
	}
	public void setGroups(Set groups) {
		this.groups = groups;
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailbox() {
		return this.mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getQq() {
		return this.qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Set getNotes() {
		return this.notes;
	}
	public void setNotes(Set notes) {
		this.notes = notes;
	}
}