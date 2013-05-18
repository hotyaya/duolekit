package com.mjt.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Category enistence Toolstity. @author MyEclipse Pers
 */

public class Category implements java.io.Serializable {
	private String id;
	private String name;
	private String parentid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}