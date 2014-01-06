package railway.bj.admin.my.job.dao.entity;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Doccatalog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see railway.bj.admin.my.job.dao.entity.Doccatalog
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("rawtypes")
public class DoccatalogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DoccatalogDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String DOCSENDDATE = "docsenddate";
	public static final String DOCSENDER = "docsender";
	public static final String DOCCAPTION = "doccaption";
	public static final String DOCCODE = "doccode";
	public static final String CONTACT = "contact";
	public static final String PHONE = "phone";
	public static final String BASEURL = "baseurl";
	public static final String URL = "url";
	public static final String INDATE = "indate";
	public static final String ISHIDDEN = "ishidden";

	public void save(Doccatalog transientInstance) {
		log.debug("saving Doccatalog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Doccatalog persistentInstance) {
		log.debug("deleting Doccatalog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Doccatalog findById(java.lang.Long id) {
		log.debug("getting Doccatalog instance with id: " + id);
		try {
			Doccatalog instance = (Doccatalog) getSession().get(
					"railway.bj.admin.my.job.dao.entity.Doccatalog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Doccatalog instance) {
		log.debug("finding Doccatalog instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"railway.bj.admin.my.job.dao.entity.Doccatalog")
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
		log.debug("finding Doccatalog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Doccatalog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByDocsenddate(Object docsenddate) {
		return findByProperty(DOCSENDDATE, docsenddate);
	}

	public List findByDocsender(Object docsender) {
		return findByProperty(DOCSENDER, docsender);
	}

	public List findByDoccaption(Object doccaption) {
		return findByProperty(DOCCAPTION, doccaption);
	}

	public List findByDoccode(Object doccode) {
		return findByProperty(DOCCODE, doccode);
	}

	public List findByContact(Object contact) {
		return findByProperty(CONTACT, contact);
	}

	public List findByPhone(Object phone) {
		return findByProperty(PHONE, phone);
	}

	public List findByBaseurl(Object baseurl) {
		return findByProperty(BASEURL, baseurl);
	}

	public List findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List findByIndate(Object indate) {
		return findByProperty(INDATE, indate);
	}

	public List findByIshidden(Object ishidden) {
		return findByProperty(ISHIDDEN, ishidden);
	}

	public List findAll() {
		log.debug("finding all Doccatalog instances");
		try {
			String queryString = "from Doccatalog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Doccatalog merge(Doccatalog detachedInstance) {
		log.debug("merging Doccatalog instance");
		try {
			Doccatalog result = (Doccatalog) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Doccatalog instance) {
		log.debug("attaching dirty Doccatalog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Doccatalog instance) {
		log.debug("attaching clean Doccatalog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}