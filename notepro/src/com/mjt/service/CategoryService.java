package com.mjt.service;

import java.util.List;

import org.dom4j.Document;

import com.mjt.entity.Category;
import com.mjt.entity.Note;

public interface CategoryService {
	public void setFilePath(String path);
	public void setDocument();
	
	public void createXMLFile(String filepath);
	public List<Category> listCategoryByParentId(String pid);
	public String createCategory(String pid,String name);
	public boolean deleteCategory(String id);
	public boolean modifyCategory(String id, String newname);
	public List<Note> listNoteByParentId(String id,int userid);
	
}
