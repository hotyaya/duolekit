package cn.railsoft.wtk.dbdemo.server;

import org.hibernate.Session;

import cn.railsoft.wtk.dao.HibernateSessionFactory;
import cn.railsoft.wtk.dao.entity.Dept;
import cn.railsoft.wtk.dao.entity.DeptDAO;
import cn.railsoft.wtk.dbdemo.client.GreetingService;
import cn.railsoft.wtk.dbdemo.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 2 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
		String temp = "";
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			new DeptDAO().save(new Dept("0", input, "测试用"));
			session.getTransaction().commit();
			temp = "成功！";
		}catch(Exception ex){
			temp = "失败！"+ex.getMessage().toString();
		}

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + temp +"<br>" +userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
