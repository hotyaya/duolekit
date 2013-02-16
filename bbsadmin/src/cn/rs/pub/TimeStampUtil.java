package cn.rs.pub;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStampUtil {

	SimpleDateFormat format;
	Date basedate = null;
	Calendar currentCalendar = null;

	public TimeStampUtil() {
		try {
			format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			basedate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
					.parse("1970/01/01 08:00:00");
			currentCalendar = Calendar.getInstance();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void test() throws ParseException {
		// format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = (Calendar) currentCalendar.clone();
		System.out.println("currentTime:" + format.format(calendar.getTime()));
		calendar.add(Calendar.DATE, -1);
		System.out.println("befor24Time:" + format.format(calendar.getTime()));

		Calendar calendar1 = (Calendar) currentCalendar.clone();
		calendar1.add(Calendar.HOUR, -1);
		System.out.println("befor1Hour:" + format.format(calendar1.getTime()));

		Calendar calendar2 = (Calendar) currentCalendar.clone();
		calendar2.add(Calendar.HOUR, -2);
		System.out.println("befor2Hour:" + format.format(calendar2.getTime()));

		long l2 = (format.parse(format.format(calendar2.getTime())).getTime() - basedate
				.getTime()) / 1000;
		System.out.println("两个小时前的时间戳为：" + new Long(l2).intValue());

		System.out
				.println("=====================================================");
		System.out.println("currentTime:"
				+ format.format(currentCalendar.getTime()));
	}

	public String getTimeByUnixTimestamp(int t) {
		long l1 = t ;	//如果直接 int的宽度不够；
		long l0 = basedate.getTime();
		long l = l1*1000 + l0;
		return new Timestamp(l).toString();
	}

	/* 此前几小时的时间戳 */
	public int getTimestampBeforeNowByHours(int hours) {
		Calendar calendarx = (Calendar) currentCalendar.clone();
		calendarx.add(Calendar.HOUR, -hours);
		// System.out.println("befor1Hour:"
		// +format.format(calendarx.getTime()));
		long l2 = 0;
		try {
			l2 = (format.parse(format.format(calendarx.getTime())).getTime() - basedate
					.getTime()) / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("两个小时前的时间戳为："+new Long(l2).intValue());
		return new Long(l2).intValue();
	}
	
	/* 此前几分钟的时间戳 */
	public int getTimestampBeforeNowByMinutes(int minutes) {
		Calendar calendarx = (Calendar) currentCalendar.clone();
		calendarx.add(Calendar.MINUTE, -minutes);
		// System.out.println("befor1Hour:"
		// +format.format(calendarx.getTime()));
		long l2 = 0;
		try {
			l2 = (format.parse(format.format(calendarx.getTime())).getTime() - basedate
					.getTime()) / 1000;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("两个小时前的时间戳为："+new Long(l2).intValue());
		return new Long(l2).intValue();
	}

}
