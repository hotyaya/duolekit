package org.job.crawler.register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

public class TGCrawlerRegister implements IRegister {
	CloseableHttpClient httpclient = null;
	CookieStore cookieStore = new BasicCookieStore();;
	boolean runok = false;
	
	public TGCrawlerRegister(){
	}
	
	public static void main(String[] args) {
		new TGCrawlerRegister().docrawler("liuhui","a","","");
	}

	/* (non-Javadoc)
	 * @see org.job.crawler.register.IRegister#docrawler(java.lang.String, java.lang.String)
	 */
	@Override
	public void docrawler(String user,String pass,String domain,String serverip) {
		httpclient = HttpClients.custom().setRedirectStrategy(new DefaultRedirectStrategy() {
					@Override
					public HttpUriRequest getRedirect(HttpRequest request,
							HttpResponse response, HttpContext context)
							throws ProtocolException {
						response.setHeader("Location", "tele/cxjg.asp");
						return super.getRedirect(request, response, context);
					}

					@Override
					public URI getLocationURI(HttpRequest arg0,
							HttpResponse arg1, HttpContext arg2)
							throws ProtocolException {
						return super.getLocationURI(arg0, arg1, arg2);
					}

					public boolean isRedirected(HttpRequest request,
							HttpResponse response, HttpContext context) {
						boolean isRedirect = false;
						try {
							isRedirect = super.isRedirected(request, response,
									context);
						} catch (ProtocolException e) {
							e.printStackTrace();
						}
						if (!isRedirect) {
							int responseCode = response.getStatusLine()
									.getStatusCode();
							if (responseCode == 301 || responseCode == 302) {
								return true;
							}
						}
						return isRedirect;
					}
				}).setDefaultCookieStore(cookieStore).build();
		
		String url = "http://10.64.3.46:8080/checkuser.asp";
		CloseableHttpResponse response = null;
		String username = "ljxxczhk";
		String password = "a";
		username = user;
		password = pass;
		
		/* 创建post连接 */
		HttpPost httpPost = new HttpPost(url);

		/* 创建登录条件 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("UserName", username));
		nvps.add(new BasicNameValuePair("PassWord", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			response = httpclient.execute(httpPost);// 不打印登录情况
//			//System.out.println(response.getStatusLine().toString());
			/* 判断登录是否成功 */
			HttpEntity entity = response.getEntity();
			// StringBuffer sb = null;
			StringBuffer sb = null;
			// 输出页面内容
			if (entity != null) {
				InputStream is = entity.getContent();
				sb = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "gb2312"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\t\n");
					// System.out.println(line);
				}
				is.close();
			}
			//System.out.print(sb.toString());
			//parse(sb.toString());
			runok = true;
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			System.out.print("x1");
			runok = false;
			//System.out.print("UnsupportedEncodingException ");
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			System.out.print("x2");
			runok = false;
			//System.out.print("ClientProtocolException ");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.print("x3");
			runok = false;
			//System.out.print("IOException ");
		}finally {//20140304
			try {
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.job.crawler.register.IRegister#getCookieStore()
	 */
	@Override
	public CookieStore getCookieStore() {
		return cookieStore;
	}

	/* (non-Javadoc)
	 * @see org.job.crawler.register.IRegister#isRunok()
	 */
	@Override
	public boolean isRunok() {
		return runok;
	}


}
