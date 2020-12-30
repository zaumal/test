package test;

import java.util.Base64;

import util.DateUtil;
import util.SHA256Util;

public class Test1 {
	public static String tokenHeader(String appId,String password,String headerDate) {
		String tokenHeader = null;
		String token = SHA256Util.encode(password);
		String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
		try {
			String base64Str = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
			tokenHeader = "Basic ".concat(base64Str);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tokenHeader;
	}
	
	public static void main(String[] args) {
		String appid = "iap_201201001008";
		String token = "12345678";
		String headerDate = DateUtil.getHttpHeaderDate();
		System.out.println(headerDate);
		String s = tokenHeader(appid, token, headerDate);
		System.out.println(s);
	}
}
