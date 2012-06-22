package com.rs.copyrightdb;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.cr.dao.Softitem;
import cn.rs.cr.dao.SoftitemDAO;

public class WebCrawler {
	
	public void doCrawler(){
		HttpClient httpclient = null;
		try {
			httpclient = new DefaultHttpClient();
			//String sitename = "http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck";//124.193.201.195
			String sitename = "http://124.193.201.195";//
			String str1 = "/cpcc/RRegisterAction.do?method=list&no=fck";
			String url = sitename + str1;
			do {
				str1 = forwardLink(httpclient, url);
				url = sitename + str1;
				Thread.sleep(5000);
			} while (str1.length() > 0);
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
			httpclient.getConnectionManager().shutdown();
		}

	}
	

	private String forwardLink(HttpClient httpclient, String url) {
		String str1 = "";
		try {
			//HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(url);// http://localhost:8080/cms
			get.addHeader("Accept","text/html, application/xhtml+xml, */*");
			get.addHeader("Accept-Language","zh-CN,en-US;q=0.5");
			get.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
			get.addHeader("Accept-Encoding","gzip, deflate");
			get.addHeader("Host","localhost:9999");
			get.addHeader("Connection","Keep-Alive");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(get, responseHandler);
			Parser pdata = new Parser();
			pdata.setEncoding("GBK");
			pdata.setInputHTML(responseBody);
			// �����������ݱ��
			NodeList nl = pdata.parse(new NodeFilter() {
				private static final long serialVersionUID = -809081498491606996L;

				@Override
				public boolean accept(Node node) {
					if (node instanceof TableTag
							&& ((TableTag) node).getAttribute("bordercolor") != null
							&& ((TableTag) node).getAttribute("bordercolor")
									.equals("#CCCCCC")) {
						// System.out.println(node.toHtml());
						extradatafromtable(node.toHtml());
						return true;
					}
					return false;
				}
			});
			System.out.println(nl.size() + "");
			// ������һҳ��
			Parser parser = new Parser();
			parser.setInputHTML(responseBody);
			NodeList nodelist = parser.parse(new NodeFilter() {
				private static final long serialVersionUID = -2885915158327618710L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof LinkTag&& (((LinkTag) node).getLinkText().equals("��һҳ"))) {
						return true;
					}
					return false;
				}
			});
			System.out.println(nodelist.size() + "");
			str1 = nodelist.size()>0?((LinkTag)nodelist.elementAt(0)).getLink():"";
		} catch (ClientProtocolException e) {
			str1 = "";
			e.printStackTrace();
		} catch (IOException e) {
			str1 = "";
			e.printStackTrace();
		} catch (ParserException e) {
			str1 = "";
			e.printStackTrace();
		}
		return str1;
	}	
	
	private void extradatafromtable(String txt) {
		Parser pdata = new Parser();
		try {
			pdata.setEncoding("GBK");
			pdata.setInputHTML(txt);
			// System.out.println(txt);
			@SuppressWarnings({ "unused", "serial" })
			NodeList nl = pdata.parse(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableRow
							&& ((TableRow) node).getColumns().length == 8) {
						System.out.print("");
						System.out.print(((TableRow) node).getColumns()[0]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[1]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[2]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[3]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[4]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[5]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[6]
								.getStringText() + ";");
						System.out.print(((TableRow) node).getColumns()[7]
								.getStringText() + ";");
						System.out.print("\n");
						return true;
					}
					return false;
				}
			});
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

}
