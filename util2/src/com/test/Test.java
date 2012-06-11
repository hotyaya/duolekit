package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

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
			if (entity != null) {
				InputStream is = entity.getContent();
				// 将InputStream转换为Reader，并使用缓冲读取，提高效率，同时可以按行读取内容
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "GBK"));//UTF-8
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// 释放所有的链接资源，一般在所有的请求处理完成之后，才需要释放
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
