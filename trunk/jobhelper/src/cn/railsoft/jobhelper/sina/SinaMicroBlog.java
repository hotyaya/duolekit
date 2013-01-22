package cn.railsoft.jobhelper.sina;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class SinaMicroBlog {
	public static CountDownLatch latch = new CountDownLatch(1);
	private static SinaMicroBlog sSelf = new SinaMicroBlog();;
	private static Logger logger = LogManager.getLogger(SinaMicroBlog.class);
	private static DefaultHttpClient httpclient = new DefaultHttpClient();
	private static final String LOGIN_URL = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.11)";
	private static final String REQUEST_URL = "http://t.sina.com.cn/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack";
	public static SinaMicroBlog getInstance(String email, String password) {
		sSelf.isValid(email,password);
		synchronized (SinaMicroBlog.class) {
			try {
				//传入登陆Email
				HttpPost httpost = sSelf.perLoggin(email);
				//向客户端发送请求返回加密信息用于登陆密码加密
				SinaLoginMessage sinaLoginMessage = sSelf.getSinaLoginMessage(email,password,httpost);
				if (sinaLoginMessage != null) {	
					sSelf.login(sinaLoginMessage);
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
	    }
		return sSelf;
	}

	private void isValid(String email,String password) {
		// 判断用户名密码
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("用户名不能为空。");
		}
		if (password == null || password.equals("")) {
			throw new IllegalArgumentException("密码不能为空。");
		}
	}

	private void login(SinaLoginMessage sinaLoginMessage) throws Exception {
		List<NameValuePair> requestParams = createRequestParams(sinaLoginMessage);
		HttpPost httpost = new HttpPost(LOGIN_URL);
		httpost.setEntity(new UrlEncodedFormEntity(requestParams,HTTP.UTF_8));
		httpclient.execute(httpost);
		httpost.abort();
	}

	private SinaLoginMessage getSinaLoginMessage(String email,String password,HttpPost httpPost) throws Exception {
		HttpResponse response = httpclient.execute(httpPost);
		String nonceHtml = EntityUtils.toString(new BufferedHttpEntity(response.getEntity()), "UTF-8");
		if (nonceHtml != null && !nonceHtml.equals("")) {
			String nonce = nonceHtml.substring(nonceHtml.indexOf("(") + 1,nonceHtml.indexOf(")"));
			JSONObject jsonObj = (JSONObject)JSONValue.parse(nonce);
			String nonceCode = (String)jsonObj.get("nonce");
			String serverTime = jsonObj.get("servertime").toString();
			return new SinaLoginMessage(email,nonceCode,serverTime,password);
		}
		return null;
	}

	private List<NameValuePair> createRequestParams(SinaLoginMessage sinaLoginMessage) {
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("service","miniblog"));
		requestParams.add(new BasicNameValuePair("client","ssologin.js(v1.3.11)"));
		requestParams.add(new BasicNameValuePair("entry", "miniblog"));
		requestParams.add(new BasicNameValuePair("encoding", "utf-8"));
		requestParams.add(new BasicNameValuePair("gateway", "1"));
		requestParams.add(new BasicNameValuePair("savestate", "7"));
		requestParams.add(new BasicNameValuePair("from", ""));
		requestParams.add(new BasicNameValuePair("useticket", "0"));
		requestParams.add(new BasicNameValuePair("username",sinaLoginMessage.getEmail()));
		requestParams.add(new BasicNameValuePair("servertime",sinaLoginMessage.getServerTime()));
		requestParams.add(new BasicNameValuePair("nonce", sinaLoginMessage.getNonceCode()));
		requestParams.add(new BasicNameValuePair("pwencode", "wsse"));
		requestParams.add(new BasicNameValuePair("password",getEncryptPassword(sinaLoginMessage)));
		
		requestParams.add(new BasicNameValuePair("url",REQUEST_URL));
		requestParams.add(new BasicNameValuePair("returntype", "META"));
		return requestParams;
	}

	private String getEncryptPassword(SinaLoginMessage sinaLoginMessage) {
		return 	SHA1.hex_sha1(SHA1.hex_sha1(SHA1.hex_sha1(sinaLoginMessage.getPassword())) + sinaLoginMessage.getServerTime() + sinaLoginMessage.getNonceCode());
	}

	private HttpPost perLoggin(String email) {
		String emailURl = email.replaceAll("@", "%40");
		String nonceCodeUrl = "http://login.sina.com.cn/sso/prelogin.php?entry=miniblog&callback=sinaSSOController.preloginCallBack&user="
				+ emailURl + "&client=ssologin.js(v1.3.11)&_="+System.currentTimeMillis();
		return new HttpPost(nonceCodeUrl);
	}

	/*
	 * release SinaMicroBlog
	 * @param blogContent the letter of blog of content
	 */
	public void writeBlog(String blogContent){
		Double writeBlogRandom = Math.random();
		String writeBlogUrl = "http://t.sina.com.cn/mblog/publish.php?rnd="+writeBlogRandom;
		//post request of writeBlog
		HttpPost httpost = new HttpPost(writeBlogUrl);
		List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
		requestParams.add(new BasicNameValuePair("content", blogContent));
		requestParams.add(new BasicNameValuePair("pic", ""));
		requestParams.add(new BasicNameValuePair("styleid", "1"));
		requestParams.add(new BasicNameValuePair("retcode", ""));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(requestParams, HTTP.UTF_8)); // 参数编码
			httpost.setHeader("Referer","http://t.sina.com.cn/");
			httpclient.execute(httpost);
			httpost.abort();
		} catch (Exception e) {
			logger.info(e.getMessage());
		}		
	}
	public static void main(String[] args) {//TODO
		SinaMicroBlog s= SinaMicroBlog.getInstance("wang-----heng@163.com","56617480");
		s.writeBlog("kkkkkkkkkkkk");
	}
}
