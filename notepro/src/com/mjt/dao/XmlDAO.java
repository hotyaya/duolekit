package com.mjt.dao;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.dom4j.Document;
import com.mjt.entity.Category;

public interface XmlDAO {
	public Document getDocument(String filepath);
	public void createXMLFile(String filepath);
	public List<Category> listCategoryByParentId(String pid,Document document);
	public String createCategory(String pid,String name,Document document,String filepath);
	public boolean deleteCategory(String id,Document document,String filepath);
	public boolean modifyCategory(String id, String newname,Document document,String filepath);
}
