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
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class Test {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet(
					"http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(get, responseHandler);
			Parser pdata = new Parser();
			pdata.setEncoding("GBK");
			pdata.setInputHTML(responseBody);
			//�����������ݱ��
			NodeList nl = pdata.parse(new NodeFilter() {
				private static final long serialVersionUID = -809081498491606996L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableTag && ((TableTag)node).getAttribute("bordercolor")!=null &&((TableTag)node).getAttribute("bordercolor").equals("#CCCCCC")){
						System.out.println(node.toHtml());
						return true;
					}
					return false;
				}
			});
			System.out.println(nl.size()+"");
			//������һҳ��
			Parser parser = new Parser();
			parser.setInputHTML(responseBody);
			NodeList nodelist = parser.parse(new NodeFilter() {
				private static final long serialVersionUID = -2885915158327618710L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof LinkTag && (((LinkTag) node).getLinkText().equals("��һҳ"))){
						return true;
					}
					return false;
				}
			});
			System.out.println(nodelist.size()+"");
			//System.out.println(((LinkTag)nodelist.elementAt(0)).getLink());
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
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
