package cn.railsoft.schedule.dao.entity;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Vjobitemmaxseq entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.railsoft.schedule.dao.entity.Vjobitemmaxseq
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class VjobitemmaxseqDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VjobitemmaxseqDAO.class);



	public Vjobitemmaxseq findById(java.lang.Long id) {
		log.debug("getting Vjobitemmaxseq instance with id: " + id);
		try {
			Vjobitemmaxseq instance = (Vjobitemmaxseq) getSession().get(
					"cn.railsoft.schedule.dao.entity.Vjobitemmaxseq", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Vjobitemmaxseq instance) {
		log.debug("finding Vjobitemmaxseq instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.railsoft.schedule.dao.entity.Vjobitemmaxseq").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Vjobitemmaxseq instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Vjobitemmaxseq as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List<Vjobitemmaxseq> findAll() {
		log.debug("finding all Vjobitemmaxseq instances");
		try {
			String queryString = "from Vjobitemmaxseq";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

}