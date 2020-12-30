package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
	public static String getHttpHeaderDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MM yyy HH:mm:ss 'GMT'",Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sdf.format(cal.getTime());
	}
}
