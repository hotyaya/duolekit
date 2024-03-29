package org.job.dao.entity;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Onlineterminal entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see org.job.dao.entity.Onlineterminal
 * @author MyEclipse Persistence Tools
 */
public class OnlineterminalDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(OnlineterminalDAO.class);
	// property constants
	public static final String IP = "ip";
	public static final String HOSTNAME = "hostname";
	public static final String ISACTIVE = "isactive";

	public void save(Onlineterminal transientInstance) {
		log.debug("saving Onlineterminal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Onlineterminal persistentInstance) {
		log.debug("deleting Onlineterminal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Onlineterminal findById(java.lang.String id) {
		log.debug("getting Onlineterminal instance with id: " + id);
		try {
			Onlineterminal instance = (Onlineterminal) getSession().get(
					"org.job.dao.entity.Onlineterminal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Onlineterminal instance) {
		log.debug("finding Onlineterminal instance by example");
		try {
			List results = getSession()
					.createCriteria("org.job.dao.entity.Onlineterminal")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Onlineterminal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Onlineterminal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByHostname(Object hostname) {
		return findByProperty(HOSTNAME, hostname);
	}

	public List findByIsactive(Object isactive) {
		return findByProperty(ISACTIVE, isactive);
	}

	public List findAll() {
		log.debug("finding all Onlineterminal instances");
		try {
			String queryString = "from Onlineterminal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Onlineterminal merge(Onlineterminal detachedInstance) {
		log.debug("merging Onlineterminal instance");
		try {
			Onlineterminal result = (Onlineterminal) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Onlineterminal instance) {
		log.debug("attaching dirty Onlineterminal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Onlineterminal instance) {
		log.debug("attaching clean Onlineterminal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}