package cn.rs.bbsadmin.mem.daemon;

import java.util.Date;

import org.springframework.context.ApplicationContext;

import cn.rs.bbsadmin.dao.CDBMember;
import cn.rs.bbsadmin.dao.CDBMemberDAO;
import cn.rs.bbsadmin.dao.CDBThread;
import cn.rs.bbsadmin.dao.CDBThreadDAO;
import cn.rs.bbsadmin.dao.Post;
import cn.rs.bbsadmin.dao.PostDAO;
import cn.rs.bbsadmin.notifyinterface.ReceiveNoticeSet;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;

/**
 * 1 帖子移动到待审核 cdb-threads 表 displayorder 改为: -2 cdb-posts 表 invisible 改为：-2 2
 * 用户取消发帖权限 cdb-members 表 groupid 改为 4
 * 
 * @author Hui
 * 
 */
public class DubiousInfoHandler extends ReceiveNoticeSet {
	ApplicationContext context = null;
	PostDAO pdao = null;
	CDBMemberDAO mdao = null;
	CDBThreadDAO tdao = null;

	private int iMode = 0; // 0什么都不处理 1为改为审核

	public DubiousInfoHandler(int iMode, ApplicationContext context) {
		super();
		this.iMode = iMode;
		this.context = context;
		init();
	}

	public boolean init() {
		pdao = (PostDAO) context.getBean("PostDAO");
		mdao = (CDBMemberDAO) context.getBean("CDBMemberDAO");
		tdao = (CDBThreadDAO) context.getBean("CDBThreadDAO");
		return true;
	}

	public boolean handlePost(int id) {
		if (iMode == 1) {

			Post p = pdao.findById(id);
			p.setInvisible(-2);
			pdao.merge(p);
			CDBThread t = tdao.findById(p.getTid());
			if (t==null)return false;
			t.setDisplayorder(-2);
			tdao.merge(t);
			notifyMessageOut(new NoticeMessage(" moved",2));
		}
		return true;
	}

	public boolean handleMember(int id) {
		if (iMode == 1) {
			CDBMember m = mdao.findById(id);
			m.setGroupid(4);
			if (m.getBday()==null) m.setBday(new Date(System.currentTimeMillis()));//有空值错误
			mdao.merge(m);
			notifyMessageOut(new NoticeMessage("&& pid"+id+" moved &&",2));
		}
		return true;
	}

	public int getiMode() {
		return iMode;
	}

	public void setiMode(int iMode) {
		this.iMode = iMode;
	}
}
