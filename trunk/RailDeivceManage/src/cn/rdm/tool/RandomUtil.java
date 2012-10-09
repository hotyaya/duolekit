package cn.rdm.tool;

import java.util.Random;

public class RandomUtil {

	public static void randomTest()

	{
		System.out.println("--------------test()--------------");
		// 返回以毫秒为单位的当前时间。
		long r1 = System.currentTimeMillis();
		// 返回带正号的 double 值，大于或等于 0.0，小于 1.0。
		double r2 = Math.random();
		// 通过Random类来获取下一个随机的整数
		int r3 = new Random().nextInt();
		System.out.println("r1 = " + r1);
		System.out.println("r3 = " + r2);
		System.out.println("r2 = " + r3);
	}

	public static void testNoSeed()

	{
		System.out.println("--------------testNoSeed()--------------");
		// 创建不带种子的测试Random对象
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			System.out.println(random.nextInt());
		}
	}

	public static void testSeed1()

	{
		System.out.println("--------------testSeed1()--------------");
		// 创建带种子的测试Random对象
		Random random = new Random(555L);
		for (int i = 0; i < 3; i++) {
			System.out.println(random.nextInt());
		}
	}

	public static void testSeed2() {
		System.out.println("--------------testSeed2()--------------");
		// 创建带种子的测试Random对象
		Random random = new Random();
		random.setSeed(555L);
		for (int i = 0; i < 3; i++) {
			System.out.println(random.nextInt());
		}
	}

	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(generateString(6));
		// System.out.println(generateMixString(6));
		// System.out.println(generateLowerString(6));
		// System.out.println(generateUpperString(6));
		// System.out.println(generateZeroString(6));
		// System.out.println(toFixdLengthString(123, 15));
		// System.out.println(toFixdLengthString(123L, 15));
	}

}