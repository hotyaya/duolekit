package com.mjt.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Group {
	private Integer id;
	private String name;
	private Date ctime; //创建时间
	private String description;
	private Integer count; //群组总人数
	private Integer current;//当前在线人数
	private String img;  
	private Set<User> users = new HashSet(0);   //群组成员
    private Set<Note> notes = new HashSet(0);    //群组内共享的笔记
    
    
    public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
    public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<Note> getNotes() {
		return notes;
	}
	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}   
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	
    
}
