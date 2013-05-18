package com.mjt.service.impl;

import java.util.List;

import org.dom4j.Document;

import com.mjt.dao.BaseDAO;
import com.mjt.dao.XmlDAO;
import com.mjt.entity.Category;
import com.mjt.entity.Note;
import com.mjt.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
	private BaseDAO basedao;
	private XmlDAO xmldao;
	private  String filepath = null;
	private  Document document = null;

	public BaseDAO getBasedao() {
		return basedao;
	}
	public void setBasedao(BaseDAO basedao) {
		this.basedao = basedao;
	}
	
	public XmlDAO getXmldao() {
		return xmldao;
	}
	public void setXmldao(XmlDAO xmldao) {
		this.xmldao = xmldao;
	}

	
	
	public List<Note> listNoteByParentId(String id,int userid) {
		// TODO Auto-generated method stub		
		String hql = "from Note note where note.categoryid = '"+id+"'and note.user.id = "+userid;
		return (List<Note>)basedao.query(hql);
	}
	public void setFilePath(String path) {
		// TODO Auto-generated method stub
		this.filepath= path;
	}
	public void setDocument() {
		// TODO Auto-generated method stub
		this.document = xmldao.getDocument(filepath);
	}
	public void createXMLFile(String filepath) {
		xmldao.createXMLFile(filepath);
		
	}
	public List<Category> listCategoryByParentId(String pid) {
		// TODO Auto-generated method stub
		return xmldao.listCategoryByParentId(pid, document);
	}
	public String createCategory(String pid, String name) {
		// TODO Auto-generated method stub
		return xmldao.createCategory(pid, name, document, filepath);
	}
	public boolean deleteCategory(String id) {
		// TODO Auto-generated method stub
		return xmldao.deleteCategory(id, document, filepath);
	}
	public boolean modifyCategory(String id, String newname) {
		// TODO Auto-generated method stub
		return xmldao.modifyCategory(id, newname, document, filepath);
	}
	
}
