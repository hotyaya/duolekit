package org.job.task;

import java.sql.Timestamp;
import java.util.Vector;

import jodd.mail.Email;
import jodd.mail.EmailMessage;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpSslServer;
import jodd.util.MimeTypes;

import org.hibernate.Session;
import org.job.crawler.OACrawler;
import org.job.crawler.TGCrawler;
import org.job.dao.HibernateSessionFactory;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;

public class CrawlerProcess implements Runnable {
	Vector<Doccatalog> v =  new Vector<Doccatalog>();
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
				if (v.size() > 0) indb();
				v.removeAllElements();//20140127
				new OACrawler(v).docrawler();//加入办公文件的自动收集功能；
				if (v.size() > 0) indb();
				
				Thread.sleep(1000 * 2);
				System.gc();
				Thread.sleep(1000 * 60 * 5); //5分钟 60 *
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	private void indb() {
		try {
			int newcount = 0;//记录下本次的新入库数量
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			DoccatalogDAO dao = new DoccatalogDAO();
			String code = "";
			String info = "";
			int repeatcount =0;//如果已经入库数达到3，退出循环。 (20140122)
			for (int i = 0; i < v.size(); i++) { //20140123 int i = v.size(); i > 1; i--
				code = v.elementAt(i).getDoccode(); //20140123 i-1=> i
				if (dao.findByDoccode(code.trim()).size() <= 0) {
					dao.save(v.elementAt(i)); //20140123 i-1=> i
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
			session.getTransaction().commit();
			session.disconnect();//20140122看看占不占资源；
			session.close();//20140122看看占不占资源；
			if (newcount!=0) {
				System.out.println("\n"+"insert " + newcount + " recodes ok!" + new Timestamp(System.currentTimeMillis()).toString());
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
