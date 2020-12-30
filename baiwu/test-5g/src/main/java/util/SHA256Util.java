package util;

import java.security.MessageDigest;

public class SHA256Util {
	public static String encode(String str) {
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodeStr = byte2hex(messageDigest.digest());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return encodeStr;
	}
	
	private static String byte2hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		String temp;
		for(int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if(temp.length() == 1) {
				sb.append("0");
			}
			sb.append(temp);
		}
		return sb.toString();
	}
}
