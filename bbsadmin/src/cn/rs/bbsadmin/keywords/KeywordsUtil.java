package cn.rs.bbsadmin.keywords;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class KeywordsUtil extends KeywordsListenerSet{
	
	public KeywordsUtil(String filePath) {
		super();
		this.filePath = filePath;
	}
	public KeywordsUtil() {
		super();
	}

	private String filePath = "keywords.properties";
	//private Properties props = null;
	
	public static void main(String[] args) {
		KeywordsUtil ku = new KeywordsUtil();
		String filePath = "keywords.properties";
		ku.writeProperties(filePath, "1", "小姐");
		ku.writeProperties(filePath, "2", "枪支");
		ku.deleteProperties(filePath, "2");
		ku.clearProperties(filePath);
		ku.writeProperties(filePath, "1", "小姐");
		ku.writeProperties(filePath, "2", "枪支");
		ku.readProperties(filePath);
	}
	
	// 读取properties到数组
	public Vector readPropertiesToArray() {
		Vector v = new Vector();
		Properties props = readProperties();
		try {
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String property = props.getProperty(key);
				v.add(property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	public Properties readProperties() {
		return readProperties(filePath);
	}
	
	// 读取properties的全部信息
	public Properties readProperties(String filePath) {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				System.out.println(key +":" + Property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	
	
	public void writeProperties(String parameterName,String parameterValue) {
		writeProperties(filePath,parameterName,parameterValue);
	}
	// 写入properties信息
	public void writeProperties(String filePath, String parameterName,
			String parameterValue) {
		Properties prop = new Properties();
		try {
//			File directory = new File(".");
//			System.out.println(directory.getCanonicalPath());
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			prop.setProperty(parameterName, parameterValue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + parameterName + "' value");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error " + e.toString());
		}
	}
	
	
	public void deleteProperties(String parameterName) {
		deleteProperties(filePath,parameterName);
	}
	// 删除properties信息
	public void deleteProperties(String filePath, String parameterName) {
		Properties prop = new Properties();
		try {
//			File directory = new File(".");
//			System.out.println(directory.getCanonicalPath());
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			//prop.setProperty(parameterName, parameterValue);
			prop.remove(parameterName);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update '" + parameterName + "' value");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					+ parameterName + " value error " + e.toString());
		}
	}
	
	public void clearProperties() {
		clearProperties(filePath);
	}
	
	//清空属性properties信息
	public void clearProperties(String filePath) {
		Properties prop = new Properties();
		try {
//			File directory = new File(".");
//			System.out.println(directory.getCanonicalPath());
			InputStream fis = new FileInputStream(filePath);
			// 从输入流中读取属性列表（键和元素对）
			prop.load(fis);
			// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(filePath);
			//prop.setProperty(parameterName, parameterValue);
			prop.clear();
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "clear '" + "' value");
		} catch (IOException e) {
			System.err.println("Visit " + filePath + " for updating "
					 + " value error " + e.toString());
		}
	}
	

}
