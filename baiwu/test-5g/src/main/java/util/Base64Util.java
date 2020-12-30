package util;

import java.util.Base64;

public class Base64Util {
	public static String en(String s) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(s.getBytes());
	}
}
