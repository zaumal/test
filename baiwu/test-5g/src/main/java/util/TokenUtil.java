package util;

import java.util.Base64;

public class TokenUtil {
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
}
