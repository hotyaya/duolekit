package cn.rs.bbsadmin.dao;
// default package

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for Post entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .Post
  * @author MyEclipse Persistence Tools 
 */

public class PostDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(PostDAO.class);
		//property constants
	public static final String FID = "fid";
	public static final String TID = "tid";
	public static final String FIRST = "first";
	public static final String AUTHOR = "author";
	public static final String AUTHORID = "authorid";
	public static final String SUBJECT = "subject";
	public static final String DATELINE = "dateline";
	public static final String MESSAGE = "message";
	public static final String USEIP = "useip";
	public static final String INVISIBLE = "invisible";
	public static final String ANONYMOUS = "anonymous";
	public static final String USESIG = "usesig";
	public static final String HTMLON = "htmlon";
	public static final String BBCODEOFF = "bbcodeoff";
	public static final String SMILEYOFF = "smileyoff";
	public static final String PARSEURLOFF = "parseurloff";
	public static final String ATTACHMENT = "attachment";
	public static final String RATE = "rate";
	public static final String RATETIMES = "ratetimes";
	public static final String STATUS = "status";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Post transientInstance) {
        log.debug("saving Post instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Post persistentInstance) {
        log.debug("deleting Post instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
	//2012-05-06
    public Post findById( java.lang.Integer id) {
        log.debug("getting Post instance with id: " + id);
        try {
            Post instance = (Post) getHibernateTemplate()
                    .get("cn.rs.bbsadmin.dao.Post", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List findByExample(Post instance) {
        log.debug("finding Post instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Post instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Post as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}
    
    @SuppressWarnings("unchecked")
    public List findAfterDateline(long value) {
      log.debug(": " + "dateline"
            + ", value: " + value);
      try {
    	 //String queryString = "from Post as model where model.dateline"+ "= ?";
		 return getHibernateTemplate().find("from Post as model where model.dateline>"+value);
      } catch (RuntimeException re) {
         log.error("find by dateline name failed", re);
         throw re;
      }
	}
    

    @SuppressWarnings("unchecked")
	public List findByFid(Object fid
	) {
		return findByProperty(FID, fid
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByTid(Object tid
	) {
		return findByProperty(TID, tid
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByFirst(Object first
	) {
		return findByProperty(FIRST, first
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByAuthor(Object author
	) {
		return findByProperty(AUTHOR, author
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByAuthorid(Object authorid
	) {
		return findByProperty(AUTHORID, authorid
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findBySubject(Object subject
	) {
		return findByProperty(SUBJECT, subject
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByDateline(Object dateline
	) {
		return findByProperty(DATELINE, dateline
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByMessage(Object message
	) {
		return findByProperty(MESSAGE, message
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByUseip(Object useip
	) {
		return findByProperty(USEIP, useip
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByInvisible(Object invisible
	) {
		return findByProperty(INVISIBLE, invisible
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByAnonymous(Object anonymous
	) {
		return findByProperty(ANONYMOUS, anonymous
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByUsesig(Object usesig
	) {
		return findByProperty(USESIG, usesig
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByHtmlon(Object htmlon
	) {
		return findByProperty(HTMLON, htmlon
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByBbcodeoff(Object bbcodeoff
	) {
		return findByProperty(BBCODEOFF, bbcodeoff
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findBySmileyoff(Object smileyoff
	) {
		return findByProperty(SMILEYOFF, smileyoff
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByParseurloff(Object parseurloff
	) {
		return findByProperty(PARSEURLOFF, parseurloff
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByAttachment(Object attachment
	) {
		return findByProperty(ATTACHMENT, attachment
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByRate(Object rate
	) {
		return findByProperty(RATE, rate
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByRatetimes(Object ratetimes
	) {
		return findByProperty(RATETIMES, ratetimes
		);
	}
	
    @SuppressWarnings("unchecked")
	public List findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

    @SuppressWarnings("unchecked")
	public List findAll() {
		log.debug("finding all Post instances");
		try {
			String queryString = "from Post";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Post merge(Post detachedInstance) {
        log.debug("merging Post instance");
        try {
            Post result = (Post) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Post instance) {
        log.debug("attaching dirty Post instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Post instance) {
        log.debug("attaching clean Post instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PostDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PostDAO) ctx.getBean("PostDAO");
	}
}