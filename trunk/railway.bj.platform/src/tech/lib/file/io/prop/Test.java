package tech.lib.file.io.prop;

import java.io.File;
import java.io.IOException;

import jodd.props.Props;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Props props = new Props();
			props.load(new File("config.props"));
			System.out.println(""+props.getValue("oauser"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
