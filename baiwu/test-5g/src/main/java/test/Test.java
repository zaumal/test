package test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

import util.GetByteEncode;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String s = "eyJyZXNwb25zZSI6eyJyZXBseSI6eyJkaXNwbGF5VGV4dCI6ItbHxNzRp8+wIiwicG9zdGJhY2siOnsiZGF0YSI6ItbHxNzRp8+wZGF0YSJ9fX19";
		String s = "eyJyZXNwb25zZSI6eyJyZXBseSI6eyJkaXNwbGF5VGV4dCI6IuaZuuiDveWtpuS5oCIsInBvc3RiYWNrIjp7ImRhdGEiOiLmmbrog73lrabkuaBkYXRhIn19fX0=";
		System.out.println(new String(Base64.getDecoder().decode(s.getBytes("GBK")),"GBK"));
		
		
		String encoding = GetByteEncode.getEncoding(s.getBytes("gbk"));
		System.out.println(encoding);
	}
	
	
}
