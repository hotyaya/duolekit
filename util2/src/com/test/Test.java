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
			// HttpClient��Ҫ����ִ������
			HttpClient httpclient = new DefaultHttpClient();
			// ����HTTP GET���������������
			HttpGet get = new HttpGet("http://www.ccopyright.com.cn/cpcc/RRegisterAction.do?method=list&no=fck");// http://localhost:8080/cms
			// ��÷�������Ӧ�ĵ�������Ϣ
			HttpResponse response = httpclient.execute(get);
			// ��÷�������Ӧ��������Ϣ�壨������HTTP HEAD��
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream is = entity.getContent();
				// ��InputStreamת��ΪReader����ʹ�û����ȡ�����Ч�ʣ�ͬʱ���԰��ж�ȡ����
				BufferedReader br = new BufferedReader(new InputStreamReader(
						is, "GBK"));//UTF-8
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
				is.close();
			}
			// �ͷ����е�������Դ��һ�������е����������֮�󣬲���Ҫ�ͷ�
			httpclient.getConnectionManager().shutdown();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
