package test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import util.DateUtils;
import util.SHA256Util;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    public static OkHttpClient okHttpClient() {
        TrustAllCerts trustManager = new TrustAllCerts();
        return new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory(), trustManager)
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .retryOnConnectionFailure(false)
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
//        return new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).build();
    }


    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

    private static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public static JSONObject upload(File thumbnail, String thumbnailType, File file, String fileType) {
        String url = "https://ft101.sh.5gm.wo.cn:9443";
//        String chatbotId = "125200401111530";
//        String appId = "125200401111530";
//        String appSecret = "Z9!XqA|01&";
        
        String chatbotId = "10655210001";
		String appId = "iap_201201001008";
		String appSecret = "12345678";
		
        //通过静态方法创建请求体
        //创建文件表单的请求体，把文件请求体、文本参数放入表单中
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("Thumbnail", thumbnail.getName(), RequestBody.create(thumbnail, MediaType.parse(thumbnailType)));
        builder.addFormDataPart("File", file.getName(), RequestBody.create(file, MediaType.parse(fileType)));
        MultipartBody multipartBody = builder.build();
        String headerDate = DateUtils.getHttpHeaderDate();
        String tid = UUID.randomUUID().toString();
        Request request = new Request.Builder().url(url)
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Terminal-type", "Chatbot")
                .addHeader("Authorization", tokenHeader(appId, appSecret, headerDate))
                .addHeader("Date", headerDate)
                .addHeader("X-3GPP-Intended-Identity", getChatbotURI(chatbotId))
                .addHeader("tid", tid)
                .post(multipartBody)//post方式提交表单
                .build();

        try {
            Response response = OkHttpUtil.okHttpClient().newCall(request).execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String tokenHeader(String appId, String password, String headerDate) {
        String tokenHeader = null;
        String token = SHA256Util.encode(password);
        String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
        try {
            String base64Str = Base64.getEncoder().encodeToString(text.getBytes("UTF-8"));
            tokenHeader = "Basic ".concat(base64Str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return tokenHeader;
    }

    private static String getChatbotURI(String chatbotId) {
        return "sip:" + chatbotId + "@botplatform.rcs.chinaunicom.cn";
    }

    public static void main(String[] args) {
        File thumbnail = new File("x:\\rcs\\20210118\\zhang001\\dac26b5675e7ecf08c7a25a48598dff5.PNG");
//        File thumbnail = new File("D:\\tmp\\202101\\dac26b5675e7ecf08c7a25a48598dff5.PNG");
        String thumbnailType = "image/PNG";
        File file = new File("x:\\rcs\\20210118\\zhang001\\a6131d73887c5fa669bb4518dac83173.JPG");
//        File file = new File("D:\\tmp\\202101\\a6131d73887c5fa669bb4518dac83173.JPG");
        String fileType = "image/JPG";
        System.out.println(upload(thumbnail, thumbnailType, file, fileType));
    }
}
