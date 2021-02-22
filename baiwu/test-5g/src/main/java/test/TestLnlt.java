package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
		
		//辽宁联通上传临时文件，没有审核通知，只有永久素材会审核
		//上传文件
//		t.uploadFile("file/audio2.amr");
		
//		t.uploadFile("file/thumbnail2.jpg");
		
//		t.uploadFile("file/image2.png");
//		t.uploadFile("file/thumbnail2.jpg");
		
//		t.uploadFile("file/video1.mp4");
		
		//下载文件
//		t.downloadFile("http://47.103.149.126:8001/bot/v1/medias/fid/523007480902950912");
		//删除文件
//		t.deleteFile("http://47.103.149.126:8001/bot/v1/medias/fid/523007480902950912");
		
		//1、下行文本消息 
		t.sendText();
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
//		t.sendRevoke(UUID.randomUUID().toString() + t.getchatbotSip());
		
		//上传素材
//		t.uploadFile("D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\image2.png");
//		t.uploadFile("D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\thumbnail2.jpg");
	}
	
	Map<String,Object> getMutlCardContent3(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/523006452186980352");
		media.put("mediaContentType", "video/mp4");
		media.put("mediaFileSize", "660976");
		media.put("thumbnailUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
		media.put("thumbnailContentType", "image/jpg");
		media.put("thumbnailFileSize", "9216");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "这是一个卡片消息");
		content.put("description", "这是卡片消息的描述，如果超过了卡片的最大宽度和高度，描述内容会被省略");
		
		content.put("suggestions", Arrays.asList(getReply("确定"),getReply("取消"),getUrlAction(),getDialerAction()));
		return content;
	}
	
	Map<String,Object> getMutlCardContent2(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522995847191306240");
		media.put("mediaContentType", "audio/amr");
		media.put("mediaFileSize", "2758");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "这是一个卡片消息");
		content.put("description", "这是卡片消息的描述，如果超过了卡片的最大宽度和高度，描述内容会被省略");
		
		content.put("suggestions", Arrays.asList(getReply("确定"),getReply("取消"),getUrlAction(),getDialerAction()));
		return content;
	}
	
	Map<String,Object> getMutlCardContent1(){
		Map<String,Object> content = new HashMap<String,Object>();
		Map<String,Object> media = new HashMap<String,Object>();
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522989500022366208");
		media.put("mediaContentType", "image/png");
		media.put("mediaFileSize", "171008");
		media.put("thumbnailUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
		media.put("thumbnailContentType", "image/jpg");
		media.put("thumbnailFileSize", "9216");
		media.put("height", "SHORT_HEIGHT");
		
		content.put("media", media);
		content.put("title", "这是一个卡片消息");
		content.put("description", "这是卡片消息的描述，如果超过了卡片的最大宽度和高度，描述内容会被省略");
		
		content.put("suggestions", Arrays.asList(getReply("确定"),getReply("取消"),getUrlAction(),getDialerAction()));
		return content;
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
		
		media.put("mediaUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522989500022366208");
		media.put("mediaContentType", "image/png");
		media.put("mediaFileSize", "171008");
		media.put("thumbnailUrl", "http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
		media.put("thumbnailContentType", "image/jpg");
		media.put("thumbnailFileSize", "9216");
		
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
		file.put("fileSize","171008");
		file.put("fileName","image2.png");
		file.put("contentType","image/png");
		file.put("url","http://47.103.149.126:8001/bot/v1/medias/fid/522989500022366208");
//		file.put("until","2021-02-12T11:10:37Z");
		contentText.add(file);
		
		Map<String,Object> thumbnail = new HashMap<>();
		thumbnail.put("type","thumbnail");
		thumbnail.put("fileSize","9216");
		thumbnail.put("fileName","imaget2.jpg");
		thumbnail.put("contentType","image/jpg");
		thumbnail.put("url","http://47.103.149.126:8001/bot/v1/medias/fid/522991713738268672");
//		thumbnail.put("until","2021-02-12T11:12:28Z");
		contentText.add(thumbnail);
		result.put("contentText", contentText);
		
		return result;
	}
	
	String getAccessToken() {
		return this.accessToken = "accessToken " + super.getAccessToken();
	}
}


