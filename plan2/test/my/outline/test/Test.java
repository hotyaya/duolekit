package my.outline.test;

import java.sql.Timestamp;

import my.outline.dao.HibernateSessionFactory;
import my.outline.dao.entity.Tag;
import my.outline.dao.entity.TagDAO;

import org.hibernate.Session;

public class Test {
	public static void main(String[] args) {
		try{
			System.out.println(""+new Timestamp(System.currentTimeMillis()));
			Session session = HibernateSessionFactory.getSession();
			session.beginTransaction();
			TagDAO dao = new TagDAO();
			Tag tag = new Tag();
			//tag.setTagid(111);//转为触发器 
			tag.setName("关系");
			dao.save(tag);
			session.getTransaction().commit();
			System.out.println(""+new Timestamp(System.currentTimeMillis()));			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
