package test;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.DateUtil;
import util.SHA256Util;

public abstract class AbstractDemo {
	protected final static MediaType mediaTypeStr = MediaType.parse("application/xml");
	protected final static MediaType MULTIPART_FORM_DATA = MediaType.parse("multipart/form-data");
	
	protected String appId;
	
	protected String phone;
	protected String serverRoot = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer";
	protected String apiVersion = "v1"; 
	
	protected String headerDate;
	protected String authorization;
	protected OkHttpClient client;

	@SuppressWarnings("deprecation")
	public AbstractDemo(String appid,String password) {
		this.appId = appid;
		this.headerDate = DateUtil.getHttpHeaderDate();
		System.out.println("headerDate : " + headerDate);
		this.authorization = tokenHeader(appid, password);
		System.out.println("authorization : " + authorization);
		System.out.println("==================================");
		
		this.client = new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).build();
	}
	
	long getFileSize() {
		File file = new File("C:\\Users\\gongxiaoliang\\Documents\\timg.jpg");
		return file.length();
	}
	String getFileUrl() {
		return "https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1608208202035&amp;di=264a57f1685fa99847a4841dd7aec9b0&amp;imgtype=0&amp;src=http%3A%2F%2Fa4.att.hudong.com%2F25%2F99%2F19300000421423134190997943822.jpg";
	}
	String getThumbnailUrl() {
		return "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2328538646,1170053427&amp;fm=11&amp;gp=0.jpg";
	}
	long getThumbnailSize() {
		File file = new File("C:\\Users\\gongxiaoliang\\Documents\\2.jpg");
		return file.length();
	}
	
	protected void request(String data,Function<String,Request> function) {
		Request request = function.apply(data);
		
		request(request);
	}
	
	void request(Request request) {
		try {
			Response response = client.newCall(request).execute();
			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();
			System.out.println();
			System.out.println("response body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response.body().string());
			System.out.println();
			System.out.println();
			System.out.println("=====================================");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public void request(String data) {
//		System.out.println("request xml ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
//		System.out.println(data);
//		System.out.println();
//		System.out.println();
//		
//		Request request = getRequest(data);
//
//		try {
//			Response response = client.newCall(request).execute();
//			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
//			System.out.println(response);
//			System.out.println();
//			System.out.println();
//			System.out.println("response body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
//			System.out.println(response.body().string());
//			System.out.println();
//			System.out.println();
//			System.out.println("=====================================");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	String getDate() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return df.format(new Date());
	}

	private SSLSocketFactory createSSLSocketFactory() {
		SSLSocketFactory ssfFactory = null;

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, new TrustManager[] { new TrustAllCerts() }, new SecureRandom());

			ssfFactory = sc.getSocketFactory();
		} catch (Exception e) {
		}

		return ssfFactory;
	}

	private class TrustAllCerts implements X509TrustManager {
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

	private class TrustAllHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	protected String tokenHeader(String appId, String password) {
		String tokenHeader = null;
		String token = SHA256Util.encode(password);
		String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
		try {
			String base64Str = Base64.getEncoder().encodeToString(text.getBytes());
			tokenHeader = "Basic ".concat(base64Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenHeader;
	}
	
	protected String getchatbotUri(String chatbotId) {
		return "sip:" + chatbotId + "@botplatform.rcs.chinamobile.com";
	}
	protected String getXml(String contentType,String bodyText) {return null;};
}
