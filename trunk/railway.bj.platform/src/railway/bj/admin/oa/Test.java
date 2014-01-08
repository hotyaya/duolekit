package railway.bj.admin.oa;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
//import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicSchemeFactory;
import org.apache.http.impl.auth.DigestSchemeFactory;
import org.apache.http.impl.auth.KerberosSchemeFactory;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import tech.lib.ntlm.JCIFSNTLMSchemeFactory;

public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Registry<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder.<AuthSchemeProvider>create()
		        .register(AuthSchemes.NTLM, new JCIFSNTLMSchemeFactory())
		        .register(AuthSchemes.BASIC, new BasicSchemeFactory())
		        .register(AuthSchemes.DIGEST, new DigestSchemeFactory())
		        .register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory())
		        .register(AuthSchemes.KERBEROS, new KerberosSchemeFactory())
		        .build();
		CloseableHttpClient httpclient = HttpClients.custom()
		        .setDefaultAuthSchemeRegistry(authSchemeRegistry)
		        .build();
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY,
		        new NTCredentials("zhanglan", "zl", "HUI-PC", "10.64.3.55"));
		HttpHost target = new HttpHost("10.64.3.55", 80, "http");
		// Make sure the same context is used to execute logically related requests
		HttpClientContext context = HttpClientContext.create();
		context.setCredentialsProvider(credsProvider);

		// Execute a cheap method first. This will trigger NTLM authentication
		HttpGet httpget = new HttpGet("/gwcl/oagwcl.asp");
		CloseableHttpResponse response1,response2;
		try {
			response1 = httpclient.execute(target, httpget, context);
			try {
				HttpEntity entity1 = response1.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity1.getContent(), "gb2312"));  
				String line = null;  
				while ((line = reader.readLine()) != null) {  
					System.out.println(line);//登录后  
				}  
				reader.close();
			} finally {
				response1.close();
			}

			httpget = new HttpGet("http://10.64.3.55/gwcl/shw_query.asp?flag=BMSW&zhbflag=0");
			response2 = httpclient.execute(target, httpget, context);
			try {
				HttpEntity entity1 = response2.getEntity();
				BufferedReader reader = new BufferedReader(new InputStreamReader(entity1.getContent(), "gb2312"));  
//				String line = null;  
//				while ((line = reader.readLine()) != null) {  
//					System.out.println(line);  //查询后
//				}  
				System.out.print(reader.toString());
				reader.close();
			} finally {
				response2.close();
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
