package cn.rs.bbsadmin.mem.filter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import cn.rs.bbsadmin.keywords.IKeyWordsChanged;
import cn.rs.bbsadmin.keywords.KeywordsUtil;

public class KeyWordFilterUtil implements IKeyWordsChanged{
	private boolean checkFirst = true;	
	private Vector vKeywords = null;
	public KeyWordFilterUtil(Connection connection) {
		super();
		this.connection = connection;
		loadKeyWords();
	}

	private Connection connection = null;
	private KeywordsUtil ku = new KeywordsUtil();
	
	private void loadKeyWords(){
		vKeywords = ku.readPropertiesToArray();
	}
	
	public String buildsql(String fieldname){
		String sql = " 0 ";
		for (int i = 0 ;i<vKeywords.size();i++){
			sql = sql + " OR ("+ fieldname+"  LIKE '%"+vKeywords.elementAt(i)+"%' )";
		}
		if (checkFirst) sql = " ("+sql + ") and first=1 ";
		return (" SELECT * FROM posts WHERE "+sql);
	}

	public Vector testSubject(){
		return testPost("subject1");
	}
	
	public  Vector testMessage(){
		return testPost("message1");
	}
	
	/**
	 * 测试某一字段是否含有关键字；
	 * @param fieldname
	 */
	private Vector testPost(String fieldname){
		Vector v = new Vector();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int pid = 0;
		try {
			ps = connection.prepareStatement(buildsql(fieldname));
			rs = ps.executeQuery();
			while (rs.next()){
				pid = rs.getInt("pid");
				rs.getString("author");
				rs.getInt("authorid");
				rs.getString("subject");
				rs.getInt("slen");
				rs.getString("subject1");
				rs.getInt("slen1");
				rs.getString("dateline1");
				rs.getString("message");
				rs.getInt("mlen");
				rs.getString("message1");
				rs.getInt("mlen1");
				System.out.println("找到关键字:"+pid);
				v.add(new Integer(pid));
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
		return v;
	}

	@Override
	public void changeKeyWords(boolean reload) {
		// TODO Auto-generated method stub
		
	}
	
}
