package test.t1;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TokenUtil {

    public static String getHttpHeaderDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat greenwichDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        greenwichDate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return greenwichDate.format(cal.getTime());
    }

//    public static String tokenHeader(String appId, String password, String headerDate) {
//        String tokenHeader = null;
//        String token = SHA256Util.encode(password);
//        String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
//        try {
//            String base64Str = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
//            tokenHeader = "Basic ".concat(base64Str);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return tokenHeader;
//    }

    public static String getCmChatbotURI(String chatbotId) {
        return "sip:" + chatbotId + "@botplatform.rcs.chinamobile.com";
    }

    public static String getUnChatbotURI(String chatbotId) {
        return "sip:" + chatbotId + "@botplatform.rcs.chinaunicom.cn";
    }

    public static String getTelChatbotURI(String chatbotId) {
        return "sip:" + chatbotId + "@botplatform.rcs.vnet.cn";
    }
}
