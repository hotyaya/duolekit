package cn.rs.pub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = 0;
		String strCN = "";
		//String regEx = "[\\u4e00-\\u9fa5]";
		String regEx = "[\\u4e00-\\u7f51|\\u7f53-\\u9fa5]"; //删除字符”罒“
		String str = "罒●╒１８６罒５７１罒８８８４８╕罒●";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				count = count + 1;
				strCN = strCN + m.group();
				System.out.println(m.group().toString() + " =" +Integer.toHexString(m.group().toString().toCharArray()[0]));
			}
		}
		System.out.println("has " + count + "ge ");
		System.out.println(" "+strCN);
	}
	
	public String clearForbiddenCharacter(String str){
		String strCN = "";
		//String regEx = "[\\u4e00-\\u9fa5]";
		//String regEx = "[\\u4e00-\\u7f51|\\u7f53-\\u9fa5]"; //删除字符”罒“
		//String regEx = "[\\u4e00-\\u7f51|\\u7f53-\\u9fa5|0-9|\uFF10-\uFF19]"; //删除字符”罒“
		String regEx = "[\\u4e00-\\u7f51|\\u7f53-\\u9fa5|\uFF10-\uFF19]"; //删除字符”罒“
		//str = "中文fd我是中国人as ";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				strCN = strCN + m.group();
			}
		}
		return toNum(strCN);//20120504
	}
	
	/**
	 *
			中文数字 一二三四=》罗马数字1234
			全角数字１２３４=》罗马数字1234
	 */
	public String toNum(String str){
		//String strNum = "";
		str = str.replaceAll("一","1");
		str = str.replaceAll("二","2");
		str = str.replaceAll("三","3");
		str = str.replaceAll("四","4");
		str = str.replaceAll("五","5");
		str = str.replaceAll("六","6");
		str = str.replaceAll("七","7");
		str = str.replaceAll("八","8");
		str = str.replaceAll("九","9");
		str = str.replaceAll("零","0");

		str = str.replaceAll("１","1");
		str = str.replaceAll("２","2");
		str = str.replaceAll("３","3");
		str = str.replaceAll("４","4");
		str = str.replaceAll("５","5");
		str = str.replaceAll("６","6");
		str = str.replaceAll("７","7");
		str = str.replaceAll("８","8");
		str = str.replaceAll("９","9");
		str = str.replaceAll("０","0");
		
		return str;
	}
	
	
}
