package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.tomcat.util.security.MD5Encoder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.CloseUtil;
import util.DateUtil;

public class TestBjdxMannager {
	protected OkHttpClient client = new OkHttpClient().newBuilder().sslSocketFactory(createSSLSocketFactory()).hostnameVerifier(new TrustAllHostnameVerifier()).build();
	
	private String accessTokenUrl = "https://isptest.189.cn/cspApi/v1/accessToken";
	private String uploadCustomerFileUrl = "https://isptest.189.cn/cspApi/v1/uploadFile";
	private String addCustomerUrl = "https://isptest.189.cn/cspApi/v1/cspCustomer/addcspCustomer";
	private String selectCustomerUrl = "https://isptest.189.cn/cspApi/v1/selectCspCustomer";
	private String uploadChatbotFileUrl = "https://isptest.189.cn/cspApi/v1/Chatbot/uploadChatbotFile";
	private String addChatbotUrl = "https://isptest.189.cn/cspApi/v1/Chatbot/addChatbot";
	private String updateDeveloperUrl = "https://isptest.189.cn/cspApi/v1/developerConfiguration/updateDeveloper";
	private String updateOnlineUrl = "https://isptest.189.cn/cspApi/v1/Chatbot/isOnlineUpdate";
	
	private String cspId;
	private String accessKey;
	private String timestamp;
	private String nonce;
	private String signature;
	
	private String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJDMTIxMDAwMDA2IiwiaWF0IjoxNjExODg4NTcwLCJzdWIiOiJsbkdpdktfWlUhSDFuUSpOZno4ITIyQGE3IiwiZXhwIjoxNjExODg4NTc3fQ.q9S-XR_PeB6oOarKFShZC9hqJnUpEXqp0iSfXtCfg6A";
	
	public TestBjdxMannager(String cspId,String accessKey) {
		this.cspId = cspId;
		this.accessKey = accessKey;
		this.timestamp = getDate();
		this.nonce = getNonce();
		this.signature = getSignature();
		
	}
	
	public static void main(String[] args) {
		String scpId = "C121000006";
		String cspSecret = "lnGivK_ZU!H1nQ*Nfz8!22@a7";
		String cspToken = "-gGM#-UG?YJutkB04ZO8";
		
		TestBjdxMannager t = new TestBjdxMannager(scpId,cspSecret);
		t.accessToken = t.getAccessToken();
		//上传客户图片
//		t.uploadFile("0","D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\logo.png");
		//上传省份证正反面
//		t.uploadFile("1","D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\法人身份证正面.png");
//		t.uploadFile("1","D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\法人身份证反面.png");
		//合同
//		t.uploadCustomerFile("2","D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\营业执照.jpg");
		
		//新增客户
//		t.addCustomer();
		//查询客户
//		t.selectCustomer();
		
		//上传chatbot资料
		//logo图片
//		t.uploadChatbotFile("0","D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\logo.png");
//		//Chatbot 背景图片
//		t.uploadChatbotFile("1", "D:\\zal\\bw\\5G消息\\北京电信\\北京电信5G消息CSP资料\\背景.png");
		
		//新增chatbot
//		t.addChatbot();
		
		//上线chatbot
//		t.updateOnline();
		
		//开发者配置
		t.updateDeveloper();
	}
	
	public String updateOnline() {
		System.out.println("变更上线状态：");
		String url = updateOnlineUrl;
		
		Map<String,String> map = new HashMap<>();
		map.put("accessTagNo", "a7d256eab9cf45a8be92972f6d78cf96");
		map.put("type", "1");
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("content-type","application/json")
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build();
		String response = request(request);
		writeFile("beijingDx-updateOnline",response);
		return response;
	}
	
	public String updateDeveloper() {
		System.out.println("开发者配置：");
		String url = updateDeveloperUrl;
		
		Map<String,String> map = new HashMap<>();
		map.put("agreement", "2");//协议 1:http，2:https
		map.put("token", "8mZQD4inG98YW5");//开发者 token，最大 16 位
		map.put("url", "http://124.239.146.131:9090/rcs/api/dx/bjdx/callback");//回调 URL 地址根目录 以 http://开头，IP+端口的形式，用来接收下行消息状态报告以及消息通知
		map.put("accessTagNo", "a7d256eab9cf45a8be92972f6d78cf96");//新增 Chatbot 时,返回的 Chatbot 的唯一识别标识
		map.put("enable", "1");//Chatbot 接口是否启用，1:启用，0:不启用
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("content-type","application/json")
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build();
		String response = request(request);
		writeFile("beijingDx-updateDeveloper",response);
		return response;
	}
	
	public String addChatbot() {
		System.out.println("新增chatbot：");
		String url = addChatbotUrl;
		
		Map<String,Object> map = new HashMap<>();
		map.put("cspId", "C121000006");//CSP 编码
		map.put("cspEcNo", "C12100000600001");//客户识别码
		map.put("chatbotId", "106598858810000006");//ChatbotId：接入号码+自定义号码（ChatbotId 一旦创建不可修改）
		map.put("serviceName", "集微科技");//Chatbot 服务名称
		map.put("serviceIcon", "https://maaptest.189.cn/maap_message/bot/chanageUrl/head/20210130173059/5355/3,41565fa3f6fc.png");//Chatbot logo 路径
		//Chatbot 描述信息
		map.put("serviceDescription", "厦门集微科技有限公司致力于为广大客户提供更专业的全球化企业通信服务，是以5G、互联网+、物联网+及人工智能等高科技为驱动的国家高新技术企业。");
		map.put("SMSNumber", "106598858810000006");//Chatbot 短信端口号=chatbotId 中(接入号码+自定义号码)
		map.put("autograph", "集微科技");//Chatbot 签名
		map.put("category", new String[] {"1"});//Chatbot 分类即行业类别分类，详见行业类型字典表，当前只支持单一行业类型
		map.put("provider", "厦门集微科技有限公司");//Chatbot 提供者名称
		map.put("showProvider", "1");//是否显示Chatbot提供者名称，1：显示，0：不显示
		map.put("TCPage", "http://www.jiweitech.com");//Chatbot 服务条款
		map.put("emailAddress", "2638343851@qq.com");//Chatbot 邮箱
		map.put("serviceWebsite", "http://www.jiweitech.com");//Chatbot 官网(主页地址)
		map.put("callBackNumber", "0592-6190616");//Chatbot 服务电话
		map.put("address", "福建省厦门市集美区");//Chatbot 办公地址
		map.put("longitude", "118.049");//Chatbot 地理经度
		map.put("latitude", "24.621");//Chatbot 地理纬度
		map.put("ipWhiteList", new String[] {"124.239.146.131","36.110.62.178"});//IP 地址白名单 (最多 15 个)
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("content-type","application/json")
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build();
		String response = request(request);
		writeFile("beijingDx-addChatbot",response);
		return response;
	}
	
	public String uploadChatbotFile(String uploadType,String filePath) {
		System.out.println("上传chatbot资料：");
		String url = uploadChatbotFileUrl;
		
		File file = new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(file,MediaType.parse("image/png")));
        
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("uploadType", uploadType)
				.addHeader("content-type","application/json")
				.post(builder.build())
				.build();
		String response = request(request);
		writeFile("beijingDx-uploadChatbotFile",filePath + " : " + response);
		return response;
	}
	
	public String selectCustomer() {
		System.out.println("查询客户资料：");
		String url = selectCustomerUrl;
		
		Map<String,String> map = new HashMap<>();
		map.put("cspEcNo", "C12100000600001");
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("content-type","application/json")
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build();
		String response = request(request);
		writeFile("beijingDx-selectCustomer",response);
		return response;
	}
	
	public String addCustomer() {
		System.out.println("上传客户资料：");
		String url = addCustomerUrl;
		
		Map<String,Object> map = new HashMap<>();
		//客户基本信息
		Map<String,String> rcsRegisterInfo = new HashMap<>();
		rcsRegisterInfo.put("ecName", "集微科技"); //客户名称
		rcsRegisterInfo.put("businessType", "1");//行业类型
		rcsRegisterInfo.put("ecGrade", "3");//标准级
		map.put("rcsRegisterInfo",rcsRegisterInfo);
		
		//客户企业信息
		Map<String,Object> rcsInfo = new HashMap<>();
		//企业介绍
		rcsInfo.put("introduce", "厦门集微科技有限公司致力于为广大客户提供更专业的全球化企业通信服务，是以5G、互联网+、物联网+及人工智能等高科技为驱动的国家高新技术企业。公司主要为企业客户提供企业短信、企业语音客户服务热线（含95、1010、10195码号）、5G消息、物联网、集微私有云、人工智能、大象能力平台等产品服务，为移动互联网、金融、电商、交通运输等众多行业提供企业通信解决方案。\r\n" + 
				"集微科技是中国三大电信运营商及国际运营商的密切合作伙伴，在企业通信的技术平台、产品服务、行业解决方案等多领域实现了合作，并积累了丰富的企业通信服务经验。同时，集微致力于整合企业通信产品生态，承载企业通信支撑一条龙服务。\r\n" + 
				"截至目前，集微已为京东、58同城、国美在线、唯品会等诸多国内外知名企业提供企业通信服务，业务覆盖中国多个地区及境外，有效推动了通信技术与传统产业的跨界结合。\r\n");
		rcsInfo.put("serviceIcon", "http://172.17.25.10:9081/7,0f23bc123477");//企业 logo
		rcsInfo.put("workPhone", "0592-6190616");//办公电话
		rcsInfo.put("businessLicense", "http://172.17.25.10:9081/7,0f2878ebc4ef");//企业营业执照
		rcsInfo.put("businessAddress", "福建省厦门市集美区");//企业所在地
        rcsInfo.put("province", "福建省");//省份
		rcsInfo.put("city", "厦门市");//市
		rcsInfo.put("area", "集美区");//区
		rcsInfo.put("operatorName", "傅竞捷");//客户联系人
		rcsInfo.put("operatorCard", "350583198812276630");//客户联系人身份证号
		rcsInfo.put("operatorPhone", "18518258756");//客户联系人手机号
		rcsInfo.put("emailAddress", "2638343851@qq.com");//客户联系人邮箱
		//客户联系人身份证正反面文件地址
		rcsInfo.put("operatorIdentityPic", new String[]{"http://172.17.25.10:9081/7,0f26848e2376","http://172.17.25.10:9081/7,0f274f422229"});
        map.put("rcsInfo",rcsInfo);
        
        //合同信息
        Map<String,String> rcsContractInformation = new HashMap<>();
		rcsContractInformation.put("contractNo", "5G-JW-2015301");//合同编码
		rcsContractInformation.put("name", "集微科技有限公司5G消息业务合同");//合同名称
		rcsContractInformation.put("effectiveDate", "20210127152126");//合同 效日期 ，yyyyMMddHHmmss
		rcsContractInformation.put("expiryDate", "20220127152126");//合同失效日期 ，yyyyMMddHHmmss
		rcsContractInformation.put("status", "2");//合同是否续签(1.是 2.否)
//        rcsContractInformation.put("renewalDate", "");//合同续签日期 ，yyyyMMddHHmmss
		rcsContractInformation.put("accessory", "http://172.17.25.10:9081/7,0f2878ebc4ef");//合同附件，需使用客户资料上传接口返回的 url
        map.put("rcsContractInformation",rcsContractInformation);
        
        //客户法人信息
        Map<String,String> rcsLegalP = new HashMap<>();
		rcsLegalP.put("legalName", "傅竞捷");//法人名称
		rcsLegalP.put("legalIdentification", "350583198812276630");//法人身份证号
		rcsLegalP.put("identificationStraight", "http://172.17.25.10:9081/7,0f26848e2376");//法人身份证正面文件地址
		rcsLegalP.put("identificationCounter", "http://172.17.25.10:9081/7,0f274f422229");//法人身份证反面文件地址
        map.put("rcsLegalP",rcsLegalP);
		
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("content-type","application/json")
				.post(RequestBody.create(JSON.toJSONString(map),MediaType.parse("application/json")))
				.build();
		String response = request(request);
		writeFile("beijingDx-addCustomer",response);
		return response;
	}
	
	public String uploadCustomerFile(String uploadType,String filePath) {
		System.out.println("上传客户资料：");
		String url = uploadCustomerFileUrl;
		
		File file = new File(filePath);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        builder.addFormDataPart("file", file.getName(), RequestBody.create(file,MediaType.parse("image/png")));
        
		Request request = new Request.Builder()
				.url(url)
				.addHeader("timestamp", timestamp)
				.addHeader("nonce", nonce)
				.addHeader("signature", signature)
				.addHeader("authorization", accessToken)
				.addHeader("uploadType", uploadType)
				.addHeader("content-type","application/json")
				.post(builder.build())
				.build();
		String response = request(request);
		writeFile("beijingDx-uploadFile",filePath + " : " + response);
		return response;
	}
	
//	{"code":0,"data":{"accessToken":"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJDMTIxMDAwMDA2IiwiaWF0IjoxNjExNzEzMzM0LCJzdWIiOiJsbkdpdktfWlUhSDFuUSpOZno4ITIyQGE3IiwiZXhwIjoxNjExNzEzMzQxfQ.zFpeHNetNK0UZaVo4B0KfM4Tawjf_oOfuvlN0bqy6s4"},"message":"请求成功"}
	public String getAccessToken() {
		System.out.println("获取 accessToken:");
		Map<String,String> map = new HashMap<>();
		map.put("cspId", cspId);
		map.put("accessKey", accessKey);
		
		String url = accessTokenUrl;
		String data = JSON.toJSONString(map);
		Request request = new Request.Builder()
			.url(url)
			.addHeader("timestamp", timestamp)
			.addHeader("nonce", nonce)
			.addHeader("signature", signature)
			.post(RequestBody.create(data,MediaType.parse("application/json")))
			.build();
		String response = request(request);
		
		writeFile("beijingDx-getAccessToken", response);
		
		JSONObject jo = JSON.parseObject(response);
		return jo.getJSONObject("data").getString("accessToken");
	}
	
	String getDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}
	
	String getNonce() {
		return timestamp + getRandomString(8);
	}
	
	String getSignature() {
		return Hashing.md5().newHasher().putString(this.accessKey + this.nonce + this.timestamp, Charsets.UTF_8).hash().toString();
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
	void writeFile(String filename,String text) {
		File file = new File("file/" + filename);
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
        	fos = new FileOutputStream(file,true);
        	osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(DateUtil.getDateTime() + " : " + text); //写入内容
            osw.write("\r\n");  //换行
        }catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(osw);
			CloseUtil.close(fos);
		}
	}
}
