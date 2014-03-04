package org.job.crawler.register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.Vector;

import jodd.datetime.JDateTime;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.ParserException;
import org.job.crawler.ntlm.JCIFSNTLMSchemeFactory;
import org.job.dao.entity.Doccatalog;


public class OACrawlerRegister implements IRegister{
	CloseableHttpClient httpclient = null;
	CookieStore cookieStore = new BasicCookieStore();
	boolean runok = false;

	public OACrawlerRegister(){
	}
	
	public static void main(String[] args) {
		new OACrawlerRegister().docrawler("zhanglan", "zl", "HUI-PC", "10.64.3.55");
	}

	public void docrawler(String user,String pass,String domain,String serverip) {
		Registry<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder.<AuthSchemeProvider>create()
		        .register(AuthSchemes.NTLM, new JCIFSNTLMSchemeFactory())
		        .register(AuthSchemes.BASIC, new BasicSchemeFactory())
		        .register(AuthSchemes.DIGEST, new DigestSchemeFactory())
		        .register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory())
		        .register(AuthSchemes.KERBEROS, new KerberosSchemeFactory())
		        .build();
		httpclient = HttpClients.custom()
		        .setDefaultAuthSchemeRegistry(authSchemeRegistry)
		        .setDefaultCookieStore(cookieStore).build();
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY,
		        new NTCredentials(user,pass,domain,serverip));
		HttpHost target = new HttpHost(serverip, 80, "http");
		// Make sure the same context is used to execute logically related requests
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);
		StringBuffer sb;
		sb = new StringBuffer();
		// Execute a cheap method first. This will trigger NTLM authentication
		try {
			HttpGet httpget = new HttpGet("/gwcl/oagwcl.asp");
			CloseableHttpResponse response1 = null;
			response1 = httpclient.execute(target, httpget, context);
			try {
//				HttpEntity entity1 = response1.getEntity();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(entity1.getContent(), "gb2312"));  
//				String line = null;  
//				while ((line = reader.readLine()) != null) {  
//					//System.out.println(line);//登录后  
//				}  
//				reader.close();
			} finally {
				response1.close();
			}

			httpget = new HttpGet("http://10.64.3.55/gwcl/shw_query.asp?flag=all&zhbflag=0");
			CloseableHttpResponse response2;
			response2 = httpclient.execute(target, httpget, context);
			try {
				HttpEntity entity1 = response2.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity1.getContent(), "gb2312"));  
				String line = null;  
				
				while ((line = reader.readLine()) != null) {  
					//System.out.println(line);  //查询后
					sb.append(line+ "\t\n");
				}  
				//System.out.print(sb.toString());
				reader.close();
			} finally {
				response2.close();
			}
			//parse(sb.toString());
			runok = true;
		} catch (ClientProtocolException e) {
			runok = false;
			System.out.print("e2");
		} catch (IOException e) {
			runok = false;
			System.out.print("e3");
		}
	}

	@Override
	public CookieStore getCookieStore() {
		return cookieStore;
	}

	@Override
	public boolean isRunok() {
		return runok;
	}

}
