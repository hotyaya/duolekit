package com.test;
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
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Test {
	
	public static void extradatafromtable(String txt){
		Parser pdata = new Parser();
		try {
			pdata.setEncoding("GBK");
			pdata.setInputHTML(txt);
			//System.out.println(txt);
			@SuppressWarnings({ "unused", "serial" })
			NodeList nl = pdata.parse(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableRow && ((TableRow) node).getColumns().length == 8){
						System.out.print("");
						System.out.print(((TableRow) node).getColumns()[0].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[1].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[2].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[3].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[4].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[5].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[6].getStringText()+";");
						System.out.print(((TableRow) node).getColumns()[7].getStringText()+";");
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(get, responseHandler);
			Parser pdata = new Parser();
			pdata.setEncoding("GBK");
			pdata.setInputHTML(responseBody);
			//查找数据内容表格；
			NodeList nl = pdata.parse(new NodeFilter() {
				private static final long serialVersionUID = -809081498491606996L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableTag && ((TableTag)node).getAttribute("bordercolor")!=null &&((TableTag)node).getAttribute("bordercolor").equals("#CCCCCC")){
						//System.out.println(node.toHtml());
						extradatafromtable(node.toHtml());
						return true;
					}
					return false;
				}
			});
			System.out.println(nl.size()+"");
			//查找下一页；
			Parser parser = new Parser();
			parser.setInputHTML(responseBody);
			NodeList nodelist = parser.parse(new NodeFilter() {
				private static final long serialVersionUID = -2885915158327618710L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof LinkTag && (((LinkTag) node).getLinkText().equals("下一页"))){
						return true;
					}
					return false;
				}
			});
			System.out.println(nodelist.size()+"");
			//System.out.println(((LinkTag)nodelist.elementAt(0)).getLink());
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
