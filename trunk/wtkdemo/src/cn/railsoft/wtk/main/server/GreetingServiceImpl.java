package cn.railsoft.wtk.main.server;

import java.sql.Timestamp;
import java.util.ArrayList;

import cn.railsoft.wtk.main.client.GreetingService;
import cn.railsoft.wtk.main.shared.FieldVerifier;
import cn.railsoft.wtk.main.shared.MyO;

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
	
	@Override
	public MyO greetServer2(MyO myo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		MyO myo1= new MyO();
		myo1.setStr1("test1测试1"+myo.getStr1());
		myo1.setStr2("test2测试2"+myo.getStr2());
		myo1.setTs(new Timestamp(System.currentTimeMillis()));
		return myo1;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ArrayList greetServer3(MyO myo) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ArrayList al = new ArrayList();
		MyO myo1= new MyO();
		myo1.setStr1("test1测试1");
		myo1.setStr2("test2测试2");
		myo1.setTs(new Timestamp(System.currentTimeMillis()));
		al.add(0,myo);
		al.add(1,myo1);
		return al;
	}
}
