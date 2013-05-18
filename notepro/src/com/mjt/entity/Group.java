package com.mjt.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Group {
	private Integer id;
	private String name;
	private Date ctime; //����ʱ��
	private String description;
	private Integer count; //Ⱥ��������
	private Integer current;//��ǰ��������
	private String img;  
	private Set<User> users = new HashSet(0);   //Ⱥ���Ա
    private Set<Note> notes = new HashSet(0);    //Ⱥ���ڹ���ıʼ�
    
    
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
