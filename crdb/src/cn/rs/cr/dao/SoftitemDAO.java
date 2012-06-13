package cn.rs.cr.dao;
// default package

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for Softitem entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Softitem
  * @author MyEclipse Persistence Tools 
 */

public class SoftitemDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(SoftitemDAO.class);
		//property constants
	public static final String TYPECODE = "typecode";
	public static final String SOFTNAME = "softname";
	public static final String SOFTBRIEF = "softbrief";
	public static final String VERSION = "version";
	public static final String AUTHOR = "author";
	public static final String PUBLISHDATE = "publishdate";
	public static final String REGISTERDATE = "registerdate";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Softitem transientInstance) {
        log.debug("saving Softitem instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Softitem persistentInstance) {
        log.debug("deleting Softitem instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Softitem findById( java.lang.String id) {
        log.debug("getting Softitem instance with id: " + id);
        try {
            Softitem instance = (Softitem) getHibernateTemplate()
                    .get("cn.rs.cr.dao.Softitem", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Softitem instance) {
        log.debug("finding Softitem instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Softitem instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Softitem as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTypecode(Object typecode
	) {
		return findByProperty(TYPECODE, typecode
		);
	}
	
	public List findBySoftname(Object softname
	) {
		return findByProperty(SOFTNAME, softname
		);
	}
	
	public List findBySoftbrief(Object softbrief
	) {
		return findByProperty(SOFTBRIEF, softbrief
		);
	}
	
	public List findByVersion(Object version
	) {
		return findByProperty(VERSION, version
		);
	}
	
	public List findByAuthor(Object author
	) {
		return findByProperty(AUTHOR, author
		);
	}
	
	public List findByPublishdate(Object publishdate
	) {
		return findByProperty(PUBLISHDATE, publishdate
		);
	}
	
	public List findByRegisterdate(Object registerdate
	) {
		return findByProperty(REGISTERDATE, registerdate
		);
	}
	

	public List findAll() {
		log.debug("finding all Softitem instances");
		try {
			String queryString = "from Softitem";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Softitem merge(Softitem detachedInstance) {
        log.debug("merging Softitem instance");
        try {
            Softitem result = (Softitem) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Softitem instance) {
        log.debug("attaching dirty Softitem instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Softitem instance) {
        log.debug("attaching clean Softitem instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static SoftitemDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (SoftitemDAO) ctx.getBean("SoftitemDAO");
	}
}