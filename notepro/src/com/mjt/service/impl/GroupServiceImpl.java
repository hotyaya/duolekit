package com.mjt.service.impl;

import java.util.List;

import com.mjt.dao.BaseDAO;
import com.mjt.entity.Group;
import com.mjt.entity.Note;
import com.mjt.service.GroupService;


public class GroupServiceImpl implements GroupService {
	BaseDAO basedao;
	public BaseDAO getBasedao() {
		return basedao;
	}
	public void setBasedao(BaseDAO basedao) {
		this.basedao = basedao;
	}
	
	public List<Note> listNoteByGroupId(int id) {
		// TODO Auto-generated method stub
		String hql = "select g.notes from Group g where g.id = "+id;
		return basedao.query(hql);
	}
	public Group findGroupById(int id) {
		// TODO Auto-generated method stub		
		return (Group)basedao.loadById(Group.class, id);
	}
	public Note findNoteById(int id) {
		// TODO Auto-generated method stub
		return (Note)basedao.loadById(Note.class, id);
	}
	public boolean deleteGroupById(int id) {
		// TODO Auto-generated method stub
		return true;
		
	}
	public boolean quitByGroupId(int userid,int id) {
		// TODO Auto-generated method stub
		String hql = "delete form  user_group where userid = "+userid+"and groupid = "+id;
		basedao.update(hql);
		return true;
	}
	
}
