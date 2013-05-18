package com.mjt.service;

import java.util.List;

import com.mjt.entity.Group;
import com.mjt.entity.Note;

public interface GroupService {
	public Group findGroupById(int id);
	public List<Note> listNoteByGroupId(int id);
	public Note findNoteById(int id);
	public boolean quitByGroupId(int userid,int id);
}
