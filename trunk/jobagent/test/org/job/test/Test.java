package org.job.test;

import java.sql.Timestamp;

import jodd.datetime.JDateTime;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new JDateTime().getTimeInMillis());
		System.out.println(System.currentTimeMillis());
		
		JDateTime jdt = new JDateTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(jdt);
		JDateTime jdt1 = new JDateTime();
		jdt1.subSecond(5);
		System.out.println(jdt1);
		System.out.println("jdt1.isAfter(jdt):"+jdt1.isAfter(jdt));
		
		
	}

}
