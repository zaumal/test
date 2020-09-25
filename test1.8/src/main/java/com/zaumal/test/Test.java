package com.zaumal.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static void main(String[] args) {
		t3();
	}
	
	public static void t4() {
		List<String> list = new ArrayList<>();
		list.add("dd");
	}
	
	public static String getSd(String s) {
		return "\\,\\s*" + s + "\\s*=\\s*\\'?\\$?\\{?([^}]*)\\}?\\'?\\s*(.*(?=[^,]+))\\,";
	}
	
	public static void t3() {
		String s = ",data_date='数据1',data_type='${数据类型1,par_org='1000110',";
//		s =",yyyy=${yyyy},data_type=数据类型1,parOrg=${curOrg},";
		Pattern pattern = Pattern.compile(getSd("data_type"));
		Matcher matcher = pattern.matcher(s);
		String dataFilter = matcher.replaceAll(Matcher.quoteReplacement(",data_type=${y},"));
		System.out.println(dataFilter);
	}
	
	public static void t2() {
		String sql = "yyyy=${z}";
		sql = sql.replaceAll("${z}", "11111");
		System.out.println(sql);
	}
	
	public static Date subMonth(Date date, int m) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);

		rightNow.add(Calendar.MONTH, m);
		return rightNow.getTime();
	}
	

	public static void t1() {
		String sql = "aaaaalastMonth=${yyyyMM-1},yyyy=${yyyy}aaaaaaa";
		String var = "\\$\\{([^}]*)\\}";
		String var1 = "lastMonth=";
		String var2 = "yyyy=";
		
		Pattern pattern1 = Pattern.compile(var1+var);
		Matcher m1 = pattern1.matcher(sql);
		sql = m1.replaceAll(var1+Matcher.quoteReplacement("${x}"));
		
		Pattern pattern2 = Pattern.compile(var2+var);
		Matcher m2 = pattern2.matcher(sql);
		sql = m2.replaceAll(var2+Matcher.quoteReplacement("${y}"));
		
		System.out.println(sql);
	}
	
}
