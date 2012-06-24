package cn.qkd.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.model.Version;
import cn.rs.model.VersionDAO;

import com.opensymphony.xwork2.ActionSupport;

public class WSAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4416461354649979777L;
	private List<Version> versions;
	
	@SuppressWarnings("unchecked")
	public void list(){
		versions = new ArrayList<Version>();
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		VersionDAO versiondao = (VersionDAO)context.getBean("VersionDAO");
		List list = versiondao.findAll();
		Object o;
		Iterator iterator= list.iterator();
		while (iterator.hasNext()){
			o = iterator.next();
			if (o instanceof Version){
				versions.add((Version)o);
			}
		}
	}
	
	@Override
	public String execute() throws Exception {
		list();
		return super.execute();
	}

	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

}
