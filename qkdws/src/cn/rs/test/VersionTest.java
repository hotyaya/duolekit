package cn.rs.test;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.model.Version;
import cn.rs.model.VersionDAO;

public class VersionTest extends TestCase {

	public VersionTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test(){
		Version v = new Version();
		v.setId(200);
		v.setCurrentItem("test");
		v.setCurrentVersion(11);
		v.setPublishDate("20120421");
		v.setDescription("我的测试-版本号");

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		VersionDAO versiondao = (VersionDAO)context.getBean("VersionDAO");
		versiondao.save(v);
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
