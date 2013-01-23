package cn.railsoft.jobhelper;

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
import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.Header;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
//import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

public class TelegramCrawler {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultHttpClient httpclient = new DefaultHttpClient();// 得到httpclient实例
		String url = "http://10.64.3.46:8080/checkuser.asp";// ?action=checkuser.asp
//		HttpGet httpGet = new HttpGet(url);
		HttpResponse response;
		String username = "ljxxczhk";
		String password = "a";

		/* 创建post连接 */
		HttpPost httpPost = new HttpPost(url);

		/* 创建登录条件 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("UserName", username));
		nvps.add(new BasicNameValuePair("PassWord", password));
		// nvps.add(new BasicNameValuePair("dest", "index.asp"));
		// 默认跳转？

		//跳转方法1
		// httpclient.setRedirectHandler(new DefaultRedirectHandler());

		//跳转方法2
		//httpclient.setRedirectStrategy(new LaxRedirectStrategy());

		//跳转方法3
		// httpclient.setRedirectStrategy(new DefaultRedirectStrategy() {
		// public boolean isRedirected(HttpRequest request, HttpResponse
		// response, HttpContext context) {
		// boolean isRedirect=false;
		// try {
		// isRedirect = super.isRedirected(request, response, context);
		// } catch (ProtocolException e) {
		// e.printStackTrace();
		// }
		// if (!isRedirect) {
		// int responseCode = response.getStatusLine().getStatusCode();
		// if (responseCode == 301 || responseCode == 302) {
		// return true;
		// }
		// }
		// return isRedirect;
		// }
		// });

		//跳转方法4
		httpclient.setRedirectStrategy(new DefaultRedirectStrategy() {
			@Override
			public HttpUriRequest getRedirect(HttpRequest request,
					HttpResponse response, HttpContext context)
					throws ProtocolException {
				// TODO Auto-generated method stub
				response.setHeader("Location", "tele/cxjg.asp");
				//System.out.println("request:"+request.toString());
				//System.out.println("response:"+response.toString());
				//System.out.println("context:"+context.toString());
				return super.getRedirect(request, response, context);
			}

			@Override
			public URI getLocationURI(HttpRequest arg0, HttpResponse arg1,
					HttpContext arg2) throws ProtocolException {
				// TODO Auto-generated method stub
				//System.out.println("arg2:"+arg2.toString());
				return super.getLocationURI(arg0, arg1, arg2);
			}

			public boolean isRedirected(HttpRequest request,
					HttpResponse response, HttpContext context) {
				boolean isRedirect = false;
				try {
					isRedirect = super.isRedirected(request, response, context);
				} catch (ProtocolException e) {
					e.printStackTrace();
				}
				if (!isRedirect) {
					int responseCode = response.getStatusLine().getStatusCode();
					if (responseCode == 301 || responseCode == 302) {
						return true;
					}
				}
				return isRedirect;
			}
		});

		/* 添加到httpPost提交的内容中 */
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			/* 执行并打印登录后内容显示情况 */
			// httpclient.setRedirectStrategy();
			response = httpclient.execute(httpPost);// 不打印登录情况
			System.out.println(response.getStatusLine().toString());
			// 检查是否重定向
			// int statuscode = response.getStatusLine().getStatusCode();
			// if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) ||
			// (statuscode == HttpStatus.SC_MOVED_PERMANENTLY) ||
			// (statuscode == HttpStatus.SC_SEE_OTHER) ||
			// (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
			// //读取新的URL地址
			// Header header = response.getFirstHeader("location");
			// System.out.println(""+header.toString());
			// if (header != null) {
			// String newuri = header.getValue();
			// if ((newuri == null) || (newuri.equals(""))) newuri = "/";
			//
			// // GetMethod redirect = new GetMethod(newuri);
			// // httpclient.executeMethod(redirect);
			// // redirect.releaseConnection();
			//
			// } else{
			// System.out.println("Invalid redirect");
			// }
			// }
			/* 判断登录是否成功 */
			HttpEntity entity = response.getEntity();
			// StringBuffer sb = null;
			StringBuffer sb = null;
			// 输出页面内容
			if (entity != null) {
				InputStream is = entity.getContent();
				sb = new StringBuffer();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "gb2312"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\t\n");
					System.out.println(line);
				}
				is.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
