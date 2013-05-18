package com.mjt.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.mjt.entity.Group;
import com.mjt.entity.Note;
import com.mjt.entity.User;
import com.mjt.service.GroupService;
import com.mjt.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupAction extends ActionSupport {
	int id;
	String callback;
	UserService userservice;
	GroupService groupservice;
	
	public UserGroupAction() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public GroupService getGroupservice() {
		return groupservice;
	}
	public void setGroupservice(GroupService groupservice) {
		this.groupservice = groupservice;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}	
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	
	
	public User getUserFormSession() {
		ActionContext actionContext = ActionContext.getContext();         
		Map session = actionContext.getSession();
		User u =(User)session.get("user");
		return u;		
	}
	
	public String listgroup() {
		String info = "";
		List<Group> group = userservice.getGroups(getUserFormSession());
		info += callback+"({groups:[";
		int count = group.size();
		for(int i = 0;i < count;i++)
		{
			Group temp = group.get(i);
			info += "{img:'"+temp.getImg()+"',id:"+temp.getId()+",ctime:'"+temp.getCtime()+"',description:'"+temp.getDescription()+"',name:'"
			+temp.getName()+"',currentpeople:'"+temp.getCurrent()+"/"+temp.getCount()+"'}";
			if(i != count-1) {info +=",";}
			
		}
//		for(Group temp:group) {
//			info += "{img:'"+temp.getImg()+"',id:"+temp.getId()+",ctime:'"+temp.getCtime()+"',description:'"+temp.getDescription()+"',name:'"
//			+temp.getName()+"',currentpeople:'"+temp.getCurrent()+"/"+temp.getCount()+"'}";
//			
//		}
		info += "]})";
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String ingroup() {
		Map request = (Map) ActionContext.getContext().get("request");
        request.put("listnotes", groupservice.listNoteByGroupId(id));
        request.put("group", groupservice.findGroupById(id));
//        for(Note note:groupservice.listNoteByGroupId(id)) {
//        	System.out.println(note.getTitle());
//        	System.out.println(note.getUser().getName());
//        }
		return SUCCESS;
	}
	
	public String checknote() {
		Note note = groupservice.findNoteById(id);
		Map request = (Map) ActionContext.getContext().get("request");
		request.put("title", note.getTitle());			
	    request.put("article", note.getArticle());		   
        //System.out.println(groupservice.findNoteById(id).getTitle());
		return SUCCESS;
	}
	
	public String quitgroup() {
		if(groupservice.quitByGroupId(getUserFormSession().getId(), id)) {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write("{success:true}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
