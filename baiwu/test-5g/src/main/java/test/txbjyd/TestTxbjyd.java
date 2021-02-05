package test.txbjyd;

import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import test.TestFjydtH5;
import util.SHA256Util;

public class TestTxbjyd extends TestFjydtH5{
	String fileserverroot = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer";
	String serverRoot = "http://112.35.162.232:8078/mbmp/developer/accesslayer";
	String apiVersion = "v1"; 
	public TestTxbjyd(String appid, String password) {
		super(appid,appid, password);
		this.client = new OkHttpClient().newBuilder().build();
	}

	public static void main(String[] args) {
		//中飞艾维5G消息 
		String appid = "125200401111833"; 
		String password = "lxB;6O(2ST|J~3b`qw";
		
		TestTxbjyd t = new TestTxbjyd(appid,password);
		
		//删除文件
//		t.requestDeleteFile("56fc8f836c3794d4a991d5501b4f832a6");
		//撤回消息
//		t.requestRevokeMsg("");
		
		t.phone = "15811491455";
		
		t.requestTextXml();
		
	}
	
	protected void request(String data) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = serverRoot + "/messaging/group/" + apiVersion + "/outbound/" + getchatbotSip() + "/requests";
		System.out.println(url);
		System.out.println("xml : ");
		System.out.println(data);
		System.out.println();
		System.out.println();
		request(data,x -> {
				RequestBody body = RequestBody.create(x, mediaTypeStr);
				return new Request.Builder()
						.url(url)
						.addHeader("Address", "+86" + phone)
						.addHeader("Authorization", authorization)
						.addHeader("Date", headerDate)
						.addHeader("Content-Type", "application/xml")
						.post(body)
						.build();
		});
	}
	
	
	void uploadFile() {
		
	}
	
	public void requestGroupMsg() {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = serverRoot + "/messaging/group/" + apiVersion + "/outbound/" + getchatbotSip() + "/requests";
		System.out.println(url);
		System.out.println("群发：" + "");
		System.out.println();
		System.out.println();
	}
	
	
	
	
	
	public void requestRevokeMsg(String messageId) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = serverRoot + "/messaging/" + apiVersion + "/outbound/" + getchatbotSip() + "/requests/" + messageId + "/status";
		System.out.println(url);
		System.out.println("撤回：" + messageId);
		System.out.println();
		System.out.println();
		
		request(messageId,x -> {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
					"<msg:messageStatusReport xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
					"    <status>RevokeRequested</status>\r\n" + 
					"    <address>tel:+86" + phone + "</address>\r\n" + 
					"</msg:messageStatusReport>\r\n";
			RequestBody body = RequestBody.create(xml, mediaTypeStr);
			return new Request.Builder()
					.url(url)
					.addHeader("Address", "+86" + phone)
					.addHeader("Authorization", authorization)
					.addHeader("Date", headerDate)
					.addHeader("Content-Type", "application/xml")
					.post(body)
					.build();
			});
	}
	public void requestDeleteFile(String tid) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer/Content";
		System.out.println(url);
		System.out.println("删除： " + tid);
		System.out.println();
		System.out.println();
		
		request(tid,x -> {
			return new Request.Builder()
					.url(url)
					.addHeader("Authorization", authorization)
					.addHeader("Date", headerDate)
					.addHeader("User-Agent", "SP/"+getchatbotSip())
					.addHeader("Terminal-type", "Chatbot")
					.addHeader("tid", x)
					.delete()
					.build();
			});
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
}
