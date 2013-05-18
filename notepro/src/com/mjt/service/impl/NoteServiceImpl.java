package com.mjt.service.impl;

import com.mjt.dao.BaseDAO;
import com.mjt.entity.Note;
import com.mjt.service.NoteService;

public class NoteServiceImpl implements NoteService {
	
	BaseDAO basedao;
	
	public BaseDAO getBasedao() {
		return basedao;
	}
	public void setBasedao(BaseDAO basedao) {
		this.basedao = basedao;
	}
	
	public boolean saveOrUpdate(Note note) {
		// TODO Auto-generated method stub
		try {
			basedao.saveOrUpdate(note);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public int saveReturnId(Note note) {
		return basedao.saveEntity(note);
		
	}
	public Note loadNoteById(int id) {
		// TODO Auto-generated method stub
		Note note = (Note)basedao.loadById(Note.class, id);
		return note;
	}
	public void queryByHql(String hql) {
		// TODO Auto-generated method stub
		basedao.update(hql);
	}
	public boolean deleteNoteById(int id) {
		// TODO Auto-generated method stub
		basedao.delById(Note.class, id);
		return true;
	}
	
}
