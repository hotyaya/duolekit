package cn.rs.bbsadmin.dao;
// default package

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for CDBMember entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .CDBMember
  * @author MyEclipse Persistence Tools 
 */

public class CDBMemberDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(CDBMemberDAO.class);
		//property constants
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String SECQUES = "secques";
	public static final String GENDER = "gender";
	public static final String ADMINID = "adminid";
	public static final String GROUPID = "groupid";
	public static final String GROUPEXPIRY = "groupexpiry";
	public static final String EXTGROUPIDS = "extgroupids";
	public static final String REGIP = "regip";
	public static final String REGDATE = "regdate";
	public static final String LASTIP = "lastip";
	public static final String LASTVISIT = "lastvisit";
	public static final String LASTACTIVITY = "lastactivity";
	public static final String LASTPOST = "lastpost";
	public static final String POSTS = "posts";
	public static final String DIGESTPOSTS = "digestposts";
	public static final String OLTIME = "oltime";
	public static final String PAGEVIEWS = "pageviews";
	public static final String CREDITS = "credits";
	public static final String EXTCREDITS1 = "extcredits1";
	public static final String EXTCREDITS2 = "extcredits2";
	public static final String EXTCREDITS3 = "extcredits3";
	public static final String EXTCREDITS4 = "extcredits4";
	public static final String EXTCREDITS5 = "extcredits5";
	public static final String EXTCREDITS6 = "extcredits6";
	public static final String EXTCREDITS7 = "extcredits7";
	public static final String EXTCREDITS8 = "extcredits8";
	public static final String EMAIL = "email";
	public static final String SIGSTATUS = "sigstatus";
	public static final String TPP = "tpp";
	public static final String PPP = "ppp";
	public static final String STYLEID = "styleid";
	public static final String DATEFORMAT = "dateformat";
	public static final String TIMEFORMAT = "timeformat";
	public static final String PMSOUND = "pmsound";
	public static final String SHOWEMAIL = "showemail";
	public static final String NEWSLETTER = "newsletter";
	public static final String INVISIBLE = "invisible";
	public static final String TIMEOFFSET = "timeoffset";
	public static final String NEWPM = "newpm";
	public static final String ACCESSMASKS = "accessmasks";
	public static final String EDITORMODE = "editormode";
	public static final String CUSTOMSHOW = "customshow";
	public static final String XSPACESTATUS = "xspacestatus";



	protected void initDao() {
		//do nothing
	}
    
    public void save(CDBMember transientInstance) {
        log.debug("saving CDBMember instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(CDBMember persistentInstance) {
        log.debug("deleting CDBMember instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public CDBMember findById( java.lang.Integer id) {
        log.debug("getting CDBMember instance with id: " + id);
        try {
            CDBMember instance = (CDBMember) getHibernateTemplate()
                    .get("cn.rs.bbsadmin.dao.CDBMember", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(CDBMember instance) {
        log.debug("finding CDBMember instance by example");
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
      log.debug("finding CDBMember instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from CDBMember as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUsername(Object username
	) {
		return findByProperty(USERNAME, username
		);
	}
	
	public List findByPassword(Object password
	) {
		return findByProperty(PASSWORD, password
		);
	}
	
	public List findBySecques(Object secques
	) {
		return findByProperty(SECQUES, secques
		);
	}
	
	public List findByGender(Object gender
	) {
		return findByProperty(GENDER, gender
		);
	}
	
	public List findByAdminid(Object adminid
	) {
		return findByProperty(ADMINID, adminid
		);
	}
	
	public List findByGroupid(Object groupid
	) {
		return findByProperty(GROUPID, groupid
		);
	}
	
	public List findByGroupexpiry(Object groupexpiry
	) {
		return findByProperty(GROUPEXPIRY, groupexpiry
		);
	}
	
	public List findByExtgroupids(Object extgroupids
	) {
		return findByProperty(EXTGROUPIDS, extgroupids
		);
	}
	
	public List findByRegip(Object regip
	) {
		return findByProperty(REGIP, regip
		);
	}
	
	public List findByRegdate(Object regdate
	) {
		return findByProperty(REGDATE, regdate
		);
	}
	
	public List findByLastip(Object lastip
	) {
		return findByProperty(LASTIP, lastip
		);
	}
	
	public List findByLastvisit(Object lastvisit
	) {
		return findByProperty(LASTVISIT, lastvisit
		);
	}
	
	public List findByLastactivity(Object lastactivity
	) {
		return findByProperty(LASTACTIVITY, lastactivity
		);
	}
	
	public List findByLastpost(Object lastpost
	) {
		return findByProperty(LASTPOST, lastpost
		);
	}
	
	public List findByPosts(Object posts
	) {
		return findByProperty(POSTS, posts
		);
	}
	
	public List findByDigestposts(Object digestposts
	) {
		return findByProperty(DIGESTPOSTS, digestposts
		);
	}
	
	public List findByOltime(Object oltime
	) {
		return findByProperty(OLTIME, oltime
		);
	}
	
	public List findByPageviews(Object pageviews
	) {
		return findByProperty(PAGEVIEWS, pageviews
		);
	}
	
	public List findByCredits(Object credits
	) {
		return findByProperty(CREDITS, credits
		);
	}
	
	public List findByExtcredits1(Object extcredits1
	) {
		return findByProperty(EXTCREDITS1, extcredits1
		);
	}
	
	public List findByExtcredits2(Object extcredits2
	) {
		return findByProperty(EXTCREDITS2, extcredits2
		);
	}
	
	public List findByExtcredits3(Object extcredits3
	) {
		return findByProperty(EXTCREDITS3, extcredits3
		);
	}
	
	public List findByExtcredits4(Object extcredits4
	) {
		return findByProperty(EXTCREDITS4, extcredits4
		);
	}
	
	public List findByExtcredits5(Object extcredits5
	) {
		return findByProperty(EXTCREDITS5, extcredits5
		);
	}
	
	public List findByExtcredits6(Object extcredits6
	) {
		return findByProperty(EXTCREDITS6, extcredits6
		);
	}
	
	public List findByExtcredits7(Object extcredits7
	) {
		return findByProperty(EXTCREDITS7, extcredits7
		);
	}
	
	public List findByExtcredits8(Object extcredits8
	) {
		return findByProperty(EXTCREDITS8, extcredits8
		);
	}
	
	public List findByEmail(Object email
	) {
		return findByProperty(EMAIL, email
		);
	}
	
	public List findBySigstatus(Object sigstatus
	) {
		return findByProperty(SIGSTATUS, sigstatus
		);
	}
	
	public List findByTpp(Object tpp
	) {
		return findByProperty(TPP, tpp
		);
	}
	
	public List findByPpp(Object ppp
	) {
		return findByProperty(PPP, ppp
		);
	}
	
	public List findByStyleid(Object styleid
	) {
		return findByProperty(STYLEID, styleid
		);
	}
	
	public List findByDateformat(Object dateformat
	) {
		return findByProperty(DATEFORMAT, dateformat
		);
	}
	
	public List findByTimeformat(Object timeformat
	) {
		return findByProperty(TIMEFORMAT, timeformat
		);
	}
	
	public List findByPmsound(Object pmsound
	) {
		return findByProperty(PMSOUND, pmsound
		);
	}
	
	public List findByShowemail(Object showemail
	) {
		return findByProperty(SHOWEMAIL, showemail
		);
	}
	
	public List findByNewsletter(Object newsletter
	) {
		return findByProperty(NEWSLETTER, newsletter
		);
	}
	
	public List findByInvisible(Object invisible
	) {
		return findByProperty(INVISIBLE, invisible
		);
	}
	
	public List findByTimeoffset(Object timeoffset
	) {
		return findByProperty(TIMEOFFSET, timeoffset
		);
	}
	
	public List findByNewpm(Object newpm
	) {
		return findByProperty(NEWPM, newpm
		);
	}
	
	public List findByAccessmasks(Object accessmasks
	) {
		return findByProperty(ACCESSMASKS, accessmasks
		);
	}
	
	public List findByEditormode(Object editormode
	) {
		return findByProperty(EDITORMODE, editormode
		);
	}
	
	public List findByCustomshow(Object customshow
	) {
		return findByProperty(CUSTOMSHOW, customshow
		);
	}
	
	public List findByXspacestatus(Object xspacestatus
	) {
		return findByProperty(XSPACESTATUS, xspacestatus
		);
	}
	

	public List findAll() {
		log.debug("finding all CDBMember instances");
		try {
			String queryString = "from CDBMember";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public CDBMember merge(CDBMember detachedInstance) {
        log.debug("merging CDBMember instance");
        try {
            CDBMember result = (CDBMember) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CDBMember instance) {
        log.debug("attaching dirty CDBMember instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(CDBMember instance) {
        log.debug("attaching clean CDBMember instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CDBMemberDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CDBMemberDAO) ctx.getBean("CDBMemberDAO");
	}
}