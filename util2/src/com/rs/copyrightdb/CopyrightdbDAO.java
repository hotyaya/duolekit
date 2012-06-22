package com.rs.copyrightdb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.cr.dao.Softitem;
import cn.rs.cr.dao.SoftitemDAO;

public class CopyrightdbDAO {
	public void doJob(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SoftitemDAO softitemdao = (SoftitemDAO)context.getBean("SoftitemDAO");
		Softitem item = new Softitem();
		item.setRegisterid("1");
		item.setTypecode("xxx-xxx");
		item.setSoftname("Èí¼þ1");
		item.setSoftbrief("1");
		item.setVersion("v1.0");
		item.setAuthor("lh");
		item.setPublishdate("2011-10-01");
		item.setRegisterdate("2012-06-12");
		softitemdao.save(item);
		System.out.println("OK  "+item.getSoftname());

	}
}
