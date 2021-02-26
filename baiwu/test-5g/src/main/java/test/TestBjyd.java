package test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.DateUtil;

//北京移动
public class TestBjyd {
	
	private OkHttpClient client = new OkHttpClient.Builder()
    		.sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())
    		.hostnameVerifier(new TrustAllHostnameVerifier())
    		.retryOnConnectionFailure(false)
    		.connectionPool(new ConnectionPool(200, 10, TimeUnit.MINUTES))
    		.connectTimeout(30, TimeUnit.SECONDS)
    		.readTimeout(300, TimeUnit.SECONDS)
    		.writeTimeout(300, TimeUnit.SECONDS)
    		.build();
	
	protected String serverRoot;
	protected String sendMessageUrl;
	protected String revokeUrl;
	protected String uploadFileUrl;
	protected String downloadUrl;
	protected String deleteUrl;
	
	protected String findChatbotUrl;
	protected String chatbotOptionUrl;
	protected String menuUrl;
	
	protected String getchatbotSip() {
		return "sip:" + this.chatbotId + "@botplatform.rcs.chinamobile.com";
	}
	
	protected String cspToken;
	protected String cspId;
	protected String chatbotId;
	protected String headerDate = DateUtil.getHttpHeaderDate();// Mon, 06 Jul 2020 11:28:53 GMT
	protected String TEL_URI = "tel:+8615330759941";
	protected String authorization;
	
	public void init() {
		this.sendMessageUrl = serverRoot + "/messaging/group/v1/outbound/" + getchatbotSip() + "/requests";
		this.revokeUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/revoke";
		this.uploadFileUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/upload";
		this.downloadUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/download";
		this.deleteUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/delete";
		this.findChatbotUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/find/chatBotInfo";
		this.chatbotOptionUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/update/chatBotInfo/optionals";
		this.menuUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/update/chatBotInfo/menu";
	}
	
	public TestBjyd(String cspToken,String cspId,String chatbotId) {
		this.cspToken = decodeStr(cspToken);//Base64解密
		this.cspId = cspId;
		this.chatbotId = chatbotId;
	}
	
	public static void main(String[] args) {
		//腾讯
		String cspToken = "NzYyMjc5MTQ2OTExMzU3ZjE3ZjhlZjI5OWNkY2U5NTE3NTZmYzA3MDg0YTQ4ZjRlMWUxNDIyODYwY2NiODExZA==";
		String cspId = "202002230027";
		String chatbotId = "2020022310027";
		
		TestBjyd t = new TestBjyd(cspToken,cspId,chatbotId);
		t.serverRoot = "https://cmic-csp-cgw.cmicmaap.com";
		t.init();
		t.getAuthorization();
		
		//纯文本消息
		t.sendText();
	}
	
	public String getAuthorization() {
		System.out.println("获取 Authorization:");
//		String cspId = "test12345";
//		String cspToken = "ff556388699c84a4ae73bc719eda6480c0d8290c575f297d7f65dad8f9c6804f";
//		String headerDate = "Mon, 06 Jul 2020 11:28:53 GMT";
		String authorization = "Basic "+encodeStr(cspId+":"+getSHA256StrJava(cspToken+headerDate)).replaceAll("\r\n", "");
		System.out.println(authorization);
//		System.out.println(authorization.equals("Basic dGVzdDEyMzQ1OmI3MzEzNzgwYTU5ZDg2OTRlMzU0NGFlZGM2ZmNhNjA0MjIwM2ZlMDBmNWVmNmYyOTI5YjYwOWJjZThkYjIzYzU="));
		this.authorization = authorization;
		return this.authorization;
	}
	
	String sendText() {
		System.out.println("纯文本消息:");
		String url = sendMessageUrl;
		String body = getText();
		System.out.println("消息体：" + body);
		return request(new Request.Builder()
				.addHeader("Host", this.serverRoot)
				.addHeader("Authorization", this.authorization)
				.addHeader("Content-Type", "application/xml")
				.addHeader("Date", this.headerDate)
				.addHeader("Content-Length", String.valueOf(body.getBytes().length))
				.url(url)
				.post(RequestBody.create(MediaType.parse("application/xml"),body))
				.build());
	}
	
	//纯文本消息
	String getText(){
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"  <address>"+TEL_URI+"</address>\r\n" + 
				"  <destinationAddress>"+TEL_URI+"</destinationAddress>\r\n" + 
				"  <senderAddress>"+getchatbotSip()+"</senderAddress>\r\n" + 
				"  <outboundIMMessage>\r\n" + 
				"<contentType> text/plain</contentType>\r\n" + 
				"<conversationID>"+UUID.randomUUID()+"</conversationID>\r\n" + 
				"<contributionID>"+UUID.randomUUID()+"</contributionID>\r\n" + 
				"<serviceCapability>\r\n" + 
				"<capabilityId>ChatbotSA</capabilityId>\r\n" + 
				"<version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				"</serviceCapability>\r\n" + 
				"<bodyText>Hello，中国移动！</bodyText>\r\n" + 
				"</outboundIMMessage>\r\n" + 
				"<clientCorrelator>567895</clientCorrelator>\r\n" + 
				"</msg:outboundMessageRequest>\r\n";
		return body;
	}
	
	String getDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}
	
	String request(Request request) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(request);
		System.out.println();
		System.out.println("======================================================================");
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();
			
			System.out.println("response header  ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			Headers headers = response.headers();
			for(int i = 0; i < headers.size(); i++) {
				String name = headers.name(i);
				String value = headers.get(name);
				
				System.out.println(name + " : " + value);
				
			}
			System.out.println();
			
			System.out.println("response body ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			String message = response.body().string();
			System.out.println(message);
			System.out.println();
			System.out.println("=====================================");
			return message;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//随机生成n位字符串
	public static String getRandomString(int n){
	    //定义一个字符串（A-Z，a-z，0-9）即62位；
	    String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
	 
        Random random=new Random(); 
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < n; i++){
        	sb.append(str.charAt(random.nextInt(62)));
        }
        
        return sb.toString();
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
	
	/**  
	 * SHA256加密   
	 */
	public static String getSHA256StrJava(String str) {

        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
            	//1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
    
    /**  
	 * Base64解密  
	 */
	public static String decodeStr(String pwd) {
	    Base64.Decoder decoder = Base64.getDecoder();
	    byte[] bytes = decoder.decode(pwd);
	    return new String(bytes);
	}
	 
	/**  
	 * Base64加密  
	 */
	public static String encodeStr(String pwd) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(pwd.getBytes());
	}

}
