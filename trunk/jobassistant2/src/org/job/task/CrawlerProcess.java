package org.job.task;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import jodd.datetime.JDateTime;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.job.Application;
import org.job.crawler.DBCrawler;
import org.job.crawler.OACrawler;
import org.job.crawler.TGCrawler;
import org.job.dao.HibernateUtil;
import org.job.dao.entity.Doccatalog;
import org.job.dao.entity.DoccatalogDAO;
import org.job.dao.entity.Vcatadaynum;
import org.job.dao.entity.VcatadaynumDAO;
import org.job.interf.INotifyMessage;

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
		
		String domain = "";
		try{
			domain = InetAddress.getLocalHost().getHostName();
		}catch(Exception ex){
			System.out.print("i");
		}
		
		while(true){
			try{
				//TODO 读入配置文件!
				
				//电报爬虫!
				if (Application.getV("TGDO").trim().toUpperCase().equals("TRUE")){
					v.removeAllElements();//20140123
					//new TGCrawler(v).docrawler("liuhui","890");
					new TGCrawler(v).docrawler(Application.getV("TGUSER"),Application.getV("TGPASS"));
					if (v.size() > 0) indb("TG");
					Thread.sleep(1000 * 2);
				}
				//OA公文
				if (Application.getV("OADO").trim().toUpperCase().equals("TRUE")){
					v.removeAllElements();//20140127
					//new OACrawler(v).docrawler("zhanglan", "zl", domain.trim(), "10.64.3.55");//加入办公文件的自动收集功能；
					new OACrawler(v).docrawler(Application.getV("OAUSER"), Application.getV("OAPASS"), domain.trim(), "10.64.3.55");//加入办公文件的自动收集功能；
					if (v.size() > 0) indb("OA");
					Thread.sleep(1000 * 2);
				}
				//待办
				if (Application.getV("DBDO").trim().toUpperCase().equals("TRUE")){
					v.removeAllElements();//20140211
					//new DBCrawler(v).docrawler("xxhclh", "a", domain.trim(), "10.64.3.55");//加入办公文件的自动收集功能；
					new DBCrawler(v).docrawler(Application.getV("DBUSER"), Application.getV("DBPASS"), domain.trim(), "10.64.3.55");//加入办公文件的自动收集功能；
					if (v.size() > 0) indb("DB");//待办公文；
					Thread.sleep(1000 * 2);
				}
				
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
	
	@SuppressWarnings("rawtypes")
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
				List list = dao.findByDoccode(code.trim());
				if (list.size() <= 0) {
					session.save(v.elementAt(i)); //20140123 i-1=> i //20140207 dao=> session
					newcount++;
					info = info + ";" + v.elementAt(i).getDoccaption() ;//20140123 i-1=> i
				} else {
					if (ctag.equals("DB")){
						/**
						 * 20140213
						 * 如果发现已经存入的公文，又进行了待办处理，变为待办类型，并修改日期，已显示在今天。
						 */
						if (list.size()==1){
							try{
								Doccatalog dc = ((Doccatalog)list.get(0));
								if (!dc.getType().equals("DB")){
									dc.setType("DB");
									dc.setDocsenddate(Integer.parseInt(new JDateTime().toString("YYYYMMDD")));
									dc.setIstodo(true);//待办标识
									dc.setIstodotime(new Timestamp(System.currentTimeMillis()));//待办时间
									session.update(dc);//20140213
									newcount++;
									info = info + ";" + v.elementAt(i).getDoccaption() ;//20140123 i-1=> i
								}
							}catch(Exception ex){
								ex.printStackTrace();
							}
						}
					}else{
						//System.out.print(code+"已经入库！");
						repeatcount++; 
					}
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
				}else if (ctag.equals("DB")){
					temp ="待办";
				}else{
					temp ="其它";
				}
				System.out.println("\n"+"insert " + newcount + "项" + temp + " recodes ok!" + new Timestamp(System.currentTimeMillis()).toString());
				notifyListener("NEWFILE"+"|"+ctag+"|"+newcount);
				if (Application.getV("MAILDO")!=null && Application.getV("MAILDO").trim().toUpperCase().equals("TRUE")){
					new Thread(new MailProcess(info)).start();
				}
			}else{
				System.out.print("+");
				//mail("-");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
