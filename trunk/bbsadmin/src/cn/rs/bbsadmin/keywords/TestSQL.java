package cn.rs.bbsadmin.keywords;

import java.util.Vector;

import junit.framework.TestCase;

public class TestSQL extends TestCase {
	Vector vKeywords = new Vector();
	boolean checkFirst = true;
	protected void setUp() throws Exception {
		checkFirst = true;
		checkFirst = false;
		vKeywords.add("小姐");
		vKeywords.add("枪支");
	}

	protected void tearDown() throws Exception {

	}
	
	public void test(){
		String sql = " 0 ";
		for (int i = 0 ;i<vKeywords.size();i++){
			sql = sql + " OR ("+ " subject1 "+"  LIKE '%"+vKeywords.elementAt(i)+"%' )";
		}
		if (checkFirst) sql = " ("+sql + ") and first=1 ";
		System.out.println (" SELECT * FROM posts WHERE "+sql);
	}
	
}
