package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.CloseUtil;
import util.DateUtil;

public class TestBjdx{
	protected String pre = "beijingDxChatbot-";
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
	protected String getAccessTokenUrl;
	protected String sendMessageUrl;
	protected String revokeUrl;
	protected String uploadFileUrl;
	protected String downloadUrl;
	protected String deleteUrl;
	
	protected String accessToken;
	
	protected String getchatbotSip() {
		return "sip:" + this.chatbotId + "@botplatform.rcs.vnet.cn";
	}
	
	protected String headerDate = DateUtil.getHttpHeaderDate();;// Tue, 15 Nov 2019 08:12:31 GMT
	
	protected String chatbotId;
	protected String appId;
	protected String appkey;
	
	public void init() {
		this.getAccessTokenUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/accessToken";
		this.sendMessageUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/messages";
		this.revokeUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/revoke";
		this.uploadFileUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/upload";
		this.downloadUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/download";
		this.deleteUrl = serverRoot + "/bot/v1/" + getchatbotSip() + "/medias/delete";
	}
	
	public TestBjdx(String chatbotId,String appid,String appkey) {
		this.chatbotId = chatbotId;
		this.appId = appid;
		this.appkey = appkey;
	}
	
	protected String phone = "tel:+8615330759941";
	public static void main(String[] args) {
		//集微科技
		String chatbotId = "106598858810000006";
		String appId = "xSnuH12M";
		String appkey = "aa6420f8dd9a812665b0e06dc36e0ebcd3614b24";

		TestBjdx t = new TestBjdx(chatbotId,appId,appkey);
		t.serverRoot = "https://maaptest.189.cn/maap_message";
		t.init();
//		t.accessToken = "a7d256eab9cf45a8be92972f6d78cf96";
        t.getAccessToken();
        //上传文件
//        t.uploadFile("file/image2.png");
        //缩略图
//        t.uploadFile("file/thumbnail2.jpg");
        //下载文件
//        t.downloadFile("http://124.126.120.102/temp/src/2021012515/29c00/view/36,3c3504f9382c90824e05.png");
        //删除文件
//        t.deleteFile("http://124.126.120.102/temp/src/2021012515/29c00/view/36,3c3504f9382c90824e05.png");
        //下发文本消息
//        t.requestText();
        
        //3、文件消息 
//		t.sendFile();
        //5、富媒体单卡片消息(带 CSS 样式) + Suggestions 
//        t.sendSigleCard();
        //6、富媒体多卡片消息(带 CSS 样式) + Suggestions 
		t.sendMultCard();
	}
	
	String getDuokp() {
		String s = "{\r\n" + 
				"  \"contributionId\": \"134dbb7a-32c7-4813-80a3-b9597e3f9a27\",\r\n" + 
				"  \"conversationId\": \"7c000ce6-4206-4111-b186-7c0756fb80c9\",\r\n" + 
				"  \"destinationAddress\": [\r\n" + 
				"    \"tel:+8615330759941\"\r\n" + 
				"  ],\r\n" + 
				"  \"messageId\": \"426123a32-6391-4395-a5ef-d325506155ed\",\r\n" + 
				"  \"messageList\": [\r\n" + 
				"    {\r\n" + 
				"      \"contentText\": {\r\n" + 
				"        \"message\": {\r\n" + 
				"          \"generalPurposeCardCarousel\": {\r\n" + 
				"            \"content\": [\r\n" + 
				"              {\r\n" + 
				"                \"description\": \"电信5G多卡片消息!\",\r\n" + 
				"                \"media\": {\r\n" + 
				"                  \"height\": \"SHORT_HEIGHT\",\r\n" + 
				"                  \"mediaContentType\": \"image/png\",\r\n" + 
				"                  \"mediaFileSize\": 178887,\r\n" + 
				"                  \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                  \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                  \"thumbnailFileSize\": 178887,\r\n" + 
				"                  \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"                },\r\n" + 
				"                \"suggestions\": [\r\n" + 
				"                  {\r\n" + 
				"                    \"reply\": {\r\n" + 
				"                      \"displayText\": \"建议回复\",\r\n" + 
				"                      \"postback\": {\r\n" + 
				"                        \"data\": \"建议回复\"\r\n" + 
				"                      }\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                ],\r\n" + 
				"                \"title\": \"5G视频彩铃 \"\r\n" + 
				"              },\r\n" + 
				"              {\r\n" + 
				"                \"description\": \"电信5G多卡片消息！\",\r\n" + 
				"                \"media\": {\r\n" + 
				"                  \"height\": \"SHORT_HEIGHT\",\r\n" + 
				"                  \"mediaContentType\": \"image/png\",\r\n" + 
				"                  \"mediaFileSize\": 178887,\r\n" + 
				"                  \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                  \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                  \"thumbnailFileSize\": 178887,\r\n" + 
				"                  \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\"\r\n" + 
				"                },\r\n" + 
				"                \"suggestions\": [\r\n" + 
				"                  {\r\n" + 
				"                    \"reply\": {\r\n" + 
				"                      \"displayText\": \"建议回复\",\r\n" + 
				"                      \"postback\": {\r\n" + 
				"                        \"data\": \"建议回复\"\r\n" + 
				"                      }\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                ],\r\n" + 
				"                \"title\": \"电信5G多卡片消息!\"\r\n" + 
				"              }\r\n" + 
				"            ],\r\n" + 
				"            \"layout\": {\r\n" + 
				"              \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
				"            }\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      },\r\n" + 
				"      \"contentType\": \"application/vnd.gsma.botmessage.v1.0+json\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"reportRequest\": [\r\n" + 
				"    \"sent\",\r\n" + 
				"    \"failed\",\r\n" + 
				"    \"delivered\",\r\n" + 
				"    \"displayed\",\r\n" + 
				"    \"deliveredToNetwork\"\r\n" + 
				"  ],\r\n" + 
				"  \"senderAddress\": \"sip:106598858810000006@botplatform.rcs.vnet.cn\",\r\n" + 
				"  \"serviceCapability\": [\r\n" + 
				"    {\r\n" + 
				"      \"capabilityId\": \"ChatbotSA\",\r\n" + 
				"      \"version\": \"+g.gsma.rcs.botversion=\\\"#=1\\\"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"smsSupported\": false,\r\n" + 
				"  \"storeSupported\": false\r\n" + 
				"}";
		return s;
	}
	
	String uploadFile(String filePath) {
		System.out.println("上传素材文件：");
		String url = uploadFileUrl;
		
		File file = new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(file,MediaType.parse("*/*")));
        
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("accessToken", this.accessToken)
				.addHeader("accessToken", "accessToken " +this.accessToken)
				.addHeader("uploadMode", "perm") //perm:永久文件 temp:临时文件
				.addHeader("content-type", "multipart/form-data")
				.url(url)
				.post(builder.build())
				.build(),"uploadFile");
	}
	
	String getAccessToken() {
		if(null == this.accessToken || this.accessToken.length() == 0) {
			String accessToken = readFile("accessToken/bjdx-chatbot");
			if(null != accessToken && accessToken.trim().length() > 0) {
				this.accessToken = accessToken.trim();
			}else {
				System.out.println("获取accessToken：");
				String url = getAccessTokenUrl;
				
				Map<String,String> requestBodyMap = new HashMap<>();
				requestBodyMap.put("appId", appId);
				requestBodyMap.put("appKey", appkey);
				String bodyStr = JSONObject.toJSONString(requestBodyMap);
				
				String response = request(new Request.Builder()
						.addHeader("content-type", "application/json")
						.addHeader("accept", "application/json")
						.addHeader("date", headerDate)
						.addHeader("host", "maaptest.189.cn")
						.addHeader("content-length", bodyStr.getBytes().length + "")
						.url(url)
						.post(RequestBody.create(bodyStr.getBytes(),MediaType.parse("application/json")))
						.build(),"beijingDxChatbot-getAccessToken");
				JSONObject jo = JSON.parseObject(response);
				accessToken = jo.getString("accessToken");
				writeAccessTokenFile("bjdx-chatbot",accessToken);
				this.accessToken = accessToken;
			}
		}
		return this.accessToken;
	}
	
	String request(Request request,String filePath) {
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
			appendFile(filePath, message);
			System.out.println(message);
			System.out.println();
			System.out.println("=====================================");
			return message;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
	
	void writeAccessTokenFile(String filename,String text) {
		if(null == text) {
			text = "";
		}
		File file = new File("accessToken/" + filename);
		wirteFile(file, text,false);
	}
	void appendFile(String filename,String text) {
		File file = new File("file/" + pre + filename);
		wirteFile(file, DateUtil.getDateTime() + " : " + text,true);
	}
	void wirteFile(File file,String text,boolean append) {
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
        	fos = new FileOutputStream(file,append);
        	osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(text); //写入内容
            osw.write("\r\n");  //换行
        }catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(osw);
			CloseUtil.close(fos);
		}
	}
	
	String readFile(String filePath) {
		File file = new File(filePath);
		while(!file.canRead()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			 int iAvail = is.available();
			 byte[] bytes = new byte[iAvail];
			 is.read(bytes);
			 return new String(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	String sendRevoke(String messageId) {
		System.out.println("富媒体多卡片消息(带 CSS 样式) + Suggestions ");
		String url = sendMessageUrl;
		
		Map<String,Object> map = new HashMap<>();
		map.put("messageId", messageId);
		map.put("status", "RevokeRequested");
		map.put("destinationAddress", Arrays.asList("tel:+86"+phone));
        
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build(),"sendRevoke");
	}
	
	String sendMultCard() {
		System.out.println("富媒体多卡片消息(带 CSS 样式) + Suggestions ");
		String url = sendMessageUrl;
		
		Map<String,Object> card1 = getSignleCard();
		Map<String,Object> card2 = getSignleCard();
		
		Map map = getJSON(card1,card2);
//        Strig body = JSON.toJSONString(map);
        
		String body = getDuokp();
		System.out.println("消息体：" + body);
		
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendSigleCard() {
		System.out.println("富媒体单卡片消息(带 CSS 样式) + Suggestions ：");
		String url = sendMessageUrl;
		
		Map<String,Object> card = getSignleCard();
		
		Map map = getJSON(card);
//        String body = JSON.toJSONString(map);
		String body = getDkp();
        System.out.println(body);
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("accessToken", this.accessToken)
				.addHeader("accessToken", "accessToken " +this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	} 
	
	
	String getDkp() {
		String s = "{\r\n" + 
				"  \"messageId\": \"cb1188a3-37ec-1037-9254-2ec66e44305b2\",\r\n" + 
				"  \"messageList\": [\r\n" + 
				"    {\r\n" + 
				"      \"contentType\": \"application/vnd.gsma.botmessage.v1.0+json\",\r\n" + 
				"      \"contentText\": {\r\n" + 
				"        \"message\": {\r\n" + 
				"          \"generalPurposeCard\": {\r\n" + 
				"            \"layout\": {\r\n" + 
				"              \"cardOrientation\": \"VERTICAL\",\r\n" + 
				"              \"titleFontStyle\": [\r\n" + 
				"                \"underline\",\r\n" + 
				"                \"bold\"\r\n" + 
				"              ],\r\n" + 
				"              \"descriptionFontStyle\": [\r\n" + 
				"                \"bold\"\r\n" + 
				"              ],\r\n" + 
				"              \"style\": \"http://example.com/default.css\"\r\n" + 
				"            },\r\n" + 
				"            \"content\": {\r\n" + 
				"              \"media\": {\r\n" + 
				"                \"mediaUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                \"mediaContentType\": \"image/jpg\",\r\n" + 
				"                \"mediaFileSize\": 178887,\r\n" + 
				"                \"thumbnailUrl\": \"https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg\",\r\n" + 
				"                \"thumbnailContentType\": \"image/png\",\r\n" + 
				"                \"thumbnailFileSize\": 178887,\r\n" + 
				"                \"height\": \"MEDIUM_HEIGHT\",\r\n" + 
				"                \"contentDescription\": \"Textual description of media content,  e.g. for use with screen readers.\"\r\n" + 
				"              },\r\n" + 
				"              \"title\": \"This is a single rich card.\",\r\n" + 
				"              \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\",\r\n" + 
				"              \"suggestions\": [\r\n" + 
				"                {\r\n" + 
				"                  \"reply\": {\r\n" + 
				"                    \"displayText\": \"点击回复\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_reply_no\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                  \"action\": {\r\n" + 
				"                    \"urlAction\": {\r\n" + 
				"                      \"openUrl\": {\r\n" + 
				"                        \"url\": \"www.baidu.com\",\r\n" + 
				"                        \"application\": \"webview\",\r\n" + 
				"                        \"viewMode\": \"half\"\r\n" + 
				"                      }\r\n" + 
				"                    },\r\n" + 
				"                    \"displayText\": \"百度一下\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_open_url\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                {\r\n" + 
				"                  \"action\": {\r\n" + 
				"                    \"dialerAction\": {\r\n" + 
				"                      \"dialPhoneNumber\": {\r\n" + 
				"                        \"phoneNumber\": \"+8615330759941\"\r\n" + 
				"                      }\r\n" + 
				"                    },\r\n" + 
				"                    \"displayText\": \"拨打号码\",\r\n" + 
				"                    \"postback\": {\r\n" + 
				"                      \"data\": \"set_by_chatbot_open_dialer\"\r\n" + 
				"                    }\r\n" + 
				"                  }\r\n" + 
				"                }\r\n" + 
				"              ]\r\n" + 
				"            }\r\n" + 
				"          }\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"trafficType\": \"advertisement\",\r\n" + 
				"  \"destinationAddress\": [\r\n" + 
				"    \"tel:+8615330759941\"\r\n" + 
				"  ],\r\n" + 
				"  \"senderAddress\": \"sip:106598858810000006@botplatform.rcs.vnet.cn\",\r\n" + 
				"  \"smsSupported\": false,\r\n" + 
				"  \"storeSupported\": false,\r\n" + 
				"  \"serviceCapability\": [\r\n" + 
				"    {\r\n" + 
				"      \"capabilityId\": \"ChatbotSA\",\r\n" + 
				"      \"version\": \"+g.gsma.rcs.botversion=\\\"#=1\\\"\"\r\n" + 
				"    }\r\n" + 
				"  ],\r\n" + 
				"  \"conversationId\": \"XSFDSFDFSAFDSAS^%\",\r\n" + 
				"  \"contributionId\": \"SFF$#REGFY7&^%THT\"\r\n" + 
				"}";
		return s;
	}
	
	String sendGeo() {
		System.out.println("下发回落地理位置 ：");
		String url = sendMessageUrl;
		
		Map<String,Object> text = getText("geo:50.7311865,7.0914591;crs=gcj02;u=10;rcsl=Qingfeng%20Steamed%20Dumpling%20Shop%20%F0%9F%8D%9A");
		
		Map map = getJSON(text);
        
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendFile() {
		System.out.println("下发文件消息 ：");
		String url = sendMessageUrl;
		
		Map<String,Object> file = getFile();
		
		Map map = getJSON(file);
		String body = JSON.toJSONString(map);
        System.out.println("消息体：" + body);
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(body,MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendSugText() {
		System.out.println("下发文本消息+ 悬浮菜单 ：");
		String url = sendMessageUrl;
		
		Map<String,Object> text = getText("你好chatbot,下行文本消息+ 悬浮菜单 ");
		Map<String,Object> reply1 = getReply("是的 ");
		Map<String,Object> reply2 = getReply("不是 ");
		Map<String,Object> urlAction = getUrlAction();
		Map<String,Object> dialerAction = getDialerAction();
		Map<String,Object> sugs = getSug(reply1,reply2,urlAction,dialerAction);
		
		Map map = getJSON(text,sugs);
        
		return request(new Request.Builder()
				.addHeader("Authorization", this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	String sendText() {
		System.out.println("下发文本消息：");
		String url = sendMessageUrl;
		
		Map<String,Object> map = getJSON(getText("你好chatbot,下行文本消息"));
        
		return request(new Request.Builder()
				.addHeader("Authorization", "accessToken " + this.accessToken)
				.addHeader("accessToken", this.accessToken)
				.addHeader("accessToken", "accessToken " +this.accessToken)
				.addHeader("Content-Type", "application/json")
				.addHeader("Date", this.headerDate)
				.url(url)
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build(),"sendText");
	}
	
	Map<String,Object> getSignleCard(){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType","application/vnd.gsma.botmessage.v1.0+json");
		Map<String,Object> contentText = new HashMap<String,Object>();
		Map<String,Object> message = new HashMap<String,Object>();
		Map<String,Object> generalPurposeCard = new HashMap<String,Object>();
		
		Map<String,Object> layout = new HashMap<String,Object>();
		layout.put("cardOrientation", "HORIZONTAL");
		layout.put("imageAlignment", "LEFT");
		layout.put("titleFontStyle", Arrays.asList("underline","bold"));
		layout.put("descriptionFontStyle", Arrays.asList("calibri"));
//		layout.put("style", "http://example.com/default.css");
		generalPurposeCard.put("layout", layout);
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
//		media.put("mediaUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210212111037/171008/12,489e21a8151d.png");
//		media.put("mediaContentType", "image/png");
//		media.put("mediaFileSize", "171008");
//		media.put("thumbnailUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/temp/20210212111228/9216/12,489f69eede8a.jpg");
//		media.put("thumbnailContentType", "image/jpg");
//		media.put("thumbnailFileSize", "9216");
		
		media.put("mediaUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
		media.put("mediaContentType", "image/jpg");
		media.put("mediaFileSize", "178887");
		media.put("thumbnailUrl", "https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
		media.put("thumbnailContentType", "image/png");
		media.put("thumbnailFileSize", "178887");
		
		media.put("height", "MEDIUM_HEIGHT");
		media.put("contentDescription", "媒体文件的描述信息");
		content.put("media", media);
		content.put("title", "这是一个但卡片消息");
		content.put("description", "这是单卡片消息的描述，如果超过了卡片的最大宽度和高度，描述内容会被省略");
		
		content.put("suggestions", Arrays.asList(getReply("确定"),getReply("取消"),getUrlAction(),getDialerAction()));
		generalPurposeCard.put("content", content);
		message.put("generalPurposeCard", generalPurposeCard);
		contentText.put("message", message);
		result.put("contentText", contentText);
		return result;
	}
	
	Map<String,Object> getFile(){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType","application/vnd.gsma.rcs-ft-http");
		result.put("contentEncoding","utf8");
		List<Map<String,Object>> contentText = new ArrayList<>();
		
		Map<String,Object> file = new HashMap<>();
		file.put("type","file");
		file.put("fileSize","178887");
		file.put("fileName","image2.jpg");
		file.put("contentType","image/jpg");
		file.put("url","https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
//		file.put("until","2021-02-12T11:10:37Z");
		contentText.add(file);
		
		Map<String,Object> thumbnail = new HashMap<>();
		thumbnail.put("type","thumbnail");
		thumbnail.put("fileSize","178887");
		thumbnail.put("fileName","imaget2.png");
		thumbnail.put("contentType","image/png");
		thumbnail.put("url","https://maaptest.189.cn/maap_message/bot/chanageUrl/perm/20201219104624/178887/4,04a014d5db9b.jpg");
//		thumbnail.put("until","2021-02-12T11:12:28Z");
		contentText.add(thumbnail);
		result.put("contentText", contentText);
		
		return result;
	}
	
	Map<String,Object> getSug(Map<String,Object>... sugs){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType", "application/vnd.gsma.botsuggestion.v1.0+json");
		Map<String,Object> contentText = new HashMap<>();
		contentText.put("suggestions", sugs);
		result.put("contentText",contentText);
		return result;
	}
	
	Map<String,Object> getDialerAction(){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> action = new HashMap<>();
		action.put("displayText", "拨打电话");
		Map<String,String> data = new HashMap<>();
		data.put("data", "拨打电话data");
		action.put("postback", data);
		Map<String,Object> dialerAction = new HashMap<>();
		Map<String,Object> dialPhoneNumber = new HashMap<>();
		dialPhoneNumber.put("phoneNumber","+8613211112222");
		dialerAction.put("openUrl", dialPhoneNumber);
		result.put("action", action);
		return result;
	}
	
	Map<String,Object> getUrlAction(){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> action = new HashMap<>();
		action.put("displayText", "打开百度");
		Map<String,String> data = new HashMap<>();
		data.put("data", "打开百度data");
		action.put("postback", data);
		Map<String,Object> urlAction = new HashMap<>();
		Map<String,Object> openUrl = new HashMap<>();
		openUrl.put("openUrl","http://www.baidu.com");
		urlAction.put("openUrl", openUrl);
		result.put("action", action);
		return result;
	}
	
	Map<String,Object> getReply(String text){
		Map<String,Object> result = new HashMap<>();
		Map<String,Object> replay = new HashMap<>();
		replay.put("displayText", text);
		Map<String,String> data = new HashMap<>();
		data.put("data", text + "data");
		replay.put("postback", data);
		result.put("reply", replay);
		return result;
	}
	
	protected Map<String,Object> getText(String text){
		Map<String,Object> result = new HashMap<>();
		result.put("contentType", "text/plain");
		result.put("contentEncoding", "utf8");
		result.put("contentText", text);
		return result;
	}
	
	protected Map<String,Object> getJSON(Map<String,Object>... message){
		Map<String,Object> map = new HashMap<>();
		map.put("messageId", UUID.randomUUID().toString());//消息ID
		
		map.put("messageList", message);//消息列表。其携带的数据类型是由contentType, contentEncoding和contentText组成的对象详情请见消息内容结构体
		
		map.put("destinationAddress", new String[] {phone});//用户URI list， tel格式
		map.put("senderAddress", getchatbotSip());//发送方地址From，群发时填写Chatbot的URI，广播时填写Chatbot的URI（暂不ᨀ供）
		map.put("conversationId", UUID.randomUUID().toString());//唯一标识主被叫用户间的一个聊天对话
		map.put("contributionId", UUID.randomUUID().toString());//唯一标识一个聊天会话
		
		Map<String,String> serviceCapability = new HashMap<>();
		serviceCapability.put("capabilityId", "ChatbotSA");
		serviceCapability.put("version", "+g.gsma.rcs.botversion=\"#=1\"");
		map.put("serviceCapability", new Map[] {serviceCapability});//业务能力，Chatbot版本号。说明见下表：ServiceCapability关键字段
//		流量类型，可取值为
//		advertisement
//		payment
//		premium
//		subscription
//		token（token可用于类型扩展）
//		map.put("trafficType", "");
		map.put("smsSupported", false);//是否转短信。false:不转，true:转，缺省false
//		map.put("imFormat", "");//IM 消息格式，可选值包括 IM LargerMode、PagerMode，默认值为IM
//		map.put("inReplyTo", "");//该标识是对另一条消息的回复，值是一条上行消息的contributionId。
//		状态事件报告列表，每个状态事件的可选值为:
//		消息状态，主要有如下几种状态：
//		sent：消息已发送
//		failed：消息发送失败
//		delivered：消息已送达
//		displayed：消息已阅读
//		deliveredToNetwork：已转短消息已送达
		map.put("reportRequest", new String[] {"sent","delivered","displayed","failed"});
		map.put("storeSupported", true);//是否离线存储。false:不存也不重试，true:存，缺省true
//		map.put("smsContent", "");//smsContent为消息回落时的消息内容，smsSupported为true时，本字段有效且不能为空。
		return map;
	}
}
