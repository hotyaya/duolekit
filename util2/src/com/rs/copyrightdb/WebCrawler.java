package com.rs.copyrightdb;

import java.io.IOException;
import java.util.Date;

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

import cn.rs.cr.dao.Softitem;
import cn.rs.cr.dao.SoftitemDAO;

public class WebCrawler {
	
	public WebCrawler(SoftitemDAO dao) {
		super();
		this.dao = dao;
	}

	private SoftitemDAO dao = null;
	private int ifindRepeatRecord = 0;
	private int icount = 0;
	private int ipagecount = 0;
	public void doCrawler(){
		HttpClient httpclient = null;
		icount = 0;
		ipagecount = 0;
		try {
			httpclient = new DefaultHttpClient();
			//String sitename = "http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck";//124.193.201.195
			String sitename = "http://124.193.201.195";//
			String str1 = "/cpcc/RRegisterAction.do?method=list&no=fck";
			//中断的页面2012/06/23
			str1 = "/cpcc/RRegisterAction.do?method=list&no=fck&sql_name=&sql_regnum=&sql_author=&sortLabel=registerDate&sortOrder=&count=10&curPage=4025";
			String url = sitename + str1;
			do {
				ifindRepeatRecord = 0;	//已发现的重复的记录
				str1 = forwardLink(httpclient, url);
				url = sitename + str1;
				ipagecount ++ ;
				/**
				 * 初始化时不应该启动此项
				 */
//				if (ifindRepeatRecord>=5){	
//					System.out.println("发现重复记录组！");
//					break;
//				}
				System.out.println("记录当前页码"+ipagecount);
				System.out.println("记录当前记录"+icount);
				Thread.sleep(50);
			} while (str1.length() > 0);
			System.out.println("记录当前页码"+ipagecount);
			System.out.println("记录当前记录"+icount);
			httpclient.getConnectionManager().shutdown();
		} catch (Exception e) {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			System.out.println(" "+new Date(System.currentTimeMillis()));
			System.out.println("记录当前页码"+ipagecount);
			System.out.println("记录当前记录"+icount);
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
			e.printStackTrace();
			httpclient.getConnectionManager().shutdown();
		}

	}
	

	private String forwardLink(HttpClient httpclient, String url) {
		String str1 = "";
		try {
			//HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(url);// http://localhost:8080/cms
			get.addHeader("Accept","text/html, application/xhtml+xml, */*");
			get.addHeader("Accept-Language","zh-CN,en-US;q=0.5");
			get.addHeader("User-Agent","Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)");
			get.addHeader("Accept-Encoding","gzip, deflate");
			get.addHeader("Host","ocalhost:9999");
			get.addHeader("Connection","Keep-Alive");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(get, responseHandler);
			Parser pdata = new Parser();
			pdata.setEncoding("GBK");
			pdata.setInputHTML(responseBody);
			// 查找数据内容表格；
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
			// 查找下一页；
			Parser parser = new Parser();
			parser.setInputHTML(responseBody);
			NodeList nodelist = parser.parse(new NodeFilter() {
				private static final long serialVersionUID = -2885915158327618710L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof LinkTag&& (((LinkTag) node).getLinkText().equals("下一页"))) {
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
						
						icount ++;//统计记录数
						Softitem item = new Softitem();
						System.out.print("");
						String registerid=((TableRow) node).getColumns()[0].getStringText().trim();
						item.setRegisterid(registerid);
						System.out.print(" Registerid:"+((TableRow) node).getColumns()[0].getStringText() + ";");
						String typecode=((TableRow) node).getColumns()[1].getStringText().trim();
						item.setTypecode(typecode);
						System.out.print(" Typecode:"+((TableRow) node).getColumns()[1].getStringText() + ";");
						String softname=((TableRow) node).getColumns()[2].getStringText().trim();
						item.setSoftname(softname);
						System.out.print(" Softname:"+((TableRow) node).getColumns()[2].getStringText() + ";");
						String softbrief=((TableRow) node).getColumns()[3].getStringText().trim();
						item.setSoftbrief(softbrief);
						System.out.print(" Softbrief:"+((TableRow) node).getColumns()[3].getStringText() + ";");
						String version=((TableRow) node).getColumns()[4].getStringText().trim();
						item.setVersion(version);
						System.out.print(" Version:"+((TableRow) node).getColumns()[4].getStringText() + ";");
						String author=((TableRow) node).getColumns()[5].getStringText().trim();
						item.setAuthor(author);
						System.out.print(" Author:"+((TableRow) node).getColumns()[5].getStringText() + ";");
						String publishdate=((TableRow) node).getColumns()[6].getStringText().trim();
						item.setPublishdate(publishdate);
						System.out.print(" Publishdate:"+((TableRow) node).getColumns()[6].getStringText() + ";");
						String registerdate=((TableRow) node).getColumns()[7].getStringText().trim();
						item.setRegisterdate(registerdate);
						System.out.print(" Registerdate:"+((TableRow) node).getColumns()[7].getStringText() + ";");
						System.out.print("\n");
						if ((dao!=null) && (dao.findById(registerid)==null)){
							dao.save(item);
						}else{
							System.out.println("重复："+registerid);
						}
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
