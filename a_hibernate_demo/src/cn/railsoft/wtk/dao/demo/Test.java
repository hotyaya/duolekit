package cn.railsoft.wtk.dao.demo;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import cn.railsoft.wtk.dao.HibernateSessionFactory;
import cn.railsoft.wtk.dao.entity.Dept;
import cn.railsoft.wtk.dao.entity.DeptDAO;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// File file = new File("./hibernate.cfg.xml");
		// Configuration conf = new Configuration().configure(file);
		Session session = HibernateSessionFactory.getSession();
		try{
			session.beginTransaction();
			new DeptDAO().save(new Dept("0", "信息化处", "新成立"));
			session.getTransaction().commit();
			//JOptionPane.showMessageDialog(null, "TEST");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
