package cn.railsoft.schedule.dao.entity;

//import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Jobitem entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.railsoft.schedule.dao.entity.Jobitem
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("unchecked")

public class JobitemDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(JobitemDAO.class);
	// property constants
	public static final String USERID = "userid";
	public static final String SEQ = "seq";
	public static final String ACTION_DATE = "actionDate";
	public static final String IP = "ip";
	public static final String TYPE = "type";
	public static final String NUM_PREFIX = "numPrefix";
	public static final String NUM_NO = "numNo";
	public static final String NUM_SUFFIX = "numSuffix";
	public static final String CONTENT1 = "content1";
	public static final String CONTENT2 = "content2";
	public static final String CONTENT3 = "content3";
	public static final String CONTENT4 = "content4";
	public static final String CONTENT5 = "content5";
	public static final String SOURCE = "source";
	public static final String SOURCE_ID = "sourceId";
	public static final String STATUS = "status";
	public static final String MEMO = "memo";

	public void save(Jobitem transientInstance) {
		log.debug("saving Jobitem instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Jobitem persistentInstance) {
		log.debug("deleting Jobitem instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Jobitem findById(java.lang.String id) {
		log.debug("getting Jobitem instance with id: " + id);
		try {
			Jobitem instance = (Jobitem) getSession().get(
					"cn.railsoft.schedule.dao.entity.Jobitem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Jobitem instance) {
		log.debug("finding Jobitem instance by example");
		try {
			List results = getSession().createCriteria(
					"cn.railsoft.schedule.dao.entity.Jobitem").add(
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
		log.debug("finding Jobitem instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Jobitem as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserid(Object userid) {
		return findByProperty(USERID, userid);
	}

	public List findBySeq(Object seq) {
		return findByProperty(SEQ, seq);
	}

	public List findByActionDate(Object actionDate) {
		return findByProperty(ACTION_DATE, actionDate);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByNumPrefix(Object numPrefix) {
		return findByProperty(NUM_PREFIX, numPrefix);
	}

	public List findByNumNo(Object numNo) {
		return findByProperty(NUM_NO, numNo);
	}

	public List findByNumSuffix(Object numSuffix) {
		return findByProperty(NUM_SUFFIX, numSuffix);
	}

	public List findByContent1(Object content1) {
		return findByProperty(CONTENT1, content1);
	}

	public List findByContent2(Object content2) {
		return findByProperty(CONTENT2, content2);
	}

	public List findByContent3(Object content3) {
		return findByProperty(CONTENT3, content3);
	}

	public List findByContent4(Object content4) {
		return findByProperty(CONTENT4, content4);
	}

	public List findByContent5(Object content5) {
		return findByProperty(CONTENT5, content5);
	}

	public List findBySource(Object source) {
		return findByProperty(SOURCE, source);
	}

	public List findBySourceId(Object sourceId) {
		return findByProperty(SOURCE_ID, sourceId);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	public List findAll() {
		log.debug("finding all Jobitem instances");
		try {
			String queryString = "from Jobitem";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Jobitem merge(Jobitem detachedInstance) {
		log.debug("merging Jobitem instance");
		try {
			Jobitem result = (Jobitem) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Jobitem instance) {
		log.debug("attaching dirty Jobitem instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Jobitem instance) {
		log.debug("attaching clean Jobitem instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}