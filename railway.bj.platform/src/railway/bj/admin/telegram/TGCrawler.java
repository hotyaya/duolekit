package railway.bj.admin.telegram;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import jodd.datetime.JDateTime;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.ParserException;

import railway.bj.admin.my.job.dao.entity.Doccatalog;

public class TGCrawler {
	Vector<Doccatalog> doccatalog = null;

	public TGCrawler(Vector<Doccatalog> doccatalog){
		this.doccatalog = doccatalog;
	}
	
	public static void main(String[] args) {
		new TGCrawler(new Vector<Doccatalog>()).docrawler();
	}

	public void docrawler() {
		CloseableHttpClient httpclient = HttpClients.custom()
				.setRedirectStrategy(new DefaultRedirectStrategy() {
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
				}).build();

		String url = "http://10.64.3.46:8080/checkuser.asp";
		HttpResponse response;
		String username = "ljxxczhk";
		String password = "a";

		/* 创建post连接 */
		HttpPost httpPost = new HttpPost(url);

		/* 创建登录条件 */
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("UserName", username));
		nvps.add(new BasicNameValuePair("PassWord", password));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
			response = httpclient.execute(httpPost);// 不打印登录情况
			//System.out.println(response.getStatusLine().toString());
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
					// System.out.println(line);
				}
				is.close();
			}
			//System.out.print(sb.toString());
			parse(sb.toString());
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
			System.out.print("x1");
			//System.out.print("UnsupportedEncodingException ");
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			System.out.print("x2");
			//System.out.print("ClientProtocolException ");
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.print("x3");
			//System.out.print("IOException ");
		}
	}
	/**
	 * 	<TR>	
			<TD bgcolor="f7f7f7">2014-01-06 11:26:33</TD>	
			<TD bgcolor="f7f7f7">局供电处</TD>	
			<TD bgcolor="f7f7f7">京供电[2014]3</TD>	
			<TD bgcolor="f7f7f7"><a href="javascript:winOpen('bFileInfoContent1.asp?F00=89267')" >关于召开劳动竞赛评先统计方法研讨会的通知</a></TD>	
		</TR>	
	 */
	void parse(String body){
		Parser pdata = new Parser();
		try {
			pdata.setEncoding("gb2312");
			pdata.setInputHTML(body);
			pdata.parse(new NodeFilter() {
				private static final long serialVersionUID = -809081498491606996L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableRow && 
							((TableRow) node).getColumnCount()==4 && 
							((TableRow) node).getColumns()[0].getAttribute("bgcolor").equals("f7f7f7")) {
						extradatafromtable(node);
						return true;
					}
					return false;
				}
			});
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	void extradatafromtable(Node node){
		//System.out.println(node.toHtml());
		Doccatalog item = new Doccatalog();
		//item.setDocid();
		item.setType("TG");
		JDateTime jdt = new JDateTime();
		jdt.parse(((TableRow) node).getColumns()[0].toPlainTextString().trim());
		item.setDocsenddate(Integer.parseInt(jdt.toString("YYYYMMDD")));
		item.setDocsendtime(((TableRow) node).getColumns()[0].toPlainTextString().trim());//20140108
		item.setDocsender(((TableRow) node).getColumns()[1].toPlainTextString().trim());
		String caption = ((TableRow) node).getColumns()[3].toPlainTextString().trim();
		item.setDoccaption(caption);
		item.setDoccode(((TableRow) node).getColumns()[2].toPlainTextString().trim());
		item.setContact("");
		item.setPhone("");
		item.setBaseurl("");
		item.setUrl("");
		item.setIndate(Integer.parseInt(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD")));
		item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
		item.setIshidden(filter(caption));
		if (doccatalog!=null){
			doccatalog.add(item);
		}
	}
	
	boolean filter(String str){
		if (str.indexOf("防止事故奖励的通知")<0){
			return false;
		}
		return true;
	}
}
