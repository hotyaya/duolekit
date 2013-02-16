package cn.rs.bbsadmin.mem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.rs.bbsadmin.dao.Post;
import cn.rs.bbsadmin.dao.PostDAO;
import cn.rs.pub.CharFilter;
import cn.rs.pub.TimeStampUtil;

public class LocalCacheUtil {
	
	public LocalCacheUtil(int beforeNowByMinutes,ApplicationContext context) {
		super();
		this.beforeNowByMinutes = beforeNowByMinutes;
		this.context = context;
		iniDatabaseConnection();
	}

	private int beforeNowByMinutes = 120;
	private Connection connection = null;
	private Vector<Integer> vUser = null;
	private Vector<Post> vPosts = null;
	private ApplicationContext context = null;
	
	public void initData() {
		try {
			vUser = new Vector<Integer>();
			vPosts = new Vector<Post>();
			initLocalCacheStru();
			cachedata();
			cacheUserList();
			System.out.println("初始化成功！");
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private Connection iniDatabaseConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite::memory:");
			//connection = DriverManager.getConnection("jdbc:sqlite:bbs.db");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private Connection initLocalCacheStru() {
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("drop table if exists posts");
			statement.executeUpdate("CREATE TABLE posts (  pid integer,  fid integer,  tid integer,  first integer,  author varchar(15),  authorid integer,  subject varchar(80),slen integer,  subject1 varchar(80),slen1 integer,  dateline integer,dateline1 varchar(80),  message text,mlen integer,  message1 text,mlen1 integer,  useip varchar(15),tag integer, PRIMARY KEY (pid)) ");
			statement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
		return connection;
	}

	@SuppressWarnings("rawtypes")
	private void cachedata() throws ParseException {
		CharFilter filter = new CharFilter();
		TimeStampUtil timeutil = new TimeStampUtil();
		//int beforeNowByHours = 120;

		PostDAO postdao = (PostDAO) context.getBean("PostDAO");
		List list = postdao.findAfterDateline(new TimeStampUtil().getTimestampBeforeNowByMinutes(beforeNowByMinutes));
		//20120515.getTimestampBeforeNowByHours(beforeNowByHours)
		//System.out.println(beforeNowByHours + "小时找到记录：" + list.size());
		Object o;
		Iterator iterator = list.iterator();
		int i = 0;
		// Timestamp ts1 = new Timestamp(System.currentTimeMillis());
		String sql0 = "insert into posts(pid ,  fid ,  tid ,  first ,  author ,  authorid ,  subject ,slen ,subject1, slen1 ,  dateline , dateline1, message , mlen, message1,mlen1 ,  useip,tag"
				+
				// ", invisible ,  anonymous ,  usesig ,  htmlon ,  bbcodeoff ,smileyoff ,  parseurloff ,  attachment ,  rate ,  ratetimes ,  status "
				// +
				") values ";
		java.sql.PreparedStatement ps = null;

		while (iterator.hasNext()) {
			i++;
			o = iterator.next();
			if (o instanceof Post) {
				Post p = ((Post) o);
				vPosts.add(p);
				/**
				 * pid integer, fid integer, tid integer, first integer, author
				 * varchar(15), authorid integer, subject varchar(80), dateline
				 * integer, message text, message1 text, useip varchar(15),
				 * invisible integer, anonymous integer, usesig integer, htmlon
				 * integer, bbcodeoff integer, smileyoff integer, parseurloff
				 * integer, attachment integer, rate integer, ratetimes integer,
				 * status integer
				 */
				try {
					ps = connection.prepareStatement(sql0
							+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1, p.getPid());
					ps.setInt(2, p.getFid());
					ps.setInt(3, p.getTid());
					ps.setInt(4, p.getFirst() ? 1 : 0);
					ps.setString(5, p.getAuthor());
					ps.setInt(6, p.getAuthorid());
					ps.setString(7, p.getSubject());
					ps.setInt(8, p.getSubject().length());
					String s = filter.clearForbiddenCharacter(p.getSubject());
					ps.setString(9, s);
					ps.setInt(10, s.length());
					ps.setInt(11, p.getDateline());
					ps.setString(12,
							timeutil.getTimeByUnixTimestamp(p.getDateline()));
					ps.setString(13, p.getMessageString());							//20120516
					ps.setInt(14, p.getMessageString().length());					//20120516
					String m = filter.clearForbiddenCharacter(p.getMessageString());//20120516
					ps.setString(15, m);
					ps.setInt(16, m.length());
					ps.setString(17, p.getUseip());
					ps.setInt(18, 0); // 写入Cache时加入标记；
					int j = ps.executeUpdate();
					if (j == 1)
						System.out.println("OK" + p.getPid());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// CharFilter().clearForbiddenCharacter("中文fd我是中国人as "));
	}

	private void cacheUserList() {
		String sql = "select distinct authorid from posts";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer id = 0;
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt(1);
				vUser.add(new Integer(id));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public Vector<Integer> getvUser() {
		return vUser;
	}

	public Vector<Post> getvPosts() {
		return vPosts;
	}
}
