package com.job;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.job.dao.HibernateSessionFactory;

public class CopyOfHibernateUtil {
    private static SessionFactory factory=HibernateSessionFactory.getSessionFactory();
    // 使用ThreadLocal集合保存当前业务线程中的SESSION
    private static ThreadLocal session = new ThreadLocal();
   
    static {
        // 第一步：读取HIBERNATE的配置文件,读取hibernate.cfg.xml文件
        Configuration con = new Configuration().configure();
        // 第二步：创建服务注册构建器对象，通过配置对象中加载所有的配置信息，存放到注册服务中
        ServiceRegistryBuilder regBuilder = new ServiceRegistryBuilder().applySettings(con.getProperties());
        // 创建注册服务
        ServiceRegistry reg = regBuilder.buildServiceRegistry();
        // 第三步：创建会话工厂
        factory = con.buildSessionFactory(reg);
    }
    
    
    public static Session getLocalThreadSession() {
        Session s = (Session) session.get();// 获取当前线程下的SESSION
        if (s == null) {
            s = factory.getCurrentSession();// 获取当前线程中的SESSION， 需在在Hibernate.cfg.xml文件，具体请看面的说明
            session.set(s);// 将当前SESSION放入到当前线程的容器中保存
        }
        return s;
    }
    
    public static void closeSession() {
        Session s = (Session) session.get();// 获取当前线程下的SESSION
        if (s != null) {
            // s.close();//这里无需将Session关闭，因为该Session是保存在当前线程//中的，线程执行完毕Session自然会销毁
            session.set(null);// 将当前线程中的会话清除
        }
    }

}

