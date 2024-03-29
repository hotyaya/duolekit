package railway.bj.admin.oa;

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
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.TableRow;
import org.htmlparser.util.ParserException;

import railway.bj.admin.my.job.dao.entity.Doccatalog;
import tech.lib.ntlm.JCIFSNTLMSchemeFactory;

public class OACrawler {
	Vector<Doccatalog> doccatalog = null;

	public OACrawler(Vector<Doccatalog> doccatalog){
		this.doccatalog = doccatalog;
	}
	
	public static void main(String[] args) {
		new OACrawler(new Vector<Doccatalog>()).docrawler();
	}

	public void docrawler() {
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
		StringBuffer sb;
		sb = new StringBuffer();
		// Execute a cheap method first. This will trigger NTLM authentication
		try {
			HttpGet httpget = new HttpGet("/gwcl/oagwcl.asp");
			CloseableHttpResponse response1;
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
			parse(sb.toString());
		} catch (ClientProtocolException e) {
			System.out.print("e2");
		} catch (IOException e) {
			System.out.print("e3");
		}
	}
	/**
    <tr bgcolor="#FFFFFF" class="content2">	
      <td>2014.01.26</td>	
      <td>北京铁路局</td>	
      <td><a href="shw_showchld.asp?numb=2014-11231&shwflag=BMSW">[局文72号]北京铁路局关于2014年刊《北京铁路局年鉴》编撰工作的通知</a></td>	
      <td>京铁办[2014]72号</td>	
      <td>信息化处</td>	
      <td>局文52号</td>	
      <td>待归档</td>	
    </tr>	
    */
	void parse(String body){
		Parser pdata = new Parser();
		try {
			pdata.setEncoding("gb2312");
			pdata.setInputHTML(body);
			pdata.parse(new NodeFilter() {
				private static final long serialVersionUID = -809081498491606996L;
				@Override
				public boolean accept(Node node) {
					if (node instanceof TableRow && 
							((TableRow) node).getColumnCount()==7 && 
							((TableRow) node).getAttribute("bgcolor").equals("#FFFFFF")) {
						extradatafromtable(node);
						return true;
					}
					return false;
				}
			});
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
	
	void extradatafromtable(Node node){
		//System.out.println(node.toHtml());
		Doccatalog item = new Doccatalog();
		//item.setDocid();
		item.setType("OA");
		JDateTime jdt = new JDateTime();
		jdt.parse(((TableRow) node).getColumns()[0].toPlainTextString().trim(),"YYYY.MM.DD");
		item.setDocsenddate(Integer.parseInt(jdt.toString("YYYYMMDD")));
		item.setDocsendtime(((TableRow) node).getColumns()[0].toPlainTextString().trim());//20140108
		item.setDocsender(((TableRow) node).getColumns()[1].toPlainTextString().trim());
		String caption = ((TableRow) node).getColumns()[2].toPlainTextString().trim();
		item.setDoccaption(caption);
		item.setDoccode(((TableRow) node).getColumns()[3].toPlainTextString().trim());
//		item.setContact("");
//		item.setPhone("");
		if (((TableRow) node).getColumns()[2].getChild(0) instanceof LinkTag){
			String href = ((LinkTag)((TableRow) node).getColumns()[2].getChild(0)).getAttribute("href");
			if (href!=null) item.setUrl(""+ href);
		}
//		item.setBaseurl(""+url);
		item.setIndate(Integer.parseInt(new JDateTime(System.currentTimeMillis()).toString("YYYYMMDD")));
		item.setIntimestamp(new Timestamp(System.currentTimeMillis()));
		item.setIshidden(filter(caption));
		if (doccatalog!=null){
			doccatalog.add(item);
		}
		//System.out.println(item.toString());
	}
	
	boolean filter(String str){
		if (str.indexOf("防止事故奖励的通知")<0){
			return false;
		}
		return true;
	}
}
