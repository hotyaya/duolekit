package com.mjt.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mjt.entity.Note;
import com.mjt.entity.User;
import com.mjt.service.CategoryService;
import com.mjt.service.NoteService;
import com.mjt.util.JsonFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class TreePanelAction extends ActionSupport {
	String id;  
	String title;
	String article;
	String categoryid;
	String name;
	//String flag;
	CategoryService categoryservice;
	NoteService noteservice;	
	
	public User getUserFormSession() {
		ActionContext actionContext = ActionContext.getContext();         
		Map session = actionContext.getSession();
		User u =(User)session.get("user");
		return u;		
	}

	public String ajax() {
		String path = ServletActionContext.getServletContext().getRealPath("/")+"user_category/"+this.getUserFormSession().getName()+"-category.xml";
		HttpServletResponse response = ServletActionContext.getResponse();
		categoryservice.setFilePath(path);
		categoryservice.setDocument();		
		JsonFormat jsonformat = new JsonFormat();					
		String str;
		str = jsonformat.format(categoryservice.listNoteByParentId(id,getUserFormSession().getId()), categoryservice.listCategoryByParentId(id)).toString();
		//System.out.println(str);
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return null;
	}
	
	public String newFile() {
		Note note = new Note();
		note.setTitle(title);
		note.setCategoryid(categoryid);
		note.setUser(this.getUserFormSession());
		Timestamp d = new Timestamp(System.currentTimeMillis());
		note.setDisplaytime(d);
		int rid = noteservice.saveReturnId(note);			
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("{success:true,id:\'"+rid+"\'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String editFile() {
		Note note = noteservice.loadNoteById(Integer.parseInt(id));
		Map request = (Map) ActionContext.getContext().get("request");
	    request.put("editid", Integer.parseInt(id));
	    request.put("article", note.getArticle());	
	    //System.out.println(note.getArticle().length());
	    return "edit";
	}
	
	public String newFloder() {
		String filename = this.getUserFormSession().getName();
		String rid = categoryservice.createCategory(id, name);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("{success:true,id:\'"+rid+"\'}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	public String viewFile() {
		Note note = noteservice.loadNoteById(Integer.parseInt(id));
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("title", note.getTitle());			
	    request.put("article", note.getArticle());		   
	    return "view";
	}
	public String delete() {
		if('p'==id.charAt(0)) {				
			categoryservice.deleteCategory(id);
		}else {
			noteservice.deleteNoteById(Integer.parseInt(id));
		}
		return null;
	}
	
	public String modify() {
		if('p'==id.charAt(0)) {
			categoryservice.modifyCategory(id, title);
		}else {
			String hql = "update Note note set note.title = '"+title+"' where note.id ="+id;
			noteservice.queryByHql(hql);
		}
		return null;
	}
	
	public String update() {
		String hql = "update Note note set note.article = '"+article+"' where note.id ="+id;
		//System.out.println(hql);
		noteservice.queryByHql(hql);
		return null;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public NoteService getNoteservice() {
		return noteservice;
	}
	public void setNoteservice(NoteService noteservice) {
		this.noteservice = noteservice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public CategoryService getCategoryservice() {
		return categoryservice;
	}
	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}
}
