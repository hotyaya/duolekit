package cn.railsoft.schedule.app.server;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import cn.railsoft.schedule.app.client.GreetingService;
import cn.railsoft.schedule.app.shared.FieldVerifier;
import cn.railsoft.schedule.app.shared.Jobitem;	//用于传输的对象 
import cn.railsoft.schedule.dao.HibernateSessionFactory;
import cn.railsoft.schedule.dao.entity.JobitemDAO;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Jobitem> getJobItemList() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		ArrayList<Jobitem> jobitemlist = new ArrayList<Jobitem>();
		try{
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			JobitemDAO jd =new JobitemDAO();
			List<cn.railsoft.schedule.dao.entity.Jobitem> list = jd.findAll();
			for(cn.railsoft.schedule.dao.entity.Jobitem ji:list){
				Jobitem item = new Jobitem();
				item.setId(ji.getId());
				item.setCreateTime(ji.getCreateTime());
				item.setUserid(ji.getUserid());
				item.setSeq(ji.getSeq());
				item.setActionDate(ji.getActionDate());
				item.setType(ji.getType());
				item.setNumPrefix(ji.getNumPrefix());
				item.setNumNo(ji.getNumNo());
				item.setNumSuffix(ji.getNumSuffix());
				item.setContent1(ji.getContent1());
				item.setContent2(ji.getContent2());
				item.setContent3(ji.getContent3());
				item.setContent4(ji.getContent4());
				item.setContent5(ji.getContent5());
				item.setSource(ji.getSource());
				item.setSourceId(ji.getSourceId());
				item.setStatus(ji.getStatus());
				item.setStatusCreatetime(ji.getCreateTime());
				item.setMemo(ji.getMemo());
				jobitemlist.add(item);//2013/1/17
			}
			//jobitemlist.add(e);
			//jd.save(item);
			session.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList<Jobitem>(jobitemlist);
	}
	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
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
