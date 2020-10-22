package com.zaumal.ting89.utils;

import java.text.DecimalFormat;

public class TranUtil {
	public static final DecimalFormat df = new DecimalFormat("######0.00");
	
	public static String tranH(long length){
		String result = length + "Bytes";
		
		long n1 = length/1024;
		if(n1 > 0){
			double n2 = length*1.0/1024;
			result = df.format(n2) + "KB";
			
			n1 = n1/1024;
			if(n1 > 0){
				n2 = n2/1024;
				result = df.format(n2) + "MB";
				
				n1 = n1/1024;
				if(n1 > 0){
					n2 = n2/1024;
					result = df.format(n2) + "GB";
				}
			}
		}
		return result;
	}
	
	public static String tranHTime(long time){
		String result = time + "毫秒";
		
		long n1 = time/1000;
		if(n1 > 0){
			double n2 = time*1.0/1000;
			result = df.format(n2) + "秒";
			
			n1 = n1/60;
			if(n1 > 0){
				n2 = n2/60;
				result = df.format(n2) + "分";
				
				n1 = n1/60;
				if(n1 > 0){
					n2 = n2/60;
					result = df.format(n2) + "小时";
				}
			}
		}
		return result;
	} 
}
