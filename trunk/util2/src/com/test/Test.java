package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			
			//InputStream is0 = entity.getContent();
			StringBuffer sb = new StringBuffer();//strbuf.append(str+"\n"); 
			
			if (entity != null) {
				InputStream is = entity.getContent();
				// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "GBK"));//UTF-8
				String line = null;
				while ((line = br.readLine()) != null) {
					//System.out.println(line);
					sb.append(line+"\n");
				}
				is.close();
			}
			
			try {
				extractText(sb.toString());
				//System.out.println();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
    public static String extractText(String inputHtml) throws Exception {
    	
		//System.out.println(""+inputHtml);
        StringBuffer text = new StringBuffer();

        Parser parser = Parser.createParser(new String(inputHtml.getBytes(),"GBK"), "GBK");
        // �������еĽڵ�
        NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
            public boolean accept(Node node) {
//            	if (node.getText().trim().equals("��һ��")){
//            		System.out.println("�ҵ���===>");
//            		return true;
//            	}
//            	return false;
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
        String filterStr = "a"; //���� a �ڵ㣨��ʵ�������ӽڵ�<a>)
        NodeFilter filter = new TagNameFilter(filterStr); 
        NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
        LinkTag tabletag = (LinkTag) nodeList.elementAt(0); //��ȡ��һ��a�ڵ����

        System.out.println(">>>>> "+nodeList.size()); //������ж��ٸ�a�ڵ�
        System.out.println("#### \r"+tabletag.toString());

//        System.out.println(" ============== \r"+nodeList.toString()); //��ȡ��һ��a�ڵ������

    }

}
