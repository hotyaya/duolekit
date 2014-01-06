package railway.bj.admin.my.job.dao.entity;

import org.hibernate.Session;
import railway.bj.admin.my.job.dao.HibernateSessionFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}