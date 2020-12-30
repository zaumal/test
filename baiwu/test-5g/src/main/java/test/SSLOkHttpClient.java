package test;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.DateUtil;
import util.SHA256Util;

public class SSLOkHttpClient {
	protected OkHttpClient client;

	@SuppressWarnings("deprecation")
	public SSLOkHttpClient() {
		this.client = new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory())
				.hostnameVerifier(new TrustAllHostnameVerifier()).build();
	}

	public static void main(String[] args) {
		String appid = "iap_12520020000120";
		String password = "4f95d15126615625250926f3747dd5861b745927ce58051295989de43deeb120";
		String url = "https://f.10086.cn/5g/v/chatbotim/chatbot/messaging/v1/outbound/sip:12520020000120@botplatform.rcs.chinamobile.com/requests";
		String mediaType = "application/xml";
		String phone = "15811491455";
		String data = "<?xml version=\\\"1.0\\\" encoding=\\\"UTF-8\\\"?><msg:outboundMessageRequest xmlns:msg=\\\"urn:oma:xml:rest:netapi:messaging:1\\\"><address>tel:+8615811491455</address><destinationAddress>tel:+8615811491455</destinationAddress><senderAddress>sip:12520020000120@botplatform.rcs.chinamobile.com</senderAddress><outboundIMMessage><reportRequest>Delivered</reportRequest><reportRequest>Displayed</reportRequest><contributionID>c20e8bea-bca2-4f4d-96ac-46bb0799c620</contributionID><conversationID>d8ddcb51-7816-4607-ad1c-3296b6c5362d</conversationID><messageId>c20e8bea-bca2-4f4d-96ac-46bb0799c620</messageId><inReplyToContributionID>889be393-c303-46a7-ae3b-618d1f9802db</inReplyToContributionID><serviceCapability><capabilityId>ChatbotSA</capabilityId><version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version></serviceCapability><contentType>multipart/mixed; boundary=\\\"next\\\"</contentType>\\r\\n\" + \r\n" + 
				"				\"<bodyText><![CDATA[\\r\\n\" + \r\n" + 
				"				\"--next\\r\\n\" + \r\n" + 
				"				\"Content-Type: application/vnd.gsma.botmessage.v1.0+json\\r\\n\" + \r\n" + 
				"				\"Content-Length: 1605\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"{\\\"message\\\":{\\\"generalPurposeCardCarousel\\\":{\\\"layout\\\":{\\\"cardWidth\\\":\\\"MEDIUM_WIDTH\\\"},\\\"content\\\":[{\\\"media\\\":{\\\"mediaUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/be818dfe52ec5ecccad6cd4000ae77c5.jpg\\\",\\\"mediaContentType\\\":\\\"image/jpg\\\",\\\"mediaFileSize\\\":\\\"151250\\\",\\\"thumbnailUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/b79826a2a221420b59b6bb512c92b844.jpg\\\",\\\"thumbnailContentType\\\":\\\"\\\",\\\"thumbnailFileSize\\\":\\\"\\\",\\\"height\\\":\\\"SHORT_HEIGHT\\\"},\\\"title\\\":\\\"集微IOT\\\",\\\"description\\\":\\\"Motion+传感器\\\",\\\"suggestions\\\":[{\\\"reply\\\":{\\\"displayText\\\":\\\"必选\\\",\\\"postback\\\":{\\\"data\\\":\\\",c6d08072-8538-4895-a1d7-02f50c3d4b7d\\\"}}}]},{\\\"media\\\":{\\\"mediaUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/b405a8e0e3fa353042ff5d2d3b74d9fb.jpg\\\",\\\"mediaContentType\\\":\\\"image/jpg\\\",\\\"mediaFileSize\\\":\\\"27654\\\",\\\"thumbnailUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/9a8b14bdf2b1969e6b5667e41fc5e54d.jpg\\\",\\\"thumbnailContentType\\\":\\\"\\\",\\\"thumbnailFileSize\\\":\\\"\\\",\\\"height\\\":\\\"SHORT_HEIGHT\\\"},\\\"title\\\":\\\"小米\\\",\\\"description\\\":\\\"加湿器\\\",\\\"suggestions\\\":[{\\\"reply\\\":{\\\"displayText\\\":\\\"选择加湿器\\\",\\\"postback\\\":{\\\"data\\\":\\\",5e00adfd-8aa1-4d2c-aa68-9a2a0c5ff77c\\\"}}}]},{\\\"media\\\":{\\\"mediaUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/be9356d1e2a3f02b7f825a379a3b9b2d.jpg\\\",\\\"mediaContentType\\\":\\\"image/jpg\\\",\\\"mediaFileSize\\\":\\\"19451\\\",\\\"thumbnailUrl\\\":\\\"http://124.239.146.131:9000/rcs/20201208/jwkj658/0100d82e759e0fbda9b71c004be19ce9.jpg\\\",\\\"thumbnailContentType\\\":\\\"\\\",\\\"thumbnailFileSize\\\":\\\"\\\",\\\"height\\\":\\\"SHORT_HEIGHT\\\"},\\\"title\\\":\\\"++GF++\\\",\\\"description\\\":\\\"地暖\\\",\\\"suggestions\\\":[{\\\"reply\\\":{\\\"displayText\\\":\\\"选择地暖\\\",\\\"postback\\\":{\\\"data\\\":\\\",8de63428-0464-485d-9aea-d53041b8437f\\\"}}}]}]}}}\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"--next\\r\\n\" + \r\n" + 
				"				\"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\\r\\n\" + \r\n" + 
				"				\"Content-Length: 18\\r\\n\" + \r\n" + 
				"				\"\\r\\n\" + \r\n" + 
				"				\"{\\\"suggestions\\\":[]}\\r\\n\" + \r\n" + 
				"				\"--next--\\r\\n\" + \r\n" + 
				"				\"]]></bodyText>\\r\\n\" + \r\n" + 
				"				\"</outboundIMMessage><clientCorrelator>567895</clientCorrelator></msg:outboundMessageRequest>";
		
		SSLOkHttpClient client = new SSLOkHttpClient();
		System.out.println(data);
		client.request(appid, password, url, data, mediaType, phone);
	}
	
	
	public void request(String appid,String password,String url,String data,String mediaType,String phone) {
		String headerDate = DateUtil.getHttpHeaderDate();
		String authorization = tokenHeader(appid, password, headerDate);

		RequestBody body = RequestBody.create(data, MediaType.parse(mediaType));
		Request request = new Request.Builder()
				.url(url)
				.addHeader("Address", "+86" + phone)
				.addHeader("Authorization", authorization)
				.addHeader("Date", headerDate)
				.addHeader("Content-Type", "application/xml")
				.post(body)
				.build();

		try {
			Response response = client.newCall(request).execute();
			System.out.println(response);
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
	protected String tokenHeader(String appId, String password, String headerDate) {
		String tokenHeader = null;
//		String token = SHA256Util.encode(password);
		String token = password;
		String text = appId.concat(":").concat(SHA256Util.encode(token + headerDate));
		try {
			String base64Str = Base64.getEncoder().encodeToString(text.getBytes());
			tokenHeader = "Basic ".concat(base64Str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenHeader;
	}
}
