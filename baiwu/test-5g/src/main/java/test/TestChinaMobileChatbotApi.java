package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 福州移动chatbot接口
 * @date 2020/07/01
 */
public class TestChinaMobileChatbotApi {
	
	//福州移动
	private String serverRoot = "http://112.35.162.232:8078/mbmp/developer/accesslayer";
	private String apiVersion = "v1"; 
	private String fileserverroot = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer"; 
	private String notifyURL= "http://124.239.146.131:9090/rcs/api/fjdy5g/callback";
	
	//移动北向
//	private String serverRoot = "http://112.35.162.232:8078/mbmp/developer/officialcsp";
//	private String apiVersion = "v1"; 
//	private String fileserverroot = "http://112.35.162.232:8078/mbmp/fileservice/officialcsp"; 
//	private String notifyURL= "http://124.239.146.131:9090/rcs/api/fjdy5g/callback";
	  
	//集微科技
//	private String chatbotId = "sip:125200401111382@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111382"; 
//	private String appKey = "125200401111382"; 
//	private static String password = "xH837(h2P0Q*rZ5U";
	
	//和选账号  
//	private String chatbotId = "sip:125200401111383@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111383"; 
//	private String appKey = "125200401111383"; 
//	private static String password = "~5#)&a.4x-nGM\"";
	
//	//宝姿PORTS
//	private String chatbotId = "sip:125200401111507@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111507";
//	private String appKey = "125200401111507";
//	private static String password = "49%L1Q85A0;MlrULy\\";
	
	//微众银行
//	private String chatbotId = "sip:125200401111508@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111508";
//	private String appKey = "125200401111508";
//	private static String password = "gSg074i/Dko628o?P43";
	
	//顺丰金融
//	private String chatbotId = "sip:125200401111510@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111510";
//	private String appKey = "125200401111510";
//	private static String password = "KxK7wm8<wvXF*IV:2x";
	
	//顺丰同城急送
//	private String chatbotId = "sip:125200401111511@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111511";
//	private String appKey = "125200401111511";
//	private static String password = "E8zb30[9|)PG,n";
	
	//马上消费金融
//	private String chatbotId = "sip:125200401111512@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111512";
//	private String appKey = "125200401111512";
//	private static String password = "x$7MozKX4Wp[y/F,";
	
	//杭银消金
//	private String chatbotId = "sip:125200401111513@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111513";
//	private String appKey = "125200401111513";
//	private static String password = "uD0qRw*6%6Fu2<8=(";
	  
	//百信银行
//	private String chatbotId = "sip:125200401111514@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111514";
//	private String appKey = "125200401111514";
//	private static String password = "52CzPp$X\\^k>YEcF>";
	
	//网上国网
//	private String chatbotId = "sip:125200401111515@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111515";
//	private String appKey = "125200401111515";
//	private static String password = "J+rAj)];2?6Of";
	
	//网银在线
//	private String chatbotId = "sip:125200401111516@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111516";
//	private String appKey = "125200401111516";
//	private static String password = "G7\+!*@w(:x5J1";
	
	//京东钱包
//	private String chatbotId = "sip:125200401111517@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111517";
//	private String appKey = "125200401111517";
//	private static String password = "LjUl&-I"2K93vL";
	
	//懂车帝卖车通
//	private String chatbotId = "sip:125200401111518@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111518";
//	private String appKey = "125200401111518";
//	private static String password = "XKu9pd/F>2~jl@5v;";
	
	//徽商银行
//	private String chatbotId = "sip:125200401111519@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111519";
//	private String appKey = "125200401111519";
//	private static String password = "M3dsRQ$^WW";
	
	//华泰证券
//	private String chatbotId = "sip:125200401111520@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111520";
//	private String appKey = "125200401111520";
//	private static String password = "Tab,=db47";
	
	//抖音火山版
//	private String chatbotId = "sip:125200401111521@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111521";
//	private String appKey = "125200401111521";
//	private static String password = "iD;(TCm5w";
	
	//国信证券
//	private String chatbotId = "sip:125200401111522@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111522";
//	private String appKey = "125200401111522";
//	private static String password = "r3`M5{4w0";
	
	//京东物流
//	private String chatbotId = "sip:125200401111523@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111523";
//	private String appKey = "125200401111523";
//	private static String password = "M<m9O3V9";
	
	//京东快递柜
//	private String chatbotId = "sip:125200401111524@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111524";
//	private String appKey = "125200401111524";
//	private static String password = "EcxWq&d6:";
	
	//好慷在家
//	private String chatbotId = "sip:125200401111525@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111525";
//	private String appKey = "125200401111525";
//	private static String password = "F!cGGYF4Wt0,`oS563TR";
	  
	//中通快递
//	private String chatbotId = "sip:125200401111707@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111707";
//	private String appKey = "125200401111707";
//	private static String password = "7p.~W2h1fq9qj)b(5sp]";
	
	//长龙航空
//	private String chatbotId = "sip:125200401111708@botplatform.rcs.chinamobile.com";
//	private static String appId = "125200401111708";
//	private String appKey = "125200401111708";
//	private static String password = "bX29AoXq8@:{I";
	
	//腾讯账号  
//	private String chatbotId = "sip:125200401111053@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111053"; 
//	private String appKey = "125200401111053"; 
//	private static String password = "1Ab7LP4aIQC@xMmDU5rA";
	
	//中飞艾维5G消息 
	private String chatbotId = "sip:125200401111833@botplatform.rcs.chinamobile.com"; 
	private static String appId = "125200401111833"; 
	private String appKey = "125200401111833"; 
	private static String password = "lxB;6O(2ST|J~3b`qw";
	
	//光鉴科技5G消息
//	private String chatbotId = "sip:125200401111834@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111834"; 
//	private String appKey = "125200401111834"; 
//	private static String password = "vnEOXyd|(5$XPr";
	
	//清锋时代5G消息
//	private String chatbotId = "sip:125200401111835@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111835"; 
//	private String appKey = "125200401111835"; 
//	private static String password = "nF7#b<7Bk4iG/L]2";
	
	//踏歌智行5G消息
//	private String chatbotId = "sip:125200401111836@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111836"; 
//	private String appKey = "125200401111836"; 
//	private static String password = "!H1?2m5P";
	
	//易诚高科5G消息
//	private String chatbotId = "sip:125200401111837@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111837"; 
//	private String appKey = "125200401111837"; 
//	private static String password = "(ec_5v@S9EQ87";
	
	//标贝科技5G消息
//	private String chatbotId = "sip:125200401111838@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111838"; 
//	private String appKey = "125200401111838"; 
//	private static String password = "N;SnhlE6t1D9A#\"j3";
	
	//观脉科技5G消息
//	private String chatbotId = "sip:125200401111839@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111839"; 
//	private String appKey = "125200401111839"; 
//	private static String password = "4kP1<0Oq50X3,#_fB71h";
	
	//海马云5G消息
//	private String chatbotId = "sip:125200401111840@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111840"; 
//	private String appKey = "125200401111840"; 
//	private static String password = "e#k%b38T9<2b";
	
	//镁伽机器人5G消息
//	private String chatbotId = "sip:125200401111841@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111841"; 
//	private String appKey = "125200401111841"; 
//	private static String password = "3:=8u53Te&27";
	
	//梯影传媒5G消息
//	private String chatbotId = "sip:125200401111842@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111842"; 
//	private String appKey = "125200401111842"; 
//	private static String password = ".]Ky3Fe4;7Z%cL1f\"\\";
	
	//卓视智通5G消息
//	private String chatbotId = "sip:125200401111843@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111843"; 
//	private String appKey = "125200401111843"; 
//	private static String password = "<i3>Kj9>lL\\0I1";
	
	//为快科技5G消息
//	private String chatbotId = "sip:125200401111844@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111844"; 
//	private String appKey = "125200401111844"; 
//	private static String password = "0OyHdU>@,\"$o";
	
	//歌尔创客5G消息
//	private String chatbotId = "sip:125200401111845@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111845"; 
//	private String appKey = "125200401111845"; 
//	private static String password = "63>z4G6Y_pck5vB1";
	
	//百度云
//	private String chatbotId = "sip:125200401111802@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111802"; 
//	private String appKey = "125200401111802"; 
//	private static String password = "k%U$`3EwdIMv48";
	
	//民生易贷
//	private String chatbotId = "sip:125200401111803@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111803"; 
//	private String appKey = "125200401111803"; 
//	private static String password = "k|5*fa7~x364D`,";
	
	//桔多多
//	private String chatbotId = "sip:125200401111804@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111804"; 
//	private String appKey = "125200401111804"; 
//	private static String password = "F#M7@7m;y7)+fgQ*34-";
	
	//享停车
//	private String chatbotId = "sip:125200401111805@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111805"; 
//	private String appKey = "125200401111805"; 
//	private static String password = "|3q8M(=3P?%Ho6";
	
	//君龙人寿
//	private String chatbotId = "sip:125200401111806@botplatform.rcs.chinamobile.com"; 
//	private static String appId = "125200401111806"; 
//	private String appKey = "125200401111806"; 
//	private static String password = "KaM\"7TuO";

	/**
	 *创建请求
	 *
	 *@return
	 */
	private RestTemplate getRestTemplate() {
//		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//		requestFactory.setConnectTimeout(10000);// 设置超时
//		requestFactory.setReadTimeout(10000);
//		return new RestTemplate(requestFactory);
		 ConnectionPool pool = new ConnectionPool(30, 10, TimeUnit.MINUTES);
	        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
	                .readTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
	                .writeTimeout(5 * 60 * 1000, TimeUnit.MILLISECONDS)
	                .connectionPool(pool)
	                .retryOnConnectionFailure(false)
	                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
//	                .addInterceptor(new RetryIntercepter(2)) //重试拦截器2次
//	                .addNetworkInterceptor(new NetworkIntercepter()) //网络拦截器，统一打印日志
	                .build();
	        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
	        RestTemplate restTemplate = new RestTemplate(factory);
	        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	        return restTemplate;
	}
	
	public static void main2(String[] args) {
		TestChinaMobileChatbotApi manager = new TestChinaMobileChatbotApi();
		String mobile = "15811491455";
		TestTxbjydChatbot.getChatbots().forEach(x -> {
			try {
				String token = manager.getAccessToken(x.password);

//				if("125200401111835".equals(x.appId)) {
//					String date = "Tue, 29 Dec 2020 16:43:50 GMT";
//					String authorization = manager.getAuthentication(x.appId,token,date);
//					System.out.println(authorization);
//				}
				
				manager.testSendGroupTextMessage(x.appId,token,mobile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		TestChinaMobileChatbotApi manager = new TestChinaMobileChatbotApi();
//		String token = manager.getAccessToken("123456");
//		String authorization = encodeStr("1001"+":"+getSHA256StrJava(token+"Thu, 11 Jul 2015 15:33:24 GMT")).replaceAll("\r\n", "");
//		System.out.println(authorization);
//		System.out.println("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92");
//		System.out.println("MTAwMTo0ZWQzZmVlY2U2N2I0MTgwYzgwZWUxYTg1ZDE0ZTI1YjhmODEyZDI0NTJlZGYyMzVlOTQ2NDhkNTkwZTU1YjRj".equals(authorization));
		String token = manager.getAccessToken(password);
		//上传文件
		//图片文件上传
		File file1 = new File("file/image1.jpg");
		File thumbnail1 = new File("file/image1-thumbnail.png");
		manager.uploadFile(token,file1,thumbnail1);
		//音频文件上传
//		File file2 = new File("file/audio1.wav");
//		File thumbnail2 = new File("file/audio1-thumbnail.png");
//		manager.uploadFile(token,file2,thumbnail2);
		//视频文件上传
//		File file3 = new File("file/video1.mp4");
//		File thumbnail3 = new File("file/video1-thumbnail.png");
//		manager.uploadFile(token,file3,thumbnail3);
		String mobile = "15811491455";
		
//		String mobile = "15132291613";
		//群发文本消息
//		manager.testSendGroupTextMessage(appId,token,mobile);
		//群发带悬浮菜单的文本消息
//		manager.testSendGroupTextSuggestionsMessage(token,mobile);
		//群发文件消息
//		manager.testSendGroupFileMessage(token,mobile);
		//群发带悬浮菜单的文件消息
//		manager.testSendGroupFileSuggestionsMessage(token,mobile);
		//群发单卡片消息
//		manager.testSendGroupSingleCardMessage(token,mobile);
		//群发带悬浮菜单的单卡片消息
//		manager.testSendGroupSingleCardSuggestionsMessage(token,mobile);
		//群发多卡片消息
//		manager.testSendGroupMultipleCardMessage(token,mobile);
		//群发带悬浮菜单的多卡片消息
//		manager.testSendGroupMultipleCardSuggestionsMessage(token,mobile);
	}
	
	/**  
	 * 得到时间的HTTP-GMT格式字符串: (Thu, 11 Jul 2015 15:33:24 GMT) 
	 */
	public static String long2gmt(long time){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US); 
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); 
		String timeStr = sdf.format(time);
		return timeStr;
	}
	
	/**  
	 * Base64解密  
	 */
	@SuppressWarnings("static-access")
	public static String decodeStr(String pwd) {
		Base64.Decoder decoder = Base64.getDecoder();
	    byte[] debytes = decoder.decode(pwd.getBytes());
	    return new String(debytes);
	}
	 
	/**  
	 * Base64加密  
	 */
	@SuppressWarnings("static-access")
	public static String encodeStr(String pwd) {
	    Base64.Encoder encoder = Base64.getEncoder();
	    return encoder.encodeToString(pwd.getBytes());
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
	 * 5.1  获取accessToken 有效期为 7200秒
	 * @return
	 * @throws Exception 
	 */
	public String getAccessToken(String password) throws Exception {
		// http://{serverRoot}/bot/{apiVersion}/{chatbotId}/get/accessToken
		/*
		 * StringBuilder builder = new StringBuilder();
		 * builder.append(serverRoot).append("/").append("bot").append("/").append(
		 * apiVersion).append("/") .append(chatbotId).append("/").append("accessToken");
		 * 
		 * String url = builder.toString();
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * headers.setContentType(MediaType.APPLICATION_JSON); List<MediaType> list =
		 * new ArrayList<MediaType>(); list.add(MediaType.APPLICATION_JSON);
		 * headers.setAccept(list); InetSocketAddress inetSocketAddress = new
		 * InetSocketAddress(9090); headers.setHost(inetSocketAddress);
		 * headers.setDate(System.currentTimeMillis()); JSONObject jsonObject = new
		 * JSONObject(); jsonObject.put("appId",appId); jsonObject.put("appKey",appKey);
		 * jsonObject.toJSONString();
		 * headers.setContentLength(jsonObject.toJSONString().length());
		 * 
		 * HttpEntity<String> request = new
		 * HttpEntity<String>(jsonObject.toJSONString(), headers);
		 * 
		 * RestTemplate restTemplate = getRestTemplate();
		 * 
		 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
		 * request, String.class);
		 * 
		 * System.out.println(response.getStatusCodeValue());
		 * 
		 * System.out.println(response.getHeaders());
		 * 
		 * System.out.println(response.getBody());
		 * 
		 * JSONObject json = JSONObject.parseObject(response.getBody()); return
		 * json.getString("accessToken");
		 */
		String token = getSHA256StrJava(password);
		return token;
	}
	
	/**
	 * 5.2  身份鉴权
	 * @return
	 * @throws Exception 
	 */
	public String getAuthentication(String token,String date) throws Exception {
		// https://{notifyURL}/notifyPath
		/*
		 * StringBuilder builder = new StringBuilder();
		 * builder.append(notifyURL).append("/").append("notifyPath"); long timestamp =
		 * System.currentTimeMillis(); String nonce = UUID.randomUUID().toString();
		 * String signature = SHACoder.encodeSHA256Hex(token + timestamp + nonce);
		 * String echoStr = UUID.randomUUID().toString().replace("-", "");
		 * 
		 * String url = builder.toString();
		 * 
		 * HttpHeaders headers = new HttpHeaders(); headers.add("signature", signature);
		 * headers.add("timestamp", String.valueOf(timestamp)); headers.add("nonce",
		 * nonce); headers.add("echoStr", echoStr); HttpEntity<String> request = new
		 * HttpEntity<String>("", headers);
		 * 
		 * RestTemplate restTemplate = getRestTemplate();
		 * 
		 * ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,
		 * request, String.class);
		 * 
		 * System.out.println(response.getStatusCodeValue());
		 * 
		 * System.out.println(response.getHeaders());
		 * 
		 * System.out.println(response.getBody());
		 * 
		 * List<String> list = response.getHeaders().get("appId"); return list.get(0);
		 */
		String authorization = encodeStr(appId+":"+getSHA256StrJava(token+date)).replaceAll("\r\n", "");
		System.out.println("Basic "+authorization);
		return authorization;
	}
	
	public String getAuthentication(String appId,String token,String date) throws Exception {
		String authorization = encodeStr(appId+":"+getSHA256StrJava(token+date)).replaceAll("\r\n", "");
		System.out.println("Basic "+authorization);
		return authorization;
	}
	
	/**
	 * 6.1.1 chatbot基本信息
	 * @return
	 * @throws Exception 
	 */
	public String getChatbotInfo(String token) throws Exception {
		return "";
	}
	
	/**
	 * 6.1.2 chatbot静态菜单
	 * @return
	 * @throws Exception 
	 */
	public String getChatbotInfoMenu(String token) throws Exception {
		return "";
	}
	
	/**
	 * 6.2 chatbot信息获取
	 * @return
	 * @throws Exception 
	 */
	public String getChatBotInfo(String token) throws Exception {
		return "";
	}
	
	//Chatbot 可调用本接口 将图片、音频 、视频等文件 提前上传到5G消息业务平台。 支持发送图片、音频 、视素材 和缩略图 。文件大小要求如下： 
	//图片（ image）: 2M，支持 JPG/JPEG 、PEG格式；
	//音频 （audio）： 5M，播放时长不超过 90s，AMR、MP3 、M4A格式 ；
	//视频（ video）： 10M，播放时长不超过60s，MP4 、WEBM格式 ；
	//缩略图（ 缩略图（ thumbnail）： 200KB，支持JPG/JPEG、PEG格式 。

	/**
	 * 根据文件名判断是否支持图片
	 * 
	 * @param file
	 * @param isThumb 是否是缩略图
	 * @return
	 * @throws Exception
	 */
	private String getFileType(File file, boolean isThumb) throws Exception {

		String tmpFile = file.getName().toLowerCase();

		if (tmpFile.endsWith(".txt")) {

			if (file.length() > 10 * 1024 * 1024) {
				throw new Exception("file size is too big not support , limit is 10M!");
			}

			return "1";
		}

		if ((tmpFile.endsWith(".jpg") || tmpFile.endsWith(".png")) && isThumb == false) {
			if (file.length() > 2 * 1024 * 1024) {
				throw new Exception("file size is too big not support , limit is 2M!");
			}
			return "2";
		}

		if (tmpFile.endsWith(".amr")) {
			if (file.length() > 5 * 1024 * 1024) {
				throw new Exception("file size is too big not support , limit is 5M!");
			}
			return "3";
		}

		if (tmpFile.endsWith(".mp4")) {
			if (file.length() > 10 * 1024 * 1024) {
				throw new Exception("file size is too big not support , limit is 10M!");
			}
			return "4";
		}
		if (tmpFile.endsWith(".jpg") || tmpFile.endsWith(".png")) {
			if (file.length() > 10 * 1024) {
				throw new Exception("file size is too big not support , limit is 10K!");
			}
			return "5";
		}
		throw new Exception("not support media type!");
	}

	 /**
	 * 7.1  媒体素材上传接口
	 * 
	 * @param token 令牌
	 * @param isTmp 是否为临时的文件，临时文件系统保存3天后自动删除
	 * @param file  要上传的文件
	 * @return 上传成功后的媒体素材id
	 * @throws Exception
	 */
	
	  public String uploadFile(String token, File file, File thumbnail) throws Exception {
		  //http://{fileServerRoot}/Content?hash=sha256<sha256_value>&File-name=<file name>&File-size=<file size>
		  StringBuilder builder = new StringBuilder();
		  builder
		  .append(fileserverroot).append("/")
		  .append("Content");
//		  .append(GetFileSHA256.getFileSHA1(file));
		  FileSystemResource File = new FileSystemResource(file);
		  FileSystemResource Thumbnail = new FileSystemResource(thumbnail);
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.add("Date", date);
		  headers.add("X-3GPP-Intended-Identity", chatbotId);
		  
		  headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		  headers.add("User-Agent", "SP/"+chatbotId);
		  headers.add("Terminal-type", "Chatbot");
		  MultiValueMap<String, Object> map= new LinkedMultiValueMap<String, Object>();
		  map.add("Thumbnail",Thumbnail);
		  map.add("File",File);
		  headers.setContentLength(map.toString().getBytes("UTF-8").length);
		  HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		  
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody()); 
		  JSONObject json = JSONObject.parseObject(response.getBody());
		  System.out.println(json);
		  String cdoe = json.getString("cdoe");
		  String msg = json.getString("msg");
		  return "code:"+cdoe+",msg:"+msg; 
	  }
	  
	  
	  
	/**
	 * 7.2 素材文件下载接口
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	
	  public String downloadFile(String token) throws Exception {
		  //https://{serverRoot}/bot/{apiVersion}/{chatbotId}/medias/download
		  
//		  String fileUrl = uploadFile(token, true , new File("F:/rcs/baidu.jpg"));
		  String fileUrl = "http://124.126.120.102/perm/src/2020071718/b5e/view/10,3c3504f70ff5ba5fe13e.jpg";
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("bot") 
		  .append("/").append(apiVersion)
		  .append("/").append(chatbotId)
		  .append("/").append("medias")
		  .append("/").append("download");
		  
		  String url = builder.toString();
		  
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "accessToken "+token);
		  headers.add("url", fileUrl);
		  InetSocketAddress inetSocketAddress = new InetSocketAddress(9090);
		  headers.setHost(inetSocketAddress);
		  headers.setDate(System.currentTimeMillis());
		  
		  HttpEntity<String> request = new HttpEntity<String>("", headers);
		  
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
		  
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  File tmp = File.createTempFile("download", ".jpg", new File("F:/rcs"));
		  
		  StreamUtils.copy(response.getBody(), new FileOutputStream(tmp));
		  
		  return tmp.getAbsolutePath(); 
	  }
	  
	  
	  
	/**
	 * 7.3 删除素材文件接口
	 * 
	 * @param token
	 * @return 返回删除结果
	 * @throws Exception
	 */
	
	  public String deleteUploadFile(String token) throws Exception {
		  //https://{serverRoot}/bot/{apiVersion}/{chatbotId}/medias/delete
		  
//		  String fileUrl = uploadFile(token, false , new File("F:/rcs/1.jpg"));
		  String fileUrl = "http://124.126.120.102/perm/src/2020071718/b5e/view/10,3c3504f70ff5ba5fe13e.jpg";
		  
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("bot")
		  .append("/").append(apiVersion)
		  .append("/").append(chatbotId)
		  .append("/").append("medias")
		  .append("/").append("delete");
		  
		  String url = builder.toString();
		  
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "accessToken "+token);
		  headers.add("url", fileUrl);
		  InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);
		  headers.setHost(inetSocketAddress);
		  headers.setDate(System.currentTimeMillis());
		  
		  HttpEntity<String> request = new HttpEntity<String>("", headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("errorCode"); 
	  }
	  
	  /**
		 * chatbot发送消息（群发消息）接口（纯文本消息）
		 * 
		 * @param token
		 * @return 返回操作结果
		 * @throws Exception
		 */
	  public String testSendGroupTextMessage(String appId,String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  //https://{serverRoot}/messaging/group/template/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(appId,token,date);
		  System.out.println("Basic "+authorization);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  
		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				  		"    <address>tel:+86"+mobile+"</address>\r\n" + 
				  		"    <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" +
				  		"    <senderAddress>"+chatbotId+"</senderAddress>\r\n" +
				  		"    <outboundIMMessage>\r\n" + 
				  		"        <contentType>text/plain</contentType>\r\n" + 
				  		"        <conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
				  		"        <contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
				  		"        <serviceCapability>\r\n" + 
				  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				  		"        </serviceCapability>\r\n" + 
				  		"        <bodyText>Hello World</bodyText>\r\n" + 
				  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
				  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
				  		"        <reportRequest>Failed</reportRequest>\r\n" + 
				  		"    </outboundIMMessage>\r\n" + 
				  		"</msg:outboundMessageRequest>";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  /**
	   * chatbot发送消息（群发消息）接口（带悬浮菜单的文本消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupTextSuggestionsMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  
		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				  		"     <address>tel:+86"+mobile+"</address>\r\n" + 
				  		"    <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
				  		"    <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
				  		"    <outboundIMMessage>\r\n" + 
				  		"        <contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
				  		"        <conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
				  		"        <contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
				  		"        <serviceCapability>\r\n" + 
				  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				  		"        </serviceCapability>\r\n" + 
				  		"        <bodyText><![CDATA[--next\r\n" + 
				  		"Content-Type: text/plain\r\n" + 
				  		"Content-Length: 21\r\n" + 
				  		"\r\n" + 
				  		"您好，群发测试\r\n" + 
				  		"--next\r\n" + 
				  		"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				  		"\r\n" + 
				  		"{\r\n" + 
				  		"    \"suggestions\": [\r\n" + 
				  		"        {\r\n" + 
				  		"            \"reply\": {\r\n" + 
				  		"                \"displayText\": \"详情\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"detail\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        },\r\n" + 
				  		"        {\r\n" + 
				  		"            \"action\": {\r\n" + 
				  		"                \"urlAction\": {\r\n" + 
				  		"                    \"openUrl\": {\r\n" + 
				  		"                        \"url\": \"https://www.baidu.com\"\r\n" + 
				  		"                    }\r\n" + 
				  		"                },\r\n" + 
				  		"                \"displayText\": \"打开百度\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"url\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        },\r\n" + 
				  		"        {\r\n" + 
				  		"            \"action\": {\r\n" + 
				  		"                \"dialerAction\": {\r\n" + 
				  		"                    \"dialPhoneNumber\": {\r\n" + 
				  		"                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
				  		"                    }\r\n" + 
				  		"                },\r\n" + 
				  		"                \"displayText\": \"打电话\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"mobile\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        }\r\n" + 
				  		"    ]\r\n" + 
				  		"}\r\n" + 
				  		"--next--\r\n" + 
				  		"]]></bodyText>\r\n" + 
				  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
				  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
				  		"        <reportRequest>Failed</reportRequest>\r\n" + 
				  		"    </outboundIMMessage>\r\n" + 
				  		"</msg:outboundMessageRequest>";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  /**
	   * chatbot发送消息（群发消息）接口（纯文件消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupFileMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  //图片
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//				  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//				  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//				  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//				  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//				  		"<outboundIMMessage>\r\n" + 
//				  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//				  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//				  		"<contentType>application/vnd.gsma.rcs-ft-http+xml</contentType>\r\n" +  
//				  		"<serviceCapability>\r\n" + 
//				  		"<capabilityId>ChatbotSA</capabilityId>\r\n" + 
//				  		"<version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//				  		"</serviceCapability>\r\n" + 
//				  		"<bodyText><![CDATA[\r\n" + 
//				  		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
//				  		"	<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" + 
//				  		"		<file-info type=\"thumbnail\">\r\n" + 
//				  		"		<file-size>1132</file-size>\r\n" + 
//				  		"		<content-type>image/png</content-type>\r\n" + 
//				  		"		<data url=\"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300TD\" until=\"2020-11-25T16:26:40Z\"/>\r\n" + 
//				  		"	</file-info>\r\n" + 
//				  		"	<file-info type=\"file\">\r\n" + 
//				  		"		<file-size>3246</file-size>\r\n" + 
//				  		"		<file-name>64cfb45b5891babd89556c06ca2d9c8e.png</file-name>\r\n" + 
//				  		"		<content-type>image/png</content-type>\r\n" + 
//				  		"		<data url=\"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300FD.png\" until=\"2020-11-25T16:26:40Z\"/>\r\n" + 
//				  		"	</file-info>\r\n" + 
//				  		"</file>\r\n" + 
//				  		"]]></bodyText>\r\n" + 
//				  		"</outboundIMMessage>\r\n" + 
//				  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//				  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//				  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//				  		"</msg:outboundMessageRequest>";
  		//音频
//  		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//	  					"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//	  					"     <address>tel:+86"+mobile+"</address>\r\n" + 
//	  					"    <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//	  					"    <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//	  					"    <outboundIMMessage>\r\n" + 
//	  					"        <contentType>application/vnd.gsma.rcs-ft-http+xml</contentType>\r\n" + 
//	  					"        <conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//	  					"        <contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//	  					"        <serviceCapability>\r\n" + 
//	  					"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//	  					"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//	  					"        </serviceCapability>\r\n" + 
//	  					"        <bodyText><![CDATA[\r\n" + 
//	  					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
//	  					"<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">"+
//	  					"<file-info type=\"file\">\r\n" + 
//	  					"<file-size>56495</file-size>\r\n" + 
//	  					"<content-type>audio/mp3</content-type>\r\n" + 
//	  					"<data \r\n" + 
//	  					"url=\"https://gz01ft.sbc.rcs.chinamobile.com:10007/s/11182015081132440460878338FD.mp3\" until=\"2020-11-25T20:15:08Z\"/>\r\n" + 
//	  					"</file-info>\r\n" + 
//	  					"</file>\r\n" +
//	  					"]]></bodyText>\r\n" + 
//	  					"        <reportRequest>Delivered</reportRequest>\r\n" + 
//	  					"        <reportRequest>Displayed</reportRequest>\r\n" + 
//	  					"        <reportRequest>Failed</reportRequest>\r\n" + 
//	  					"    </outboundIMMessage>\r\n" + 
//	  					"</msg:outboundMessageRequest>";
		  
		 //视频
  		 String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"     <address>tel:+86"+mobile+"</address>\r\n" + 
				"    <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
				"    <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
				"    <outboundIMMessage>\r\n" + 
				"        <contentType>application/vnd.gsma.rcs-ft-http+xml</contentType>\r\n" + 
				"        <conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
				"        <contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
				"        <serviceCapability>\r\n" + 
				"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				"        </serviceCapability>\r\n" + 
				"        <bodyText><![CDATA[\r\n" + 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">"+
				"<file-info type=\"file\">\r\n" + 
				"<file-size>4246316</file-size>\r\n" + 
				"<content-type>video/mp4</content-type>\r\n" + 
				"<data \r\n" + 
				"url=\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523FD.mp4\" until=\"2020-11-25T19:23:54Z\"/>\r\n" + 
				"</file-info>\r\n" + 
				"</file>\r\n" +
				"]]></bodyText>\r\n" + 
				"        <reportRequest>Delivered</reportRequest>\r\n" + 
				"        <reportRequest>Displayed</reportRequest>\r\n" + 
				"        <reportRequest>Failed</reportRequest>\r\n" + 
				"    </outboundIMMessage>\r\n" + 
				"</msg:outboundMessageRequest>";
  			
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  /**
	   * chatbot发送消息（群发消息）接口（带悬浮菜单的文件消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupFileSuggestionsMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  //图片
		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
				  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
				  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
				  		"<outboundIMMessage>\r\n" + 
				  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
				  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
				  		"<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
				  		"        <serviceCapability>\r\n" + 
				  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				  		"        </serviceCapability>\r\n" + 
				  		"        <bodyText><![CDATA[--next\r\n" + 
				  		"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
				  		"Content-Length: 320\r\n" + 
				  		"\r\n" + 
				  		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				  		"<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" + 
				  		"<file-info type=\"thumbnail\">\r\n" + 
				  		"		<file-size>1834</file-size>\r\n" + 
				  		"		<content-type>image/jpeg</content-type>\r\n" + 
				  		"		<data url=\"https://gz01ft.sbc.rcs.chinamobile.com:10011/s/12161804181132510291100688TD\" until=\"2020-12-23T18:04:18Z\"/>\r\n" + 
				  		"	</file-info>\r\n" + 
				  		"	<file-info type=\"file\">\r\n" + 
				  		"		<file-size>2035</file-size>\r\n" + 
				  		"		<file-name>22077d5a5c623fc00d2859a2ebf3e077.jpg</file-name>\r\n" + 
				  		"		<content-type>image/jpeg</content-type>\r\n" + 
				  		"		<data url=\"https://gz01ft.sbc.rcs.chinamobile.com:10011/s/12161804181132510291100688FD.jpg\" until=\"2020-12-23T18:04:18Z\"/>\r\n" + 
				  		"	</file-info>\r\n" + 
				  		"</file>\r\n" + 
				  		"--next\r\n" + 
				  		"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				  		"\r\n" + 
				  		"{\r\n" + 
				  		"    \"suggestions\": [\r\n" + 
				  		"        {\r\n" + 
				  		"            \"reply\": {\r\n" + 
				  		"                \"displayText\": \"detail\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"more\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        },\r\n" + 
				  		"        {\r\n" + 
				  		"            \"action\": {\r\n" + 
				  		"                \"urlAction\": {\r\n" + 
				  		"                    \"openUrl\": {\r\n" + 
				  		"                        \"url\": \"https://cloud.baidu.com\"\r\n" + 
				  		"                    }\r\n" + 
				  		"                },\r\n" + 
				  		"                \"displayText\": \"url\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"url\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        },\r\n" + 
				  		"        {\r\n" + 
				  		"            \"action\": {\r\n" + 
				  		"                \"dialerAction\": {\r\n" + 
				  		"                    \"dialPhoneNumber\": {\r\n" + 
				  		"                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
				  		"                    }\r\n" + 
				  		"                },\r\n" + 
				  		"                \"displayText\": \"mobile\",\r\n" + 
				  		"                \"postback\": {\r\n" + 
				  		"                    \"data\": \"mobile\"\r\n" + 
				  		"                }\r\n" + 
				  		"            }\r\n" + 
				  		"        }\r\n" + 
				  		"    ]\r\n" + 
				  		"}\r\n" + 
				  		"--next--\r\n" + 
				  		"]]></bodyText>\r\n" + 
				  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
				  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
				  		"        <reportRequest>Failed</reportRequest>\r\n" + 
				  		"    </outboundIMMessage>\r\n" + 
				  		"</msg:outboundMessageRequest>";
		  
		  //音频
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//	  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//	  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//	  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//	  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//	  		"<outboundIMMessage>\r\n" + 
//	  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//	  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//	  		"<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
//	  		"        <serviceCapability>\r\n" + 
//	  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//	  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//	  		"        </serviceCapability>\r\n" + 
//	  		"        <bodyText><![CDATA[--next\r\n" + 
//	  		"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
//	  		"Content-Length: 320\r\n" + 
//	  		"\r\n" + 
//	  		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
//	  		"<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" +
//			"<file-info type=\"file\">\r\n" + 
//			"<file-size>56495</file-size>\r\n" + 
//			"<content-type>audio/mp3</content-type>\r\n" + 
//			"<data \r\n" + 
//			"url=\"https://gz01ft.sbc.rcs.chinamobile.com:10007/s/11182015081132440460878338FD.mp3\" until=\"2020-11-25T20:15:08Z\"/>\r\n" + 
//			"</file-info>\r\n" + 
//			"</file>\r\n" +
//	  		"--next\r\n" + 
//	  		"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//	  		"\r\n" + 
//	  		"{\r\n" + 
//	  		"    \"suggestions\": [\r\n" + 
//	  		"        {\r\n" + 
//	  		"            \"reply\": {\r\n" + 
//	  		"                \"displayText\": \"点击详情\",\r\n" + 
//	  		"                \"postback\": {\r\n" + 
//	  		"                    \"data\": \"more\"\r\n" + 
//	  		"                }\r\n" + 
//	  		"            }\r\n" + 
//	  		"        },\r\n" + 
//	  		"        {\r\n" + 
//	  		"            \"action\": {\r\n" + 
//	  		"                \"urlAction\": {\r\n" + 
//	  		"                    \"openUrl\": {\r\n" + 
//	  		"                        \"url\": \"https://www.baidu.com\"\r\n" + 
//	  		"                    }\r\n" + 
//	  		"                },\r\n" + 
//	  		"                \"displayText\": \"打开百度\",\r\n" + 
//	  		"                \"postback\": {\r\n" + 
//	  		"                    \"data\": \"url\"\r\n" + 
//	  		"                }\r\n" + 
//	  		"            }\r\n" + 
//	  		"        },\r\n" + 
//	  		"        {\r\n" + 
//	  		"            \"action\": {\r\n" + 
//	  		"                \"dialerAction\": {\r\n" + 
//	  		"                    \"dialPhoneNumber\": {\r\n" + 
//	  		"                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
//	  		"                    }\r\n" + 
//	  		"                },\r\n" + 
//	  		"                \"displayText\": \"打电话\",\r\n" + 
//	  		"                \"postback\": {\r\n" + 
//	  		"                    \"data\": \"mobile\"\r\n" + 
//	  		"                }\r\n" + 
//	  		"            }\r\n" + 
//	  		"        }\r\n" + 
//	  		"    ]\r\n" + 
//	  		"}\r\n" + 
//	  		"--next--\r\n" + 
//	  		"]]></bodyText>\r\n" + 
//	  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//	  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//	  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//	  		"    </outboundIMMessage>\r\n" + 
//	  		"</msg:outboundMessageRequest>";
		  
		  //视频
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//			  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//			  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//			  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//			  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//			  		"<outboundIMMessage>\r\n" + 
//			  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//			  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//			  		"<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
//			  		"        <serviceCapability>\r\n" + 
//			  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//			  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//			  		"        </serviceCapability>\r\n" + 
//			  		"        <bodyText><![CDATA[--next\r\n" + 
//			  		"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
//			  		"Content-Length: 320\r\n" + 
//			  		"\r\n" + 
//			  		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
//			  		"<file  xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" +
//			  		"<file-info type=\"file\">\r\n" + 
//					"<file-size>4246316</file-size>\r\n" + 
//					"<content-type>video/mp4</content-type>\r\n" + 
//					"<data \r\n" + 
//					"url=\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523FD.mp4\" until=\"2020-11-25T19:23:54Z\"/>\r\n" + 
//					"</file-info>\r\n" + 
//					"</file>\r\n" +
//			  		"--next\r\n" + 
//			  		"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//			  		"\r\n" + 
//			  		"{\r\n" + 
//			  		"    \"suggestions\": [\r\n" + 
//			  		"        {\r\n" + 
//			  		"            \"reply\": {\r\n" + 
//			  		"                \"displayText\": \"详情\",\r\n" + 
//			  		"                \"postback\": {\r\n" + 
//			  		"                    \"data\": \"more\"\r\n" + 
//			  		"                }\r\n" + 
//			  		"            }\r\n" + 
//			  		"        },\r\n" + 
//			  		"        {\r\n" + 
//			  		"            \"action\": {\r\n" + 
//			  		"                \"urlAction\": {\r\n" + 
//			  		"                    \"openUrl\": {\r\n" + 
//			  		"                        \"url\": \"https://www.baidu.com\"\r\n" + 
//			  		"                    }\r\n" + 
//			  		"                },\r\n" + 
//			  		"                \"displayText\": \"打开百度\",\r\n" + 
//			  		"                \"postback\": {\r\n" + 
//			  		"                    \"data\": \"url\"\r\n" + 
//			  		"                }\r\n" + 
//			  		"            }\r\n" + 
//			  		"        },\r\n" + 
//			  		"        {\r\n" + 
//			  		"            \"action\": {\r\n" + 
//			  		"                \"dialerAction\": {\r\n" + 
//			  		"                    \"dialPhoneNumber\": {\r\n" + 
//			  		"                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
//			  		"                    }\r\n" + 
//			  		"                },\r\n" + 
//			  		"                \"displayText\": \"打电话\",\r\n" + 
//			  		"                \"postback\": {\r\n" + 
//			  		"                    \"data\": \"mobile\"\r\n" + 
//			  		"                }\r\n" + 
//			  		"            }\r\n" + 
//			  		"        }\r\n" + 
//			  		"    ]\r\n" + 
//			  		"}\r\n" + 
//			  		"--next--\r\n" + 
//			  		"]]></bodyText>\r\n" + 
//			  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//			  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//			  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//			  		"    </outboundIMMessage>\r\n" + 
//			  		"</msg:outboundMessageRequest>";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  /**
	   * chatbot发送消息（群发消息）接口（单卡片消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupSingleCardMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  //图片
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//			  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//			  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//			  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//			  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//			  		"<outboundIMMessage>\r\n" + 
//			  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//			  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//			  		"<contentType>application/vnd.gsma.botmessage.v1.0+json</contentType>\r\n" + 
//			  		"        <serviceCapability>\r\n" + 
//			  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//			  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//			  		"        </serviceCapability>\r\n" + 
//			  		"        <bodyText><![CDATA[\r\n" +
//			  		"{\r\n" + 
//			  		"    \"message\": {\r\n" + 
//			  		"        \"generalPurposeCard\": {\r\n" + 
//			  		"            \"layout\": {\r\n" + 
//			  		"                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
//			  		"                \"imageAlignment\": \"LEFT\"\r\n" + 
//			  		"            },\r\n" + 
//			  		"            \"content\": {\r\n" + 
//			  		"                \"media\": {\r\n" + 
//			  		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300FD.png\",\r\n" + 
//			  		"                    \"mediaContentType\": \"image/png\",\r\n" + 
//			  		"                    \"mediaFileSize\": 3246,\r\n" + 
//			  		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300TD\",\r\n" + 
//			  		"                    \"thumbnailContentType\": \"image/png\",\r\n" + 
//			  		"                    \"thumbnailFileSize\": 1132,\r\n" + 
//			  		"                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
//			  		"                },\r\n" + 
//			  		"                \"title\": \"单卡片消息体\",\r\n" + 
//			  		"                \"description\": \"图片\",\r\n" + 
//					  "    				\"suggestions\": [\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"reply\": {\r\n" + 
//					  "                			\"displayText\": \"详情\",\r\n" + 
//					  "                			\"postback\": {\r\n" + 
//					  "                    		\"data\": \"more\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			},\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"action\": {\r\n" + 
//					  "                		\"urlAction\": {\r\n" + 
//					  "                    \"openUrl\": {\r\n" + 
//					  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
//					  "                    }\r\n" + 
//					  "                },\r\n" + 
//					  "                \"displayText\": \"打开百度\",\r\n" + 
//					  "                \"postback\": {\r\n" + 
//					  "                    \"data\": \"url\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			},\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"action\": {\r\n" + 
//					  "                			\"dialerAction\": {\r\n" + 
//					  "                    			\"dialPhoneNumber\": {\r\n" + 
//					  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
//					  "                    			}\r\n" + 
//					  "                				},\r\n" + 
//					  "                				\"displayText\": \"打电话\",\r\n" + 
//					  "                				\"postback\": {\r\n" + 
//					  "                    			\"data\": \"mobile\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			}\r\n" + 
//					  "    			]\r\n" + 
//			  		"            }\r\n" + 
//			  		"        }\r\n" + 
//			  		"    }\r\n" + 
//			  		"}\r\n" + 
//			  		"]]></bodyText>\r\n" + 
//			  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//			  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//			  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//			  		"    </outboundIMMessage>\r\n" + 
//			  		"</msg:outboundMessageRequest>";
		  
		  //音频
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//			  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//			  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//			  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//			  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//			  		"<outboundIMMessage>\r\n" + 
//			  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//			  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//			  		"<contentType>application/vnd.gsma.botmessage.v1.0+json</contentType>\r\n" + 
//			  		"        <serviceCapability>\r\n" + 
//			  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//			  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//			  		"        </serviceCapability>\r\n" + 
//			  		"        <bodyText><![CDATA[\r\n" +
//			  		"{\r\n" + 
//			  		"    \"message\": {\r\n" + 
//			  		"        \"generalPurposeCard\": {\r\n" + 
//			  		"            \"layout\": {\r\n" + 
//			  		"                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
//			  		"                \"imageAlignment\": \"LEFT\"\r\n" + 
//			  		"            },\r\n" + 
//			  		"            \"content\": {\r\n" + 
//			  		"                \"media\": {\r\n" + 
//			  		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10007/s/11182015081132440460878338FD.mp3\",\r\n" + 
//			  		"                    \"mediaContentType\": \"video/mp3\",\r\n" + 
//			  		"                    \"mediaFileSize\": 56495,\r\n" + 
//			  		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/11181920121132340460385505TD\",\r\n" + 
//			  		"                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
//			  		"                    \"thumbnailFileSize\": 2377,\r\n" + 
//			  		"                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
//			  		"                },\r\n" + 
//			  		"                \"title\": \"单卡片消息体\",\r\n" + 
//			  		"                \"description\": \"音频\",\r\n" + 
//			  		"    				\"suggestions\": [\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"reply\": {\r\n" + 
//					  "                			\"displayText\": \"详情\",\r\n" + 
//					  "                			\"postback\": {\r\n" + 
//					  "                    		\"data\": \"more\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			},\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"action\": {\r\n" + 
//					  "                		\"urlAction\": {\r\n" + 
//					  "                    \"openUrl\": {\r\n" + 
//					  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
//					  "                    }\r\n" + 
//					  "                },\r\n" + 
//					  "                \"displayText\": \"打开百度\",\r\n" + 
//					  "                \"postback\": {\r\n" + 
//					  "                    \"data\": \"url\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			},\r\n" + 
//					  "        			{\r\n" + 
//					  "            			\"action\": {\r\n" + 
//					  "                			\"dialerAction\": {\r\n" + 
//					  "                    			\"dialPhoneNumber\": {\r\n" + 
//					  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
//					  "                    			}\r\n" + 
//					  "                				},\r\n" + 
//					  "                				\"displayText\": \"打电话\",\r\n" + 
//					  "                				\"postback\": {\r\n" + 
//					  "                    			\"data\": \"mobile\"\r\n" + 
//					  "                			}\r\n" + 
//					  "            			}\r\n" + 
//					  "        			}\r\n" + 
//					  "    			]\r\n" +
//			  		"            }\r\n" + 
//			  		"        }\r\n" + 
//			  		"    }\r\n" + 
//			  		"}\r\n" + 
//			  		"]]></bodyText>\r\n" + 
//			  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//			  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//			  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//			  		"    </outboundIMMessage>\r\n" + 
//			  		"</msg:outboundMessageRequest>";
		  
		  //视频
		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
		  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
		  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
		  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
		  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
		  		"<outboundIMMessage>\r\n" + 
		  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
		  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
		  		"<contentType>application/vnd.gsma.botmessage.v1.0+json</contentType>\r\n" + 
		  		"        <serviceCapability>\r\n" + 
		  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
		  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
		  		"        </serviceCapability>\r\n" + 
		  		"        <bodyText><![CDATA[\r\n" +
		  		"{\r\n" + 
		  		"    \"message\": {\r\n" + 
		  		"        \"generalPurposeCard\": {\r\n" + 
		  		"            \"layout\": {\r\n" + 
		  		"                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
		  		"                \"imageAlignment\": \"LEFT\"\r\n" + 
		  		"            },\r\n" + 
		  		"            \"content\": {\r\n" + 
		  		"                \"media\": {\r\n" + 
		  		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523FD.mp4\",\r\n" + 
		  		"                    \"mediaContentType\": \"video/mp4\",\r\n" + 
		  		"                    \"mediaFileSize\": 4246316,\r\n" + 
		  		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523TD\",\r\n" + 
		  		"                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
		  		"                    \"thumbnailFileSize\": 4067,\r\n" + 
		  		"                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
		  		"                },\r\n" + 
		  		"                \"title\": \"单卡片消息体\",\r\n" + 
		  		"                \"description\": \"视频\",\r\n" + 
		  		"    				\"suggestions\": [\r\n" + 
				  "        			{\r\n" + 
				  "            			\"reply\": {\r\n" + 
				  "                			\"displayText\": \"详情\",\r\n" + 
				  "                			\"postback\": {\r\n" + 
				  "                    		\"data\": \"more\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			},\r\n" + 
				  "        			{\r\n" + 
				  "            			\"action\": {\r\n" + 
				  "                		\"urlAction\": {\r\n" + 
				  "                    \"openUrl\": {\r\n" + 
				  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
				  "                    }\r\n" + 
				  "                },\r\n" + 
				  "                \"displayText\": \"打开百度\",\r\n" + 
				  "                \"postback\": {\r\n" + 
				  "                    \"data\": \"url\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			},\r\n" + 
				  "        			{\r\n" + 
				  "            			\"action\": {\r\n" + 
				  "                			\"dialerAction\": {\r\n" + 
				  "                    			\"dialPhoneNumber\": {\r\n" + 
				  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
				  "                    			}\r\n" + 
				  "                				},\r\n" + 
				  "                				\"displayText\": \"打电话\",\r\n" + 
				  "                				\"postback\": {\r\n" + 
				  "                    			\"data\": \"mobile\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			}\r\n" + 
				  "    			]\r\n" +
		  		"            }\r\n" + 
		  		"        }\r\n" + 
		  		"    }\r\n" + 
		  		"}\r\n" + 
		  		"]]></bodyText>\r\n" + 
		  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
		  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
		  		"        <reportRequest>Failed</reportRequest>\r\n" + 
		  		"    </outboundIMMessage>\r\n" + 
		  		"</msg:outboundMessageRequest>";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  
	  /**
	   * chatbot发送消息（群发消息）接口（带悬浮菜单的单卡片消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupSingleCardSuggestionsMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  //图片
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//				  "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//				  "  <address>tel:+86"+mobile+"</address>\r\n" + 
//				  "  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//				  "  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//				  "<outboundIMMessage>\r\n" + 
//				  "<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//				  "<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//				  "<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
//				  "        <serviceCapability>\r\n" + 
//				  "            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//				  "            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//				  "        </serviceCapability>\r\n" + 
//				  "        <bodyText><![CDATA[--next\r\n" + 
//				  "Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
//				  "Content-Length: 320\r\n" + 
//				  "\r\n" + 
//				  "{\r\n" + 
//			  	  "    \"message\": {\r\n" + 
//			  	  "        \"generalPurposeCard\": {\r\n" + 
//			  	  "            \"layout\": {\r\n" + 
//			  	  "                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
//			  	  "                \"imageAlignment\": \"LEFT\"\r\n" + 
//			  	  "            },\r\n" + 
//			  	  "            \"content\": {\r\n" + 
//			  	  "                \"media\": {\r\n" + 
//			  	  "                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300FD.png\",\r\n" + 
//			  	  "                    \"mediaContentType\": \"image/png\",\r\n" + 
//			  	  "                    \"mediaFileSize\": 3246,\r\n" + 
//			  	  "                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300TD\",\r\n" + 
//			  	  "                    \"thumbnailContentType\": \"image/png\",\r\n" + 
//			  	  "                    \"thumbnailFileSize\": 1132,\r\n" + 
//			  	  "                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
//			  	  "                },\r\n" + 
//			  	  "                \"title\": \"带悬浮菜单的单卡片消息体\",\r\n" + 
//			  	  "                \"description\": \"图片\",\r\n" + 
//				  "    				\"suggestions\": [\r\n" + 
//				  "        			{\r\n" + 
//				  "            			\"reply\": {\r\n" + 
//				  "                			\"displayText\": \"详情\",\r\n" + 
//				  "                			\"postback\": {\r\n" + 
//				  "                    		\"data\": \"more\"\r\n" + 
//				  "                			}\r\n" + 
//				  "            			}\r\n" + 
//				  "        			},\r\n" + 
//				  "        			{\r\n" + 
//				  "            			\"action\": {\r\n" + 
//				  "                		\"urlAction\": {\r\n" + 
//				  "                    \"openUrl\": {\r\n" + 
//				  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
//				  "                    }\r\n" + 
//				  "                },\r\n" + 
//				  "                \"displayText\": \"打开百度\",\r\n" + 
//				  "                \"postback\": {\r\n" + 
//				  "                    \"data\": \"url\"\r\n" + 
//				  "                			}\r\n" + 
//				  "            			}\r\n" + 
//				  "        			},\r\n" + 
//				  "        			{\r\n" + 
//				  "            			\"action\": {\r\n" + 
//				  "                			\"dialerAction\": {\r\n" + 
//				  "                    			\"dialPhoneNumber\": {\r\n" + 
//				  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
//				  "                    			}\r\n" + 
//				  "                				},\r\n" + 
//				  "                				\"displayText\": \"打电话\",\r\n" + 
//				  "                				\"postback\": {\r\n" + 
//				  "                    			\"data\": \"mobile\"\r\n" + 
//				  "                			}\r\n" + 
//				  "            			}\r\n" + 
//				  "        			}\r\n" + 
//				  "    			]\r\n" + 
//			  	  "            }\r\n" + 
//			  	  "        }\r\n" + 
//			  	  "    }\r\n" + 
//			  	  "}\r\n" +  
//				  "--next\r\n" + 
//				  "Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//				  "\r\n" + 
//				  "{\r\n" + 
//				  "    \"suggestions\": [\r\n" + 
//				  "        {\r\n" + 
//				  "            \"reply\": {\r\n" + 
//				  "                \"displayText\": \"了解更多\",\r\n" + 
//				  "                \"postback\": {\r\n" + 
//				  "                    \"data\": \"more\"\r\n" + 
//				  "                }\r\n" + 
//				  "            }\r\n" + 
//				  "        },\r\n" + 
//				  "        {\r\n" + 
//				  "            \"action\": {\r\n" + 
//				  "                \"urlAction\": {\r\n" + 
//				  "                    \"openUrl\": {\r\n" + 
//				  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
//				  "                    }\r\n" + 
//				  "                },\r\n" + 
//				  "                \"displayText\": \"打开百度\",\r\n" + 
//				  "                \"postback\": {\r\n" + 
//				  "                    \"data\": \"url\"\r\n" + 
//				  "                }\r\n" + 
//				  "            }\r\n" + 
//				  "        },\r\n" + 
//				  "        {\r\n" + 
//				  "            \"action\": {\r\n" + 
//				  "                \"dialerAction\": {\r\n" + 
//				  "                    \"dialPhoneNumber\": {\r\n" + 
//				  "                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
//				  "                    }\r\n" + 
//				  "                },\r\n" + 
//				  "                \"displayText\": \"打电话\",\r\n" + 
//				  "                \"postback\": {\r\n" + 
//				  "                    \"data\": \"mobile\"\r\n" + 
//				  "                }\r\n" + 
//				  "            }\r\n" + 
//				  "        }\r\n" + 
//				  "    ]\r\n" + 
//				  "}\r\n" + 
//				  "--next--\r\n" + 
//				  "]]></bodyText>\r\n" + 
//				  "        <reportRequest>Delivered</reportRequest>\r\n" + 
//				  "        <reportRequest>Displayed</reportRequest>\r\n" + 
//				  "        <reportRequest>Failed</reportRequest>\r\n" + 
//				  "    </outboundIMMessage>\r\n" + 
//				  "</msg:outboundMessageRequest>";
		  
		  //音频
//		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
//	  		"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
//	  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
//	  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
//	  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
//	  		"<outboundIMMessage>\r\n" + 
//	  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
//	  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
//	  		"<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
//	  		"        <serviceCapability>\r\n" + 
//	  		"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
//	  		"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
//	  		"        </serviceCapability>\r\n" + 
//	  		"        <bodyText><![CDATA[--next\r\n" + 
//	  		"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
//	  		"Content-Length: 320\r\n" + 
//	  		"\r\n" + 
//	  		"{\r\n" + 
//	  		"    \"message\": {\r\n" + 
//	  		"        \"generalPurposeCard\": {\r\n" + 
//	  		"            \"layout\": {\r\n" + 
//	  		"                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
//	  		"                \"imageAlignment\": \"LEFT\"\r\n" + 
//	  		"            },\r\n" + 
//	  		"            \"content\": {\r\n" + 
//	  		"                \"media\": {\r\n" + 
//	  		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10007/s/11182015081132440460878338FD.mp3\",\r\n" + 
//	  		"                    \"mediaContentType\": \"video/mp3\",\r\n" + 
//	  		"                    \"mediaFileSize\": 56495,\r\n" + 
//	  		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/11181920121132340460385505TD\",\r\n" + 
//	  		"                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
//	  		"                    \"thumbnailFileSize\": 2377,\r\n" + 
//	  		"                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
//	  		"                },\r\n" + 
//	  		"                \"title\": \"带悬浮菜单的单卡片消息体\",\r\n" + 
//	  		"                \"description\": \"音频\",\r\n" + 
//		  "    				\"suggestions\": [\r\n" + 
//		  "        			{\r\n" + 
//		  "            			\"reply\": {\r\n" + 
//		  "                			\"displayText\": \"详情\",\r\n" + 
//		  "                			\"postback\": {\r\n" + 
//		  "                    		\"data\": \"more\"\r\n" + 
//		  "                			}\r\n" + 
//		  "            			}\r\n" + 
//		  "        			},\r\n" + 
//		  "        			{\r\n" + 
//		  "            			\"action\": {\r\n" + 
//		  "                		\"urlAction\": {\r\n" + 
//		  "                    \"openUrl\": {\r\n" + 
//		  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
//		  "                    }\r\n" + 
//		  "                },\r\n" + 
//		  "                \"displayText\": \"打开百度\",\r\n" + 
//		  "                \"postback\": {\r\n" + 
//		  "                    \"data\": \"url\"\r\n" + 
//		  "                			}\r\n" + 
//		  "            			}\r\n" + 
//		  "        			},\r\n" + 
//		  "        			{\r\n" + 
//		  "            			\"action\": {\r\n" + 
//		  "                			\"dialerAction\": {\r\n" + 
//		  "                    			\"dialPhoneNumber\": {\r\n" + 
//		  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
//		  "                    			}\r\n" + 
//		  "                				},\r\n" + 
//		  "                				\"displayText\": \"打电话\",\r\n" + 
//		  "                				\"postback\": {\r\n" + 
//		  "                    			\"data\": \"mobile\"\r\n" + 
//		  "                			}\r\n" + 
//		  "            			}\r\n" + 
//		  "        			}\r\n" + 
//		  "    			]\r\n" + 
//	  		"            }\r\n" + 
//	  		"        }\r\n" + 
//	  		"    }\r\n" + 
//	  		"}\r\n" +
//			"--next\r\n" + 
//			"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//			"\r\n" + 
//			"{\r\n" + 
//			"    \"suggestions\": [\r\n" + 
//			"        {\r\n" + 
//			"            \"reply\": {\r\n" + 
//			"                \"displayText\": \"了解更多\",\r\n" + 
//			"                \"postback\": {\r\n" + 
//			"                    \"data\": \"more\"\r\n" + 
//			"                }\r\n" + 
//			"            }\r\n" + 
//			"        },\r\n" +
//			"        {\r\n" + 
//			"            \"action\": {\r\n" + 
//			"                \"urlAction\": {\r\n" + 
//			"                    \"openUrl\": {\r\n" + 
//			"                        \"url\": \"https://www.baidu.com\"\r\n" + 
//			"                    }\r\n" + 
//			"                },\r\n" + 
//			"                \"displayText\": \"打开百度\",\r\n" + 
//			"                \"postback\": {\r\n" + 
//			"                    \"data\": \"url\"\r\n" + 
//			"                }\r\n" + 
//			"            }\r\n" + 
//			"        },\r\n" + 
//			"        {\r\n" + 
//			"            \"action\": {\r\n" + 
//			"                \"dialerAction\": {\r\n" + 
//			"                    \"dialPhoneNumber\": {\r\n" + 
//			"                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
//			"                    }\r\n" + 
//			"                },\r\n" + 
//			"                \"displayText\": \"打电话\",\r\n" + 
//			"                \"postback\": {\r\n" + 
//			"                    \"data\": \"mobile\"\r\n" + 
//			"                }\r\n" + 
//			"            }\r\n" + 
//			"        }\r\n" + 
//			"    ]\r\n" + 
//			"}\r\n" + 
//			"--next--\r\n" + 
//	  		"]]></bodyText>\r\n" + 
//	  		"        <reportRequest>Delivered</reportRequest>\r\n" + 
//	  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
//	  		"        <reportRequest>Failed</reportRequest>\r\n" + 
//	  		"    </outboundIMMessage>\r\n" + 
//	  		"</msg:outboundMessageRequest>";
		  
		  //视频
		  String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + 
				  "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				  "  <address>tel:+86"+mobile+"</address>\r\n" + 
				  "  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
				  "  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
				  "<outboundIMMessage>\r\n" + 
				  "<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
				  "<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
				  "<contentType>multipart/mixed; boundary=&quot;next&quot;</contentType>\r\n" + 
				  "        <serviceCapability>\r\n" + 
				  "            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				  "            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				  "        </serviceCapability>\r\n" + 
				  "        <bodyText><![CDATA[--next\r\n" + 
				  "Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				  "Content-Length: 320\r\n" + 
				  "\r\n" + 
				  "{\r\n" + 
			  	  "    \"message\": {\r\n" + 
			  	  "        \"generalPurposeCard\": {\r\n" + 
			  	  "            \"layout\": {\r\n" + 
			  	  "                \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
			  	  "                \"imageAlignment\": \"LEFT\"\r\n" + 
			  	  "            },\r\n" + 
			  	  "            \"content\": {\r\n" + 
			  	  "                \"media\": {\r\n" + 
			  	  "                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523FD.mp4\",\r\n" + 
			  	  "                    \"mediaContentType\": \"video/mp4\",\r\n" + 
			  	  "                    \"mediaFileSize\": 4246316,\r\n" + 
			  	  "                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523TD\",\r\n" + 
			  	  "                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
			  	  "                    \"thumbnailFileSize\": 4067,\r\n" + 
			  	  "                    \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
			  	  "                },\r\n" + 
			  	  "                \"title\": \"带悬浮菜单的单卡片消息体\",\r\n" + 
			  	  "                \"description\": \"视频\",\r\n" + 
				  "    				\"suggestions\": [\r\n" + 
				  "        			{\r\n" + 
				  "            			\"reply\": {\r\n" + 
				  "                			\"displayText\": \"详情\",\r\n" + 
				  "                			\"postback\": {\r\n" + 
				  "                    		\"data\": \"more\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			},\r\n" + 
				  "        			{\r\n" + 
				  "            			\"action\": {\r\n" + 
				  "                		\"urlAction\": {\r\n" + 
				  "                    \"openUrl\": {\r\n" + 
				  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
				  "                    }\r\n" + 
				  "                },\r\n" + 
				  "                \"displayText\": \"打开百度\",\r\n" + 
				  "                \"postback\": {\r\n" + 
				  "                    \"data\": \"url\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			},\r\n" + 
				  "        			{\r\n" + 
				  "            			\"action\": {\r\n" + 
				  "                			\"dialerAction\": {\r\n" + 
				  "                    			\"dialPhoneNumber\": {\r\n" + 
				  "                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
				  "                    			}\r\n" + 
				  "                				},\r\n" + 
				  "                				\"displayText\": \"打电话\",\r\n" + 
				  "                				\"postback\": {\r\n" + 
				  "                    			\"data\": \"mobile\"\r\n" + 
				  "                			}\r\n" + 
				  "            			}\r\n" + 
				  "        			}\r\n" + 
				  "    			]\r\n" + 
			  	  "            }\r\n" + 
			  	  "        }\r\n" + 
			  	  "    }\r\n" + 
			  	  "}\r\n" + 
				  "--next\r\n" + 
				  "Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				  "\r\n" + 
				  "{\r\n" + 
				  "    \"suggestions\": [\r\n" + 
				  "        {\r\n" + 
				  "            \"reply\": {\r\n" + 
				  "                \"displayText\": \"详情\",\r\n" + 
				  "                \"postback\": {\r\n" + 
				  "                    \"data\": \"more\"\r\n" + 
				  "                }\r\n" + 
				  "            }\r\n" + 
				  "        },\r\n" + 
				  "        {\r\n" + 
				  "            \"action\": {\r\n" + 
				  "                \"urlAction\": {\r\n" + 
				  "                    \"openUrl\": {\r\n" + 
				  "                        \"url\": \"https://www.baidu.com\"\r\n" + 
				  "                    }\r\n" + 
				  "                },\r\n" + 
				  "                \"displayText\": \"打开百度\",\r\n" + 
				  "                \"postback\": {\r\n" + 
				  "                    \"data\": \"url\"\r\n" + 
				  "                }\r\n" + 
				  "            }\r\n" + 
				  "        },\r\n" + 
				  "        {\r\n" + 
				  "            \"action\": {\r\n" + 
				  "                \"dialerAction\": {\r\n" + 
				  "                    \"dialPhoneNumber\": {\r\n" + 
				  "                        \"phoneNumber\": \"+8615811491455\"\r\n" + 
				  "                    }\r\n" + 
				  "                },\r\n" + 
				  "                \"displayText\": \"打电话\",\r\n" + 
				  "                \"postback\": {\r\n" + 
				  "                    \"data\": \"mobile\"\r\n" + 
				  "                }\r\n" + 
				  "            }\r\n" + 
				  "        }\r\n" + 
				  "    ]\r\n" + 
				  "}\r\n" + 
				  "--next--\r\n" + 
				  "]]></bodyText>\r\n" + 
				  "        <reportRequest>Delivered</reportRequest>\r\n" + 
				  "        <reportRequest>Displayed</reportRequest>\r\n" + 
				  "        <reportRequest>Failed</reportRequest>\r\n" + 
				  "    </outboundIMMessage>\r\n" + 
				  "</msg:outboundMessageRequest>";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  
	  /**
	   * chatbot发送消息（群发消息）接口（多卡片消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupMultipleCardMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  
		 String body = "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
			  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
			  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
			  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
			  		"<outboundIMMessage>\r\n" + 
			  		"<contentType>application/vnd.gsma.botmessage.v1.0+json</contentType>" + 
			  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
			  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" +  
			 		"<serviceCapability>\r\n" + 
			 		"<capabilityId>ChatbotSA</capabilityId>\r\n" + 
			 		"<version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
			 		"</serviceCapability>\r\n" + 
			 		"<bodyText><![CDATA[\r\n" + 
			 		"{\r\n" + 
			 		"    \"message\": {\r\n" + 
			 		"        \"generalPurposeCardCarousel\": {\r\n" + 
			 		"            \"layout\": {\r\n" + 
			 		"                \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
			 		"            },\r\n" + 
			 		"            \"content\": [\r\n" + 
			 		"                {\r\n" + 
			 		"                    \"media\": {\r\n" + 
			 		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300FD.png\",\r\n" + 
			 		"                    \"mediaContentType\": \"image/png\",\r\n" + 
			 		"                    \"mediaFileSize\": 3246,\r\n" + 
			 		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10010/s/11181626401132290261087300TD\",\r\n" + 
			 		"                    \"thumbnailContentType\": \"image/png\",\r\n" + 
			 		"                    \"thumbnailFileSize\": 1132,\r\n" + 
			 		"                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
			 		"                	},\r\n" + 
			 		"                    \"title\": \"多卡片消息体\",\r\n" + 
			 		"                    \"description\": \"图片\",\r\n" + 
			 		"                    \"suggestions\": [\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"reply\": {\r\n" + 
			 		"	                			\"displayText\": \"详情\",\r\n" + 
			 		"	                			\"postback\": {\r\n" + 
			 		"	                    		\"data\": \"more\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                		\"urlAction\": {\r\n" + 
			 		"	                    \"openUrl\": {\r\n" + 
			 		"	                        \"url\": \"https://www.baidu.com\"\r\n" + 
			 		"	                    }\r\n" + 
			 		"	                },\r\n" + 
			 		"	                \"displayText\": \"打开百度\",\r\n" + 
			 		"	                \"postback\": {\r\n" + 
			 		"	                    \"data\": \"url\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                			\"dialerAction\": {\r\n" + 
			 		"	                    			\"dialPhoneNumber\": {\r\n" + 
			 		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
			 		"	                    			}\r\n" + 
			 		"	                				},\r\n" + 
			 		"	                				\"displayText\": \"打电话\",\r\n" + 
			 		"	                				\"postback\": {\r\n" + 
			 		"	                    			\"data\": \"mobile\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			}\r\n" + 
			 		"	    			]\r\n" + 
			 		"                },\r\n" + 
			 		"                {\r\n" + 
			 		"                    \"media\": {\r\n" + 
			 		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10007/s/11182015081132440460878338FD.mp3\",\r\n" + 
			 		"                    \"mediaContentType\": \"video/mp3\",\r\n" + 
			 		"                    \"mediaFileSize\": 56495,\r\n" + 
			 		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/11181920121132340460385505TD\",\r\n" + 
			 		"                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
			 		"                    \"thumbnailFileSize\": 2377,\r\n" + 
			 		"                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
			 		"                	},\r\n" + 
			 		"                    \"title\": \"多卡片消息体\",\r\n" + 
			 		"                    \"description\": \"音频\",\r\n" + 
			 		"                    \"suggestions\": [\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"reply\": {\r\n" + 
			 		"	                			\"displayText\": \"详情\",\r\n" + 
			 		"	                			\"postback\": {\r\n" + 
			 		"	                    		\"data\": \"more\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                		\"urlAction\": {\r\n" + 
			 		"	                    \"openUrl\": {\r\n" + 
			 		"	                        \"url\": \"https://www.baidu.com\"\r\n" + 
			 		"	                    }\r\n" + 
			 		"	                },\r\n" + 
			 		"	                \"displayText\": \"打开百度\",\r\n" + 
			 		"	                \"postback\": {\r\n" + 
			 		"	                    \"data\": \"url\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                			\"dialerAction\": {\r\n" + 
			 		"	                    			\"dialPhoneNumber\": {\r\n" + 
			 		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
			 		"	                    			}\r\n" + 
			 		"	                				},\r\n" + 
			 		"	                				\"displayText\": \"打电话\",\r\n" + 
			 		"	                				\"postback\": {\r\n" + 
			 		"	                    			\"data\": \"mobile\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			}\r\n" + 
			 		"	    			]\r\n" + 
			 		"                },\r\n" + 
			 		"                {\r\n" + 
			 		"                    \"media\": {\r\n" + 
			 		"                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523FD.mp4\",\r\n" + 
			 		"                    \"mediaContentType\": \"video/mp4\",\r\n" + 
			 		"                    \"mediaFileSize\": 4246316,\r\n" + 
			 		"                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/11181923541132300460183523TD\",\r\n" + 
			 		"                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
			 		"                    \"thumbnailFileSize\": 4067,\r\n" + 
			 		"                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
			 		"                	},\r\n" + 
			 		"                    \"title\": \"多卡片消息体\",\r\n" + 
			 		"                    \"description\": \"视频\",\r\n" + 
			 		"                    \"suggestions\": [\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"reply\": {\r\n" + 
			 		"	                			\"displayText\": \"详情\",\r\n" + 
			 		"	                			\"postback\": {\r\n" + 
			 		"	                    		\"data\": \"more\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                		\"urlAction\": {\r\n" + 
			 		"	                    \"openUrl\": {\r\n" + 
			 		"	                        \"url\": \"https://www.baidu.com\"\r\n" + 
			 		"	                    }\r\n" + 
			 		"	                },\r\n" + 
			 		"	                \"displayText\": \"打开百度\",\r\n" + 
			 		"	                \"postback\": {\r\n" + 
			 		"	                    \"data\": \"url\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			},\r\n" + 
			 		"	        			{\r\n" + 
			 		"	            			\"action\": {\r\n" + 
			 		"	                			\"dialerAction\": {\r\n" + 
			 		"	                    			\"dialPhoneNumber\": {\r\n" + 
			 		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
			 		"	                    			}\r\n" + 
			 		"	                				},\r\n" + 
			 		"	                				\"displayText\": \"打电话\",\r\n" + 
			 		"	                				\"postback\": {\r\n" + 
			 		"	                    			\"data\": \"mobile\"\r\n" + 
			 		"	                			}\r\n" + 
			 		"	            			}\r\n" + 
			 		"	        			}\r\n" + 
			 		"	    			]\r\n" + 
			 		"                }\r\n" + 
			 		"            ]\r\n" + 
			 		"        }\r\n" + 
			 		"    }\r\n" + 
			 		"}\r\n" + 
			 		"]]></bodyText>\r\n" + 
			 		"		<reportRequest>Delivered</reportRequest>\r\n" + 
			 		"        <reportRequest>Displayed</reportRequest>\r\n" + 
			 		"        <reportRequest>Failed</reportRequest>\r\n" + 
			 		"  </outboundIMMessage>\r\n" + 
			 		"  <clientCorrelator>567895</clientCorrelator>\r\n" + 
			 		"</msg:outboundMessageRequest>\r\n" + 
			 		"";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
	  /**
	   * chatbot发送消息（群发消息）接口（带悬浮菜单的多卡片消息）
	   * 
	   * @param token
	   * @return 返回操作结果
	   * @throws Exception
	   */
	  public String testSendGroupMultipleCardSuggestionsMessage(String token,String mobile) throws Exception {
		  //http://{serverRoot}/messaging/group/{apiVersion}/outbound/{chatbotURI}/requests
		  StringBuilder builder = new StringBuilder(); 
		  builder.append(serverRoot)
		  .append("/").append("messaging")
		  .append("/").append("group")
		  .append("/").append(apiVersion) 
		  .append("/").append("outbound")
		  .append("/").append(chatbotId)
		  .append("/").append("requests");
		  String url = builder.toString();
		  long time = System.currentTimeMillis();
		  String date = long2gmt(time);
		  System.out.println(date);
		  String authorization = getAuthentication(token,date);
		  HttpHeaders headers = new HttpHeaders();
		  headers.add("Authorization", "Basic "+authorization);
		  headers.setContentType(MediaType.APPLICATION_XML);
		  headers.add("Date", date);
		  
		  String body = "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
			  		"  <address>tel:+86"+mobile+"</address>\r\n" + 
			  		"  <destinationAddress>tel:+86"+mobile+"</destinationAddress>\r\n" + 
			  		"  <senderAddress>"+chatbotId+"</senderAddress>\r\n" + 
			  		"<outboundIMMessage>\r\n" + 
			  		"<contentType>multipart/mixed; boundary=\"next\"</contentType>\r\n" +
			  		"<conversationID>"+UUID.randomUUID().toString()+"</conversationID>\r\n" + 
			  		"<contributionID>"+UUID.randomUUID().toString()+"</contributionID>\r\n" + 
		  		"<serviceCapability>\r\n" + 
		  		"<capabilityId>ChatbotSA</capabilityId>\r\n" + 
		  		"<version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
		  		"</serviceCapability>\r\n" + 
		  		"<bodyText><![CDATA[\r\n" + 
		  		"--next\r\n" + 
		  		"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
//		  		"Content-Disposition: inline; filename=\"Message\"\r\n" + 
		  		"Content-Length: 2000\r\n" + 
		  		"\r\n" + 
		  		"{\r\n" + 
		  		"    \"message\": {\r\n" + 
		  		"        \"generalPurposeCardCarousel\": {\r\n" + 
		  		"            \"layout\": {\r\n" + 
		  		"                \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
		  		"            },\r\n" + 
		  		"            \"content\": [\r\n" + 
		  		"                {\r\n" + 
		  		"                    \"media\": {\r\n" + 
		  		"	                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10011/s/12161804181132510291100688FD.jpg\",\r\n" + 
		  		"	                    \"mediaContentType\": \"image/jpeg\",\r\n" + 
		  		"	                    \"mediaFileSize\": 2035,\r\n" + 
		  		"	                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10011/s/12161804181132510291100688TD\",\r\n" + 
		  		"	                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
		  		"	                    \"thumbnailFileSize\": 1834,\r\n" + 
		  		"	                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
		  		"                	},\r\n" + 
		  		"                    \"title\": \"带悬浮菜单的多卡片消息体\",\r\n" + 
		  		"                    \"description\": \"图片\",\r\n" + 
		  		"                    \"suggestions\": [\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"reply\": {\r\n" + 
		  		"	                			\"displayText\": \"了解更多\",\r\n" + 
		  		"	                			\"postback\": {\r\n" + 
		  		"	                    		\"data\": \"more\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                		\"urlAction\": {\r\n" + 
		  		"	                    \"openUrl\": {\r\n" + 
		  		"	                        \"url\": \"https://cloud.baidu.com\"\r\n" + 
		  		"	                    }\r\n" + 
		  		"	                },\r\n" + 
		  		"	                \"displayText\": \"打开百度云\",\r\n" + 
		  		"	                \"postback\": {\r\n" + 
		  		"	                    \"data\": \"url\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                			\"dialerAction\": {\r\n" + 
		  		"	                    			\"dialPhoneNumber\": {\r\n" + 
		  		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
		  		"	                    			}\r\n" + 
		  		"	                				},\r\n" + 
		  		"	                				\"displayText\": \"客服电话\",\r\n" + 
		  		"	                				\"postback\": {\r\n" + 
		  		"	                    			\"data\": \"mobile\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			}\r\n" + 
		  		"	    			]\r\n" + 
		  		"                },\r\n" + 
		  		"                {\r\n" + 
		  		"                    \"media\": {\r\n" + 
		  		"	                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10006/s/12161837441132390450500284FD.mp3\",\r\n" + 
		  		"	                    \"mediaContentType\": \"video/mp3\",\r\n" + 
		  		"	                    \"mediaFileSize\": 56495,\r\n" + 
		  		"	                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10006/s/12161837441132390450500284TD\",\r\n" + 
		  		"	                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
		  		"	                    \"thumbnailFileSize\": 1911,\r\n" + 
		  		"	                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
		  		"                	},\r\n" + 
		  		"                    \"title\": \"带悬浮菜单的多卡片消息体\",\r\n" + 
		  		"                    \"description\": \"音频\",\r\n" + 
		  		"                    \"suggestions\": [\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"reply\": {\r\n" + 
		  		"	                			\"displayText\": \"详情\",\r\n" + 
		  		"	                			\"postback\": {\r\n" + 
		  		"	                    		\"data\": \"more\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                		\"urlAction\": {\r\n" + 
		  		"	                    \"openUrl\": {\r\n" + 
		  		"	                        \"url\": \"https://cloud.baidu.com\"\r\n" + 
		  		"	                    }\r\n" + 
		  		"	                },\r\n" + 
		  		"	                \"displayText\": \"打开百度云\",\r\n" + 
		  		"	                \"postback\": {\r\n" + 
		  		"	                    \"data\": \"url\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                			\"dialerAction\": {\r\n" + 
		  		"	                    			\"dialPhoneNumber\": {\r\n" + 
		  		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
		  		"	                    			}\r\n" + 
		  		"	                				},\r\n" + 
		  		"	                				\"displayText\": \"客服电话\",\r\n" + 
		  		"	                				\"postback\": {\r\n" + 
		  		"	                    			\"data\": \"mobile\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			}\r\n" + 
		  		"	    			]\r\n" + 
		  		"                },\r\n" + 
		  		"                {\r\n" + 
		  		"                    \"media\": {\r\n" + 
		  		"	                    \"mediaUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10012/s/12161840321132530021200682FD.mp4\",\r\n" + 
		  		"	                    \"mediaContentType\": \"video/mp4\",\r\n" + 
		  		"	                    \"mediaFileSize\": 4246316,\r\n" + 
		  		"	                    \"thumbnailUrl\": \"https://gz01ft.sbc.rcs.chinamobile.com:10012/s/12161840321132530021200682TD\",\r\n" + 
		  		"	                    \"thumbnailContentType\": \"image/jpeg\",\r\n" + 
		  		"	                    \"thumbnailFileSize\": 4067,\r\n" + 
		  		"	                    \"height\": \"SHORT_HEIGHT\"\r\n" + 
		  		"                	},\r\n" + 
		  		"                    \"title\": \"带悬浮菜单的多卡片消息体\",\r\n" + 
		  		"                    \"description\": \"视频\",\r\n" + 
		  		"                    \"suggestions\": [\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"reply\": {\r\n" + 
		  		"	                			\"displayText\": \"详情\",\r\n" + 
		  		"	                			\"postback\": {\r\n" + 
		  		"	                    		\"data\": \"more\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                		\"urlAction\": {\r\n" + 
		  		"	                    \"openUrl\": {\r\n" + 
		  		"	                        \"url\": \"https://cloud.baidu.com\"\r\n" + 
		  		"	                    }\r\n" + 
		  		"	                },\r\n" + 
		  		"	                \"displayText\": \"打开百度云\",\r\n" + 
		  		"	                \"postback\": {\r\n" + 
		  		"	                    \"data\": \"url\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			},\r\n" + 
		  		"	        			{\r\n" + 
		  		"	            			\"action\": {\r\n" + 
		  		"	                			\"dialerAction\": {\r\n" + 
		  		"	                    			\"dialPhoneNumber\": {\r\n" + 
		  		"	                        			\"phoneNumber\": \"+8615811491455\"\r\n" + 
		  		"	                    			}\r\n" + 
		  		"	                				},\r\n" + 
		  		"	                				\"displayText\": \"客服电话\",\r\n" + 
		  		"	                				\"postback\": {\r\n" + 
		  		"	                    			\"data\": \"mobile\"\r\n" + 
		  		"	                			}\r\n" + 
		  		"	            			}\r\n" + 
		  		"	        			}\r\n" + 
		  		"	    			]\r\n" + 
		  		"                }\r\n" + 
		  		"            ]\r\n" + 
		  		"        }\r\n" + 
		  		"    }\r\n" + 
		  		"}\r\n" + 
		  		"\r\n" + 
		  		"--next\r\n" + 
		  		"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//		  		"Content-Disposition: inline; filename=\"Chiplist.lst\"\r\n" + 
		  		"Content-Length: 1234\r\n" + 
		  		"\r\n" + 
		  		"{\r\n" + 
		  		"	\"suggestions\": [\r\n" + 
		  		"		{\r\n" + 
		  		"			\"reply\": {\r\n" + 
		  		"    			\"displayText\": \"详情\",\r\n" + 
		  		"    			\"postback\": {\r\n" + 
		  		"        			\"data\": \"more\"\r\n" + 
		  		"    			}\r\n" + 
		  		"			}\r\n" + 
		  		"		},\r\n" + 
		  		"		{\r\n" + 
		  		"			\"action\": {\r\n" + 
		  		"    		\"urlAction\": {\r\n" + 
		  		"        	\"openUrl\": {\r\n" + 
		  		"            \"url\": \"https://cloud.baidu.com\"\r\n" + 
		  		"        	}\r\n" + 
		  		"        },\r\n" + 
		  		"        \"displayText\": \"打开百度云\",\r\n" + 
		  		"        \"postback\": {\r\n" + 
		  		"            \"data\": \"url\"\r\n" + 
		  		"        			}\r\n" + 
		  		"    			}\r\n" + 
		  		"			},\r\n" + 
		  		"			{\r\n" + 
		  		"			\"action\": {\r\n" + 
		  		"    			\"dialerAction\": {\r\n" + 
		  		"        			\"dialPhoneNumber\": {\r\n" + 
		  		"            			\"phoneNumber\": \"+8615811491455\"\r\n" + 
		  		"        			}\r\n" + 
		  		"    				},\r\n" + 
		  		"    				\"displayText\": \"客服电话\",\r\n" + 
		  		"    				\"postback\": {\r\n" + 
		  		"        			\"data\": \"mobile\"\r\n" + 
		  		"    			}\r\n" + 
		  		"			}\r\n" + 
		  		"		}\r\n" + 
		  		"    ]	\r\n" + 
		  		"}\r\n" + 
		  		"--next--\r\n" + 
		  		"]]></bodyText>\r\n" + 
		  		"		<reportRequest>Delivered</reportRequest>\r\n" + 
		  		"        <reportRequest>Displayed</reportRequest>\r\n" + 
		  		"        <reportRequest>Failed</reportRequest>\r\n" + 
		  		"  </outboundIMMessage>\r\n" + 
		  		"  <clientCorrelator>567895</clientCorrelator>\r\n" + 
		  		"</msg:outboundMessageRequest>\r\n" + 
		  		"";
		  
		  HttpEntity<String> request = new HttpEntity<String>(body, headers);
		  RestTemplate restTemplate = getRestTemplate();
		  
		  ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
		  System.out.println(response.getStatusCodeValue());
		  System.out.println(response.getHeaders());
		  System.out.println(response.getBody());
		  
		  JSONObject json = JSONObject.parseObject(response.getBody()); 
		  return json.getString("code"); 
	  }
}
