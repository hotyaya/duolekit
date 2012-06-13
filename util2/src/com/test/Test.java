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
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// responseBody���ص��Ǹ����ӵ����о�̬ҳ��
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

			String filterStr = "a"; // ���� a �ڵ㣨��ʵ�������ӽڵ�<a>)
			NodeFilter filter = new TagNameFilter(filterStr);
			NodeList nodeList = parser.extractAllNodesThatMatch(title_filter);
			LinkTag tabletag = (LinkTag) nodeList.elementAt(0); // ��ȡ��һ��a�ڵ����
			System.out.println(">>>>> " + nodeList.size()); // ������ж��ٸ�a�ڵ�
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
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			// responseBody���ص��Ǹ����ӵ����о�̬ҳ��
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
					if (node instanceof LinkTag && (((LinkTag) node).getLinkText().equals("��һҳ"))){
						//System.out.println(((LinkTag) node).getLinkText());
						return true;
					}
					return false;
				}
			});
			
			System.out.println(((LinkTag)nodelist.elementAt(0)).getLink());

			// // ��÷�������Ӧ�ĵ�������Ϣ
			// HttpResponse response = httpclient.execute(get);
			//
			//
			// // ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			// HttpEntity entity = response.getEntity();
			//
			// //InputStream is0 = entity.getContent();
			// StringBuffer sb = new StringBuffer();//strbuf.append(str+"\n");
			//
			// if (entity != null) {
			// InputStream is = entity.getContent();
			// // ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
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

			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
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
		// �������еĽڵ�
		NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
			public boolean accept(Node node) {
				// if (node.getText().trim().equals("��һ��")){
				// System.out.println("�ҵ���===>");
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

		// ���ñ���
		myParser.setEncoding("GBK");
		String filterStr = "a"; // ���� a �ڵ㣨��ʵ�������ӽڵ�<a>)
		NodeFilter filter = new TagNameFilter(filterStr);
		NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
		LinkTag tabletag = (LinkTag) nodeList.elementAt(0); // ��ȡ��һ��a�ڵ����

		System.out.println(">>>>> " + nodeList.size()); // ������ж��ٸ�a�ڵ�
		System.out.println("#### \r" + tabletag.toString());

		// System.out.println(" ============== \r"+nodeList.toString());
		// //��ȡ��һ��a�ڵ������

	}

}
