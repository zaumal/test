package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static String getHttpHeaderDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyy HH:mm:ss 'GMT'",Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(cal.getTime());
	}
	
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
