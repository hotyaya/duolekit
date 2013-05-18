package com.mjt.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.mjt.entity.User;
import com.mjt.service.CategoryService;
import com.mjt.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private Integer id;
	private String name;
	private String password;
	private String mailbox;
	private String qq;
	User user;
	UserService userservice;
	CategoryService categoryservice;
	
	public String login() throws IOException {
		//调用业务逻辑组件进行用户登录验证
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		User tempuser = userservice.userLogin(user.getName(), user.getPassword()); 
		if(tempuser != null)
		{
			//在session中保存当前用户实例
			ServletActionContext.getRequest().getSession().setAttribute("user",tempuser);			
			String path = ServletActionContext.getServletContext().getRealPath("/")+"user_category/"+user.getName()+"-category.xml";	
			categoryservice.setFilePath(path);
			categoryservice.setDocument();
			out.print("{success:true,msg:\'ok\'}");
			//response.sendRedirect("user/personal.jsp");
			return null;
		}else {
			 out.print("{success:true,msg:\'账号或密码错误,请重试!\'}");
			 out.flush();
		}	
		return null;
	}
	
	
	public String reg() throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		User u  = new User();
		u.setMailbox(mailbox);
		u.setName(name);
		u.setPassword(password);
		u.setQq(qq);
		userservice.saveUser(u);
		String str = ServletActionContext.getServletContext().getRealPath("/")+"user_category/"+name+"-category.xml";
		//categoryservice.setFilePath(str);
		categoryservice.createXMLFile(str);
		out.print("{success:true,msg:\'ok\'}");		
		return null;
	}
	
	public String logout() {
		ActionContext.getContext().getSession().clear();
		return null;
	}
	
	public String islogin() {
		if(ServletActionContext.getRequest().getSession().getAttribute("user") != null)
		{
			return SUCCESS;
		}else {
			return "login";
		}
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailbox() {
		return mailbox;
	}
	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public CategoryService getCategoryservice() {
		return categoryservice;
	}
	public void setCategoryservice(CategoryService categoryservice) {
		this.categoryservice = categoryservice;
	}
	public UserService getUserservice() {
		return userservice;
	}
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
