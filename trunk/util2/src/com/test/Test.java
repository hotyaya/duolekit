package com.test;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.NotFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Test {

	public static boolean getStringsByRegex(String txt) {
		String regex = "td class=\"no\"";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(txt);
		if (m.find()) {
			return true;
		}
		return false;

	}

	public static void test2() {
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// responseBody返回的是该链接的所有静态页面
			String responseBody = httpclient.execute(get, responseHandler);
			Parser parser = new Parser(responseBody);
			// NodeClassFilter ncf = new NodeClassFilter(TableRow.class);
			// HasAttributeFilter haf = new HasAttributeFilter("id", "Label1");
			// AndFilter af = new AndFilter(ncf, haf);
			// NodeList list = parser.parse(af);

			// parser.parse(new NodeFilter() {
			// @Override
			// public boolean accept(Node node) {
			// Node need = node;
			// if (getStringsByRegex(node.getText())) {
			// // for (int i = 0; i < 6; i++) {
			// // System.out.println(need.toPlainTextString());
			// // //result.add(need.toPlainTextString());
			// // need = need.getPreviousSibling().getPreviousSibling();
			// // }
			//
			// return true;
			// }
			// return false;
			// }
			// });

			// NodeClassFilter ncf = new NodeClassFilter(Span.class);
			// HasAttributeFilter haf = new HasAttributeFilter("id", "Label1");
			// AndFilter af = new AndFilter(ncf, haf);
			// NodeList list = parser.parse(af);

			NodeFilter title_filter = new AndFilter(new TagNameFilter("a"),
					new NotFilter(new HasAttributeFilter("href", "../")));

			String filterStr = "a"; // 过滤 a 节点（其实就是链接节点<a>)
			NodeFilter filter = new TagNameFilter(filterStr);
			NodeList nodeList = parser.extractAllNodesThatMatch(title_filter);
			LinkTag tabletag = (LinkTag) nodeList.elementAt(0); // 获取第一个a节点对象
			System.out.println(">>>>> " + nodeList.size()); // 输出共有多少个a节点
			System.out.println("#### \r" + tabletag.toString());

			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	
//	public static boolean getStringsByRegex(String txt) {
//		String regex = "td class=\"no\"";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(txt);
//		if (m.find()) {
//			return true;
//		}
//		return false;
//
//	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// responseBody返回的是该链接的所有静态页面
			String responseBody = httpclient.execute(get, responseHandler);

			
			Parser pdata = new Parser();
			pdata.setInputHTML(responseBody);
			//System.out.println(responseBody);
			NodeList nl = pdata.parse(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableTag && ((TableTag)node).getAttribute("bordercolor")!=null &&((TableTag)node).getAttribute("bordercolor").equals("#CCCCCC")){
						System.out.println(node.toHtml());
						
						return true;
					}
					return false;
				}
			});
			
			Parser parser = new Parser();
			parser.setInputHTML(responseBody);
			// NodeList nodelist = parser.parse(new
			// NodeClassFilter(LinkTag.class));
			NodeList nodelist = parser.parse(new NodeFilter() {
				@Override
				public boolean accept(Node node) {
					if (node instanceof LinkTag && (((LinkTag) node).getLinkText().equals("下一页"))){
						//System.out.println(((LinkTag) node).getLinkText());
						return true;
					}
					return false;
				}
			});
			
			System.out.println(((LinkTag)nodelist.elementAt(0)).getLink());

			// // 获得服务器响应的的所有信息
			// HttpResponse response = httpclient.execute(get);
			//
			//
			// // 获得服务器响应回来的消息体（不包括HTTP HEAD）
			// HttpEntity entity = response.getEntity();
			//
			// //InputStream is0 = entity.getContent();
			// StringBuffer sb = new StringBuffer();//strbuf.append(str+"\n");
			//
			// if (entity != null) {
			// InputStream is = entity.getContent();
			// // 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
			// BufferedReader br = new BufferedReader(new InputStreamReader(
			// is, "GBK"));//UTF-8
			// String line = null;
			// while ((line = br.readLine()) != null) {
			// //System.out.println(line);
			// sb.append(line+"\n");
			// }
			// is.close();
			// }
			//
			// try {
			// extractText(sb.toString());
			// } catch (Exception e) {
			// e.printStackTrace();
			// }

			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
			// test2();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	public static String extractText(String inputHtml) throws Exception {

		// System.out.println(""+inputHtml);
		StringBuffer text = new StringBuffer();

		Parser parser = Parser.createParser(new String(inputHtml.getBytes(),
				"GBK"), "GBK");
		// 遍历所有的节点
		NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				// if (node.getText().trim().equals("下一步")){
				// System.out.println("找到！===>");
				// return true;
				// }
				// return false;
				return true;
			}
		});
		Node node = nodes.elementAt(0);
		text.append(node.toPlainTextString());
		return text.toString();
	}

	public static void test5(String resource) throws Exception {
		URL url = new URL(resource);
		URLConnection conn = url.openConnection();
		Parser myParser = new Parser(conn);

		// 设置编码
		myParser.setEncoding("GBK");
		String filterStr = "a"; // 过滤 a 节点（其实就是链接节点<a>)
		NodeFilter filter = new TagNameFilter(filterStr);
		NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
		LinkTag tabletag = (LinkTag) nodeList.elementAt(0); // 获取第一个a节点对象

		System.out.println(">>>>> " + nodeList.size()); // 输出共有多少个a节点
		System.out.println("#### \r" + tabletag.toString());

		// System.out.println(" ============== \r"+nodeList.toString());
		// //获取第一个a节点的内容

	}

}
