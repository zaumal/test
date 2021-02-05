package test;

import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import util.DateUtils;
import util.SHA256Util;
import okhttp3.*;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.*;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil2 {

    public static RestTemplate restTemplate() {
        OkHttpClient httpClient = okHttpClient();
        ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        // 可以增加拦截器
        //restTemplate.setInterceptors(...);
        // 可以添加消息转换
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    public static OkHttpClient okHttpClient() {
        TrustAllCerts trustManager = new TrustAllCerts();
        return new OkHttpClient.Builder()
                .sslSocketFactory(createSSLSocketFactory(), trustManager)
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .retryOnConnectionFailure(false)
                .connectionPool(new ConnectionPool(200, 10, TimeUnit.MINUTES))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS)
                .writeTimeout(300, TimeUnit.SECONDS)
                .build();
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
        String chatbotId = "10655210001";
        String appId = "iap_201201001008";
        String appSecret = "12345678";
        //通过静态方法创建请求体
        //创建文件表单的请求体，把文件请求体、文本参数放入表单中
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        MediaType mediaType = MediaType.parse("*/*");
        builder.addFormDataPart("Thumbnail", thumbnail.getName(), RequestBody.create(thumbnail, mediaType));
        builder.addFormDataPart("File", file.getName(), RequestBody.create(file, mediaType));
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
            System.out.println(response.code());
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject upload(File thumbnail, File file) {
        String url = "https://ft101.sh.5gm.wo.cn:9443";
        String chatbotId = "10655210001";
        String appId = "iap_201201001008";
        String appSecret = "12345678";
        String headerDate = DateUtils.getHttpHeaderDate();
        String tid = UUID.randomUUID().toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "multipart/form-data");
        headers.add("Terminal-type", "Chatbot");
        headers.add("Authorization", tokenHeader(appId, appSecret, headerDate));
        headers.add("Date", headerDate);
        headers.add("X-3GPP-Intended-Identity", getChatbotURI(chatbotId));
        headers.add("tid", tid);

        //MultiValueMap 可以一个key对应多个value
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("Thumbnail", new FileSystemResource(thumbnail));
        map.add("File", new FileSystemResource(file));
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
        
        try {
	        ResponseEntity<String> response = OkHttpUtil2.restTemplate().exchange(url, HttpMethod.POST, request, String.class);
	        
	        //todo 状态码测试
	        HttpStatus httpStatus = response.getStatusCode();
	        JSONObject result = new JSONObject();
	        if (String.valueOf(httpStatus.value()).startsWith("2")) {
	            result.put("tid", tid);
	            result.put("code", httpStatus.value());
	            result.put("msg", httpStatus.toString());
	        } else {
	            result.put("tid", null);
	            result.put("code", httpStatus.value());
	            result.put("msg", httpStatus.toString());
	        }
	        System.out.println("manager上传资源文件响应：" + result);
	        return result;
        }catch (Exception e) {
        	e.printStackTrace();
			try {
				System.out.println(new String(e.getMessage().getBytes("GBK"),"UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
        String thumbnailType = "image/PNG";
        File file = new File("x:\\rcs\\20210118\\zhang001\\a6131d73887c5fa669bb4518dac83173.JPG");
        String fileType = "image/JPG";
//        upload(thumbnail, thumbnailType, file, fileType);
        upload(thumbnail, file);
    }
}
