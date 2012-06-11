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
			// HttpClient主要负责执行请求
			HttpClient httpclient = new DefaultHttpClient();
			// 利用HTTP GET向服务器发起请求
			HttpGet get = new HttpGet("http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms
			// 获得服务器响应的的所有信息
			HttpResponse response = httpclient.execute(get);
			// 获得服务器响应回来的消息体（不包括HTTP HEAD）
			HttpEntity entity = response.getEntity();
			
			//InputStream is0 = entity.getContent();
			StringBuffer sb = new StringBuffer();//strbuf.append(str+"\n"); 
			
			if (entity != null) {
				InputStream is = entity.getContent();
				// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
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
			
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
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
        // 遍历所有的节点
        NodeList nodes = parser.extractAllNodesThatMatch(new NodeFilter() {
            public boolean accept(Node node) {
//            	if (node.getText().trim().equals("下一步")){
//            		System.out.println("找到！===>");
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

        // 设置编码
        myParser.setEncoding("GBK");
        String filterStr = "a"; //过滤 a 节点（其实就是链接节点<a>)
        NodeFilter filter = new TagNameFilter(filterStr); 
        NodeList nodeList = myParser.extractAllNodesThatMatch(filter);
        LinkTag tabletag = (LinkTag) nodeList.elementAt(0); //获取第一个a节点对象

        System.out.println(">>>>> "+nodeList.size()); //输出共有多少个a节点
        System.out.println("#### \r"+tabletag.toString());

//        System.out.println(" ============== \r"+nodeList.toString()); //获取第一个a节点的内容

    }

}
