package cn.railsoft.simulate.pub;

public class Toolkit {
	
	/**
	 * 4位随机字母
	 */
	public static String random4char() {
		// 产生随机的字母
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			char readomLetter = (char) (Math.random() * 26 + 'a');
			//System.out.println("..."+String.valueOf(readomLetter));
			sb.append(readomLetter);
		}
		return (sb.toString());
	}
	
	public static void main(String[] args) {
		Toolkit.random4char();
	}
}
