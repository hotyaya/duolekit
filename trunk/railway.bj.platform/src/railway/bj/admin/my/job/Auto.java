package railway.bj.admin.my.job;

import java.sql.Timestamp;
import java.util.Vector;

import org.hibernate.Session;

import railway.bj.admin.my.job.dao.HibernateSessionFactory;
import railway.bj.admin.my.job.dao.entity.Doccatalog;
import railway.bj.admin.my.job.dao.entity.DoccatalogDAO;
import railway.bj.admin.telegram.TGCrawler;

public class Auto implements Runnable {
	Vector<Doccatalog> v =  new Vector<Doccatalog>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Auto()).start();
	}

	@Override
	public void run() {
		while(true){
			try{
				//读入配置文件!
				
				//电报爬虫!
				new TGCrawler(v).docrawler();
				if (v.size() > 0) indb();
				
				Thread.sleep(1000 * 2);
				System.gc();
				Thread.sleep(1000 * 60 * 5); //5分钟
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	void indb() {
		try {
			int newcount = 0;//记录下本次的新入库数量
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			DoccatalogDAO dao = new DoccatalogDAO();
			String code = "";
			for (int i = v.size(); i > 1; i--) {
				code = v.elementAt(i - 1).getDoccode();
				if (dao.findByDoccode(code.trim()).size() <= 0) {
					dao.save(v.elementAt(i - 1));
					newcount++;
				} else {
					//System.out.print(code+"已经入库！");
				}
			}
			session.getTransaction().commit();
			if (newcount!=0) {
				System.out.println("insert " + newcount + " recodes ok!" + new Timestamp(System.currentTimeMillis()).toString());
			}else{
				System.out.print("+");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
