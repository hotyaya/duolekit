package com.mjt.service;

import com.mjt.entity.Note;

public interface NoteService {
	public boolean saveOrUpdate(Note note);
	public int saveReturnId(Note note);
	public Note loadNoteById(int id);
	public void queryByHql(String hql);
	public boolean deleteNoteById(int id);
	
}
