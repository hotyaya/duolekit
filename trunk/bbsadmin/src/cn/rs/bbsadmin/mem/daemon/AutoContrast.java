package cn.rs.bbsadmin.mem.daemon;

import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Vector;

import cn.rs.bbsadmin.dao.Post;
import cn.rs.bbsadmin.mem.LocalCacheUtil;
import cn.rs.bbsadmin.mem.filter.KeyWordFilterUtil;
import cn.rs.bbsadmin.mem.filter.UserFilterUtil;
import cn.rs.bbsadmin.notifyinterface.ReceiveNoticeSet;
import cn.rs.bbsadmin.notifyinterface.message.NoticeMessage;
import cn.rs.bbsadmin.notifyinterface.message.ProgressMessage;

public class AutoContrast extends ReceiveNoticeSet {
	
	DubiousInfoHandler dubiousInfoHandler = null;
	
	private LocalCacheUtil localcacheutil = null;
	private int interval = 6;	//每6分钟进行一次检查
	private UserFilterUtil userfilterutil = null;
	private KeyWordFilterUtil keywordfilterutil = null;	//20120515
	
	public AutoContrast(DubiousInfoHandler dubiousInfoHandler,LocalCacheUtil localcacheutil, int interval) {
		super();
		this.dubiousInfoHandler = dubiousInfoHandler;
		this.localcacheutil = localcacheutil;
		this.interval = interval;
		this.userfilterutil = new UserFilterUtil(3,localcacheutil.getConnection());	//初始化用户筛选器; 3为每分钟的发贴数
		this.keywordfilterutil = new KeyWordFilterUtil(localcacheutil.getConnection());//20120515
	}

	/**
	 * （1）.用户不停发贴 【筛选可疑用户】检测间隔5分钟；每分钟大于5贴；前贴和后贴时间差正负不超过5秒
	 * （2）.贴子特点【筛选可疑帖子用户】主题贴子的长度大部分一致，一致贴超过5条，且一致率到80%的。
	 * 如主题贴【63，63，63，64，63，63，63，63，63，63】63为8条，64为2条，80%一致 （3）.关键字清贴；
	 * 从服务器取关键字，进行关键字正则匹配，有电话数字或者都为电话数字贴子的内容【增加数据保留,数字和中文数字】包含:电话号码，正则匹配
	 * 
	 * @return
	 */
	public boolean contrastByUser() {
		Vector<Integer> vusers = localcacheutil.getvUser();
		for (int i = 0; i < vusers.size(); i++) {
			int authorid = ((Integer)vusers.elementAt(i)).intValue();
			notifyProgressOut(new ProgressMessage(vusers.size(),i));
			//Logger.getRootLogger().info(new Timestamp(System.currentTimeMillis())+" 用户："+authorid);
			if (userfilterutil.checkUserSendPostCount(interval, authorid)){
				dubiousInfoHandler.handleMember(authorid);
				notifyMessageOut(new NoticeMessage(new Timestamp(System.currentTimeMillis()),"============"+authorid +"==========="));									//2012-05-05
				listPost(authorid);
			}
		}
		notifyProgressOut(new ProgressMessage(vusers.size(),vusers.size()));
		return true;
	}
	
	private void listPost(int authorid){
		Vector<Post> v = localcacheutil.getvPosts();
		
		for (int i=0;i<v.size();i++){
			if (((Post)v.elementAt(i)).getAuthorid().intValue()==authorid){
				dubiousInfoHandler.handlePost(((Post)v.elementAt(i)).getPid());
				System.out.println("非法帖子 id"+ ((Post)v.elementAt(i)).getPid());
				notifyMessageOut(new NoticeMessage(new Timestamp(System.currentTimeMillis()),"可疑帖:"+((Post)v.elementAt(i)).getPid(),2));									//2012-05-05
			}
		}
	}
	
	/**
	 * 20120516
	 * 有关键字的就都处理了！
	 * @return
	 */
	public boolean contrastByKeyword(){
//		Hashtable ht = new Hashtable();
//		Vector<Post> v =  localcacheutil.getvPosts();
//		Post p = null;
//		for (int i=0;i<v.size();i++){
//			p = (Post)v.elementAt(i);
//			p.getSubject();
//			p.getMessageString();
//		}
		Vector v = keywordfilterutil.testSubject();
		for (int i=0;i<v.size();i++){
			dubiousInfoHandler.handlePost(((Integer)v.elementAt(i)).intValue());
			System.out.println("非法帖子 id"+ ((Integer)v.elementAt(i)).intValue());
			notifyMessageOut(new NoticeMessage(new Timestamp(System.currentTimeMillis()),"关键字可疑帖:"+((Integer)v.elementAt(i)).intValue(),2));									//2012-05-05
		}
		return true;
	}
}
