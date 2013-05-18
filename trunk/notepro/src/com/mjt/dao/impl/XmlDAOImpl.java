package com.mjt.dao.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mjt.dao.XmlDAO;
import com.mjt.entity.Category;
import com.mjt.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class XmlDAOImpl implements XmlDAO {	
	

	
	public Document getDocument(String filepath){	
		SAXReader reader=new SAXReader();
		//String str = "D:/"+xmluser+"-category.xml";
		File file=new File(filepath);
		Document documenttemp=null;
		try {
			documenttemp = reader.read(file);
		} catch (DocumentException e) {
		     e.printStackTrace();
		}
		return documenttemp;
	}	
	
	private static void tranElement(Element element, Category category) {
		category.setId(element.attributeValue("id"));
		category.setName(element.elementText("name"));
		category.setParentid(element.elementText("parentid"));	
	}
	
	//为每个用户创建不同的XML文件
	@SuppressWarnings("deprecation")
	public void createXMLFile(String filepath)  {
		/** 建立document对象 */
		Document document = DocumentHelper.createDocument();		    
		Element e = document.addElement("Categories");
		e.setAttributeValue("count","0");	    
		/** 将document中的内容写入文件中 */
		try {
			XMLWriter writer = new XMLWriter(new FileWriter(new File(filepath)));
			writer.write(document);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	//将改动后的document对象写回XML
	public void writeXMLFile(String filepath,Document document) {
		Writer writer = null;
	    XMLWriter xmlWriter = null;   
	    try {
			writer = new OutputStreamWriter(new FileOutputStream(filepath),"utf-8");			
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");
			xmlWriter = new XMLWriter(writer,format);
			xmlWriter.write(document);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public List<Category> listCategoryByParentId(String pid,Document document) {
		List<Category> categorylist= new ArrayList<Category>();
	    Element rootElement = document.getRootElement();
	    List<Node> list = document.selectNodes("/Categories/Category[parentid='"+pid+"']");
	    if (list != null && list.size() > 0) {
	    	 for (int i = 0; i < list.size(); i++) {
	    	     Node node = list.get(i);
	    	     if (node != null) {
	    	         Element element = (Element) node;
	    	         Category category = new Category();
	    	         tranElement(element, category);
	    	         categorylist.add(category);
	    	      }
	    	  }
	    }

	    return categorylist;
	}
	
	public String createCategory(String pid,String name,Document document,String filepath){
		// TODO Auto-generated method stub
	    Element rootElement = document.getRootElement();
	    int id = Integer.parseInt(rootElement.attributeValue("count"))+1;
	    rootElement.setAttributeValue("count",Integer.toString(id));
	    
	    Element Element = rootElement.addElement("Category");
	    Element.addAttribute("id", "p"+Integer.toString(id));
	    Element nodepid = Element.addElement("parentid");
	    Element nodename = Element.addElement("name");
	    nodepid.addText(pid);
	    nodename.addText(name);
	    this.writeXMLFile(filepath,document);
		return "p"+Integer.toString(id);
	}
	
	
	public boolean deleteCategory(String id,Document document,String filepath) {
		// TODO Auto-generated method stub		
	    Element rootElement = document.getRootElement();
	    List<Node> list = document.selectNodes("/Categories/Category[@id='"+id+"']");
	    //System.out.println("/Categories/Category[parentid="+id+"]");
	     if (list != null && list.size() > 0) {
	    	 
	    	 Element element = (Element) list.get(0);
	    	 rootElement.remove(element);
	    	 this.writeXMLFile(filepath,document);
	    	 return true;
	     }
	     return false;
	}
	public boolean modifyCategory(String id, String newname,Document document,String filepath) {
		// TODO Auto-generated method stub
	    Element rootElement = document.getRootElement();
	    List<Node> list = document.selectNodes("/Categories/Category[@id='"+id+"']");	   
	    if (list != null && list.size() > 0) {	    	 
	    	 Element element = (Element) list.get(0);
	    	 Element e1 = element.element("name");
	    	 e1.setText(newname);
	    	 this.writeXMLFile(filepath,document);
	    	 return true;
	     }
	     return false;
	}

}
