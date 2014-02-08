package org.job.task;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpSslServer;
import jodd.util.MimeTypes;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.crawler.OACrawler;
import org.job.crawler.TGCrawler;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;
import org.job.dao.entity.Vcatadaynum;
import org.job.dao.entity.VcatadaynumDAO;
import org.job.interf.INotifyMessage;

import org.job.dao.HibernateUtil;

public class CrawlerProcess implements Runnable {
	Vector<Doccatalog> v =  new Vector<Doccatalog>();
	Vector<INotifyMessage> vnotify = new Vector<INotifyMessage>();
	
	public void addNotifyListener(INotifyMessage listener){
		vnotify.add(listener);
	}
	
	void notifyListener(String info){
		for (int i=0;i<vnotify.size();i++){
			((INotifyMessage)vnotify.elementAt(i)).notifyMessage(info);
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Timestamp(System.currentTimeMillis()).toString());
		new Thread(new CrawlerProcess()).start();
	}

	@Override
	public void run() {
		while(true){
			try{
				//读入配置文件!
				//电报爬虫!
				v.removeAllElements();//20140123
				new TGCrawler(v).docrawler();
				if (v.size() > 0) indb("TG");
				Thread.sleep(1000 * 2);
				
				v.removeAllElements();//20140127
				new OACrawler(v).docrawler();//加入办公文件的自动收集功能；
				if (v.size() > 0) indb("OA");
				Thread.sleep(1000 * 2);
				
				getCount();
				
				System.gc();
				Thread.sleep(1000 * 60 * 5); //5分钟 60 *
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * 从数据库中取得当日最新统计信息
	 */
	private void getCount(){
		try {
			Session session = HibernateUtil.currentSession();//HibernateSessionFactory.getSession();
			Transaction tran = session.beginTransaction();
			VcatadaynumDAO dao = new VcatadaynumDAO();
			List<Vcatadaynum> list = dao.findAll();
			String todaycount="";
			String temp ="";
			if (list!=null){ //20140207:24|20140205:2
				for (int i=0;i<5;i++){
					if (list.get(i)!=null){
						temp = list.get(i).getId().getDocsenddate() 
								+":"+list.get(i).getId().getCount0()+"";
						todaycount = todaycount +"|" + temp;
					}
				}
			}
			tran.commit();
			if (todaycount.length()>0){
				notifyListener("TDOAYCOUNT"+"|"+"AL"+"|"+todaycount.substring(1));//今日全部
			}
			Thread.sleep(50);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void indb(String ctag) {
		try {
			int newcount = 0;//记录下本次的新入库数量
			Session session = HibernateUtil.currentSession();//HibernateSessionFactory.getSession();
			Transaction tran = session.beginTransaction();
			DoccatalogDAO dao = new DoccatalogDAO();
			String code = "";
			String info = "";
			int repeatcount =0;//如果已经入库数达到3，退出循环。 (20140122)
			for (int i = 0; i < v.size(); i++) { //20140123 int i = v.size(); i > 1; i--
				code = v.elementAt(i).getDoccode(); //20140123 i-1=> i
				if (dao.findByDoccode(code.trim()).size() <= 0) {
					session.save(v.elementAt(i)); //20140123 i-1=> i //20140207 dao=> session
					newcount++;
					info = info + ";" + v.elementAt(i).getDoccaption() ;//20140123 i-1=> i
				} else {
					//System.out.print(code+"已经入库！");
					repeatcount++; 
					//System.out.print("_"+repeatcount+"_");
				}
				if (repeatcount>3) break; //便于提高效率(20140122)
			}
			if (repeatcount>3)System.out.print("3");//便于提高效率(20140122)
			tran.commit(); //session.getTransaction().commit();
//			session.disconnect();//20140122看看占不占资源；
//			session.close();//20140122看看占不占资源；
			Thread.sleep(50);//20140207
			if (newcount!=0) {
				String temp = "";
				if (ctag.equals("TG")){
					temp ="电报";
				}else if (ctag.equals("OA")){
					temp ="公文";
				}else{
					temp ="其它";
				}
				System.out.println("\n"+"insert " + newcount + "项" + temp + " recodes ok!" + new Timestamp(System.currentTimeMillis()).toString());
				notifyListener("NEWFILE"+"|"+ctag+"|"+newcount);
				mail(info);
			}else{
				System.out.print("+");
				//mail("-");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void mail(String text) {
		try{
			Email email = Email.create();
			EmailMessage textMessage = new EmailMessage(text, MimeTypes.MIME_TEXT_PLAIN);
			//email.subject("单位新文件提醒");
			email.addMessage(textMessage);
			//email.addText("收到电报如下：\n"+text);
			email.from("hotyaya@qq.com").to("hotyaya@126.com");
			email.subject("新文件 "+text);
			SendMailSession mailSession = new SmtpSslServer("smtp.qq.com","hotyaya@qq.com", "Bdesdk2759").createSession();
			mailSession.open();
			mailSession.sendMail(email);
			mailSession.close();
			if (text.equals("-")){
				System.out.print("-");
			}else{
				System.out.print("*发送mail成功!*");
			}
		}catch(Exception ex){
			System.out.print("x");
		}
	}
}
