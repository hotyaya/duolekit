package cn.rs.bbsadmin.mem.filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class UserFilterUtil {
	private boolean checkFirst = true;	
	private int iSetPostCount = 4;//连续4个上下贴发送时间间隔相近
	private int iSetPostInterval = 5;//连续4个上下贴发送时间间隔相近
	
	private int iSendPostLineByMinute = 3;//每分钟发贴阈值
	private int iUserSendPostCount = 0; // 每分钟实际发贴数量
	private Connection connection = null;
	
	public UserFilterUtil(int iSendPostLineByMinute,Connection connection) {
		super();
		this.iSendPostLineByMinute = iSendPostLineByMinute;
		this.connection = connection;
	}

	public boolean checkUserSendPostCount(int interval, int authorid) {
		return checkUserSendPostCount(interval, iSendPostLineByMinute, authorid);
	}

	/**
	 * 	每分钟大于3贴
		连续三个帖子，前贴和后贴时间差正负不超过20秒
	 * @param interval
	 * @param iSendPostLineByMinute
	 * @param authorid
	 */
	public boolean checkUserSendPostCount(int interval, int iSendPostLineByMinute,
			int authorid) {
		if (checkIntervalByMinute(authorid,interval)){
			System.out.println("********** 用户"+authorid+"为可疑用户！！！********");
			return true;
		}
		return false;
	}

	/**
	 * 检测统计
	 */
	private int checkCountByMinute(int authorid,int interval){
		int itag = 0;
		String sql = "SELECT count(*) FROM posts WHERE authorid = "+authorid;
		if (checkFirst) sql = sql + " and first=1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int icount = 0;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()){
				icount = rs.getInt(1);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		if (icount > (interval*iSendPostLineByMinute)){
			itag = 1;
		}
		return itag;
	}
	
	/**
	 * 检测均值
	 */
	private void checkAverageByMinute(int authorid,int interval){
		String sql = "SELECT count(*) FROM posts WHERE authorid = "+authorid;
		if (checkFirst) sql = sql + " and first=1";
	
	}
	
	/**
	 * 检测间隔（最精确）
	 */
	private boolean checkIntervalByMinute(int authorid,int interval){
		String sql = "SELECT * FROM posts WHERE authorid = "+authorid;
		if (checkFirst) sql = sql + " and first=1";
		sql = sql + " order by dateline desc"; //最新产生的靠前；
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ChangeRateUtil cru = new ChangeRateUtil();
		int ilastline = 0;
		int idateline = 0;
		int idifference = 0;
		try {
			ps = connection.prepareStatement(sql);
			//System.out.println(sql);
			rs = ps.executeQuery();
			while (rs.next()){
				idateline = rs.getInt("dateline");
				idifference =  ilastline - idateline;
				Logger.getRootLogger().debug("用户发贴特征："+rs.getInt(1)+":"+rs.getString("author")+":"+ ilastline+"-"+idateline  +"="+idifference+"");
				if (idifference == idateline) continue;
				/**
				 * 以idifference为基准
				 */
				/*=========================*/
				cru.addValue(idifference);
				/*=========================*/
				ilastline = idateline;
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		//System.out.println("# "+sql+"");
		return cru.isBestBeSatisfied();
	}
	
	
}
