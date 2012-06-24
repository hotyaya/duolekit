package cn.rs.model;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * Version entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.rs.model.Version
 * @author MyEclipse Persistence Tools
 */

public class VersionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(VersionDAO.class);
	// property constants
	public static final String CURRENT_ITEM = "currentItem";
	public static final String CURRENT_VERSION = "currentVersion";
	public static final String PUBLISH_DATE = "publishDate";
	public static final String DESCRIPTION = "description";

	protected void initDao() {
		// do nothing
	}

	public void save(Version transientInstance) {
		log.debug("saving Version instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Version persistentInstance) {
		log.debug("deleting Version instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Version findById(java.lang.Integer id) {
		log.debug("getting Version instance with id: " + id);
		try {
			Version instance = (Version) getHibernateTemplate().get(
					"cn.rs.model.Version", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByExample(Version instance) {
		log.debug("finding Version instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Version instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Version as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List findByCurrentItem(Object currentItem) {
		return findByProperty(CURRENT_ITEM, currentItem);
	}

	@SuppressWarnings("unchecked")
	public List findByCurrentVersion(Object currentVersion) {
		return findByProperty(CURRENT_VERSION, currentVersion);
	}

	@SuppressWarnings("unchecked")
	public List findByPublishDate(Object publishDate) {
		return findByProperty(PUBLISH_DATE, publishDate);
	}

	@SuppressWarnings("unchecked")
	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all Version instances");
		try {
			String queryString = "from Version";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Version merge(Version detachedInstance) {
		log.debug("merging Version instance");
		try {
			Version result = (Version) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Version instance) {
		log.debug("attaching dirty Version instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Version instance) {
		log.debug("attaching clean Version instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static VersionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (VersionDAO) ctx.getBean("VersionDAO");
	}
}