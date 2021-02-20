package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class TestLnlt extends TestBjdx{
	public TestLnlt(String chatbotId,String appid,String password) {
		super(chatbotId,appid,password);
		this.pre = "liaoningLtChatbot-";
		this.serverRoot = "http://47.103.149.126:8001";
	}
	
	@Override
	protected String getchatbotSip() {
		return "sip:12580012011402@botplatform.rcs.chinaunicom.cn";
	}
	
	public static void main(String[] args) {
		//集微IoT
		String chatbotId = "12580012011402";
		String appid = "40c59571cd954e1eb602a5698aa9ddaa";
		String password = "9f39ffbd1576426b856506af248eab56";
		String accessToken = "jiweikeji1";
		
		TestLnlt t = new TestLnlt(chatbotId,appid,password);
//		t.accessToken = accessToken;
		t.init();
		t.getAccessToken();
		//联通号
		t.phone = "18611886117";
		//国芳联通手机
//		t.phone = "18518258286";
		//本奇联通手机
//		t.phone = "18501960660";

		
//		t.uploadFile("file/image2.png");
//		t.uploadFile("file/thumbnail2.jpg");
		
//		t.uploadFile("file/video1.mp4");
		
		//1、下行文本消息 
//		t.sendText();
		//2、下行文本消息+ 悬浮菜单 
//		t.sendSugText();
		//3、文件消息 
//		t.sendFile();
		//4、地理位置回落消息 
//		t.sendGeo();
		//5、富媒体单卡片消息(带 CSS 样式) + Suggestions 
//		t.sendSigleCard();
		//6、富媒体多卡片消息(带 CSS 样式) + Suggestions 
//		t.sendMultCard();
		
		//撤回消息
//		t.sendRevoke("");
		
		//上传素材
//		t.uploadFile("D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\image2.png");
//		t.uploadFile("D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\thumbnail2.jpg");
	}
	
	String getAccessToken() {
		return this.accessToken = "accessToken " + super.getAccessToken();
	}
}


