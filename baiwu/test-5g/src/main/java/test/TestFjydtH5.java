package test;

import java.util.Base64;
import java.util.UUID;

import util.SHA256Util;

public class TestFjydtH5 extends AbstractDemo{
	public TestFjydtH5(String chatbotId,String appid,String password) {
		super(chatbotId,appid,password);
		this.sendUrl = "https://test.andfx.net/chatbotim/chatbot/messaging/v1/outbound/" + getchatbotSip() + "/requests";
	}
	
	public static void main(String[] args) {
		//集微科技
		String chatbotId = "12520020000120";
		String appid = "iap_12520020000120";
		String password = "4f95d15126615625250926f3747dd5861b745927ce58051295989de43deeb120";
		
		TestFjydtH5 t = new TestFjydtH5(chatbotId,appid,password);
		//移动号码
		t.phone = "15811491455";
		
//		t.requestSugDuokpXml();
//		t.requestDuokpXml();
//		t.requestSugDkpXml();
//		t.requestDkpXml();
//		t.requestSugFileXml();
//		t.requestFileXml();
//		t.requestSugTextXml();
//		t.requestTextXml();
	}
	
	FileInfo getFileInfo() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<file\r\n" + 
				"    xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\"\r\n" + 
				"    xmlns:e=\"urn:gsma:params:xml:ns:rcs:rcs:up:fthttpext\">\r\n" + 
				"    <file-info type=\"thumbnail\">\r\n" + 
				"        <file-size>9216</file-size>\r\n" + 
				"        <content-type>image/jpg</content-type>\r\n" + 
				"        <data url=\"https://ft101.sh.5gm.wo.cn:10099/s/01181409311101410110100018TD\" until=\"2021-01-21T14:09:31Z\"/>\r\n" + 
				"    </file-info>\r\n" + 
				"    <file-info type=\"file\">\r\n" + 
				"        <file-size>171008</file-size>\r\n" + 
				"        <file-name>car.png</file-name>\r\n" + 
				"        <content-type>image/png</content-type>\r\n" + 
				"        <data url=\"https://ft101.sh.5gm.wo.cn:10099/s/01181409311101410110100018FD.png\" until=\"2021-01-21T14:09:31Z\"/>\r\n" + 
				"        <file-exist>1</file-exist>\r\n" + 
				"        <e:branded-url>https://1/s/W4eRJfAABqQ.png</e:branded-url>\r\n" + 
				"    </file-info>\r\n" + 
				"</file>";
		return readXml(xml);
	}
	
	protected void requestSugDuokpXml() {
		System.out.println("下发带建议回复的多卡片消息：");
		String duokp = getDuokp();
		String sug = getSug(getReply(),getUrlAction(),getDialerAction(),getMapAction(),getCalendarAction());
		
		String body = "--next\r\n" + 
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: " + duokp.getBytes().length + "\r\n\r\n" + 
				duokp + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" + 
				sug + "\r\n" +
				"--next--\r\n";
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	protected void requestSugDkpXml() {
		System.out.println("下发带建议回复的单卡片消息：");
		String dkp = getDkp();
		String sug = getSug(getReply(),getUrlAction(),getDialerAction(),getMapAction(),getCalendarAction());
		
		String body = "--next\r\n" + 
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: " + dkp.getBytes().length + "\r\n\r\n" + 
				dkp + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" + 
				sug + "\r\n" +
				"--next--\r\n";
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getDuokp() {
		FileInfo fileInfo = getFileInfo();
		
		String xml = "{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"generalPurposeCardCarousel\": {\r\n" + 
				"      \"layout\": {\r\n" + 
				"        \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
				"      },\r\n" + 
				"      \"content\": [\r\n" + 
				"        {\r\n" + 
				"          \"media\": {\r\n" + 
				"            \"mediaUrl\": \"" + fileInfo.fileUrl + "\",\r\n" + 
				"            \"mediaContentType\": \"" + fileInfo.fileType + "\",\r\n" + 
				"            \"thumbnailUrl\": \"" + fileInfo.thumbnailFileUrl + "\",\r\n" + 
				"            \"thumbnailContentType\": \"" + fileInfo.thumbnailFileType + "\",\r\n" + 
				"            \"height\": \"SHORT_HEIGHT\"\r\n" + 
				"          },\r\n" + 
				"          \"title\": \"This is the first rich card in a carousel.\",\r\n" + 
				"          \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\",\r\n" + 
				"          \"suggestions\": [\r\n" + 
				"            {\r\n" + 
				"              \"action\": {\r\n" + 
				"                \"mapAction\": {\r\n" + 
				"                  \"showLocation\": {\r\n" + 
				"                    \"location\": {\r\n" + 
				"                      \"latitude\": 37.4220041,\r\n" + 
				"                      \"longitude\": -122.0862515,\r\n" + 
				"                      \"label\": \"Googleplex\"\r\n" + 
				"                    },\r\n" + 
				"                    \"fallbackUrl\": \"https://www.google.com/maps/@37.4219162,-122.078063,15z\"\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                \"displayText\": \"Show location on a map\",\r\n" + 
				"                \"postback\": {\r\n" + 
				"                  \"data\": \"set_by_chatbot_open_map\"\r\n" + 
				"                }\r\n" + 
				"              }\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"              \"action\": {\r\n" + 
				"                \"calendarAction\": {\r\n" + 
				"                  \"createCalendarEvent\": {\r\n" + 
				"                    \"startTime\": \"2017-03-14T00:00:00Z\",\r\n" + 
				"                    \"endTime\": \"2017-03-14T23:59:59Z\",\r\n" + 
				"                    \"title\": \"Meeting\",\r\n" + 
				"                    \"description\": \"GSG review meeting\"\r\n" + 
				"                  }\r\n" + 
				"                },\r\n" + 
				"                \"displayText\": \"Schedule Meeting\",\r\n" + 
				"                \"postback\": {\r\n" + 
				"                  \"data\": \"set_by_chatbot_create_calendar_event\"\r\n" + 
				"                }\r\n" + 
				"              }\r\n" + 
				"            }\r\n" + 
				"          ]\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"          \"title\": \"This is the second rich card in the carousel.\",\r\n" + 
				"          \"description\": \"Carousel cards need to specify a card width in the 'layout' section. For small width cards, only short and medium height media are supported.\",\r\n" + 
				"          \"[...]\": \"[...]\"\r\n" + 
				"        }\r\n" + 
				"      ]\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}\r\n";
		return xml;
	}
	
	protected void requestDuokpXml() {
		System.out.println("下发多卡片消息：");
		String duokp = getDuokp();
		sendMessage(getXml("application/vnd.gsma.botmessage.v1.0+json", duokp));
	}
	
	String getDkp() {
		FileInfo fileInfo = getFileInfo();
		
		String xml = "{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"generalPurposeCard\": {\r\n" + 
				"      \"layout\": {\r\n" + 
				"        \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
				"        \"imageAlignment\": \"LEFT\"\r\n" + 
				"      },\r\n" + 
				"      \"content\": {\r\n" + 
				"        \"media\": {\r\n" + 
				"          \"mediaUrl\": \"" + fileInfo.fileUrl + "\",\r\n" + 
				"          \"mediaContentType\": \"" + fileInfo.fileType + "\",\r\n" + 
				"          \"thumbnailUrl\": \"" + fileInfo.thumbnailFileUrl + "\",\r\n" + 
				"          \"thumbnailContentType\": \"" + fileInfo.thumbnailFileType + "\",\r\n" + 
				"          \"height\": \"MEDIUM_HEIGHT\"\r\n" + 
				"        },\r\n" + 
				"        \"title\": \"This is a single rich card.\",\r\n" + 
				"        \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}\r\n";
		return xml;
	}
	
	protected void requestDkpXml() {
		System.out.println("下发单卡片消息：");
		String dkp = getDkp();
		sendMessage(getXml("application/vnd.gsma.botmessage.v1.0+json", dkp));
	}
	
	protected void requestSugFileXml() {
		System.out.println("下发带建议回复消息：");
		String file = getFile();
		String sug = getSug(getReply(),getUrlAction(),getDialerAction(),getMapAction(),getCalendarAction());
		
		String body = "--next\r\n" + 
				"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
				"Content-Length: " + file.length() + "\r\n\r\n" +
				file + "\r\n\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.length() + "\r\n\r\n" +
				sug + "\r\n" +
				"--next--\r\n";
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getFile() {
		FileInfo fileInfo = getFileInfo();
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" + 
				"    <file-info type=\"thumbnail\">\r\n" + 
				"        <file-size>" + fileInfo.thumbnailFileSize + "</file-size>\r\n" + 
				"        <content-type>" + fileInfo.thumbnailFileType + "</content-type>\r\n" + 
				"        <data url=\"" + fileInfo.thumbnailFileUrl + "\"/>\r\n" + 
				"    </file-info>\r\n" + 
				"    <file-info type=\"file\">\r\n" + 
				"        <file-size>" + fileInfo.fileSize +"</file-size>\r\n" + 
				"        <file-name>" + fileInfo.fileName + "</file-name>\r\n" + 
				"        <content-type>" + fileInfo.fileType + "</content-type>\r\n" + 
				"        <data url=\"" + fileInfo.fileUrl + "\"/>\r\n" + 
				"    </file-info>\r\n" + 
				"</file>";
		return xml;
	}
	
	protected void requestFileXml() {
		System.out.println("下发纯文件消息：");
		sendMessage(getXml("application/vnd.gsma.rcs-ft-http+xml", getFile()));
	}
	
	protected void requestTextXml() {
		System.out.println("下发纯文本消息：");
		String text = "hello word"; 
		sendMessage(getXml("text/plain",text));
	}
	
	public String getSug(String... action) {
		if(action.length == 0) {
			throw new RuntimeException();
		}
		String sug = "{\r\n" + 
				"  \"suggestions\": [\r\n";
		for(int i = 0; i < action.length; i++) {
			sug += action[i];
			sug += ",";
		}
		sug = sug.substring(0, sug.length() -1) +
		"  ]\r\n" + 
		"}\r\n";
		return sug;
	}
	
	String getReply() {
		String reply = "{\r\n" + 
				"  \"reply\": {\r\n" + 
				"    \"displayText\": \"同意\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_reply_agree\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return reply;
	}
	
	String getUrlAction() {
		String action = "{\r\n" + 
				"  \"action\": {\r\n" + 
				"    \"urlAction\": {\r\n" + 
				"      \"openUrl\": {\r\n" + 
				"        \"url\": \"https://www.baidu.com\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"displayText\": \"打开百度\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_open_url\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return action;
	}
	
	String getDialerAction() {
		String action = "{\r\n" + 
				"  \"action\": {\r\n" + 
				"    \"dialerAction\": {\r\n" + 
				"      \"dialPhoneNumber\": {\r\n" + 
				"        \"phoneNumber\": \"+8613800001111\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"displayText\": \"拨打电话\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_open_dialer\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return action;
	}
	
	String getMapAction() {
		String action = "{\r\n" + 
				"  \"action\": {\r\n" + 
				"    \"mapAction\": {\r\n" + 
				"      \"showLocation\": {\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"latitude\": 116.490061,\r\n" + 
				"          \"longitude\": 40.003678,\r\n" + 
				"          \"label\": \"百度地图\"\r\n" + 
				"        },\r\n" + 
				"        \"fallbackUrl\": \"https://map.baidu.com/@12967747.65,4839057.55,16z\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"displayText\": \"地图\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_open_map\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return action;
	}
	
	String getGoogleMapAction() {
		String action = "{\r\n" + 
				"  \"action\": {\r\n" + 
				"    \"mapAction\": {\r\n" + 
				"      \"showLocation\": {\r\n" + 
				"        \"location\": {\r\n" + 
				"          \"latitude\": 37.4220041,\r\n" + 
				"          \"longitude\": -122.0862515,\r\n" + 
				"          \"label\": \"Googleplex\"\r\n" + 
				"        },\r\n" + 
				"        \"fallbackUrl\": \"https://www.google.com/maps/@37.4219162,-122.078063,15z\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"displayText\": \"Show location on a map\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_open_map\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return action;
	}
	
	String getCalendarAction() {
		String action = "{\r\n" + 
				"  \"action\": {\r\n" + 
				"    \"calendarAction\": {\r\n" + 
				"      \"createCalendarEvent\": {\r\n" + 
				"        \"startTime\": \"2021-03-14T00:00:00Z\",\r\n" + 
				"        \"endTime\": \"2021-03-14T23:59:59Z\",\r\n" + 
				"        \"title\": \"会议\",\r\n" + 
				"        \"description\": \"全体大会\"\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    \"displayText\": \"预定会议\",\r\n" + 
				"    \"postback\": {\r\n" + 
				"      \"data\": \"set_by_chatbot_create_calendar_event\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";
		return action;
	}

	protected void requestSugTextXml() {
		System.out.println("下发带建议回复的文本消息：");
		String sug = getSug(getReply(),getUrlAction(),getDialerAction(),getGoogleMapAction(),getCalendarAction());
		
		String text = "hello world";
		
		String body = "--next\r\n" + 
				"Content-Type: text/plain\r\n" + 
				"Content-Length: " + text.getBytes().length + "\r\n\r\n" + 
				text + "\r\n" + 
				"\r\n" + 
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" + 
				sug +
				"\r\n" + 
				"--next--\r\n";
		
		sendMessage(getXml("multipart/mixed; boundary=\"next\"",body));
	}
		
	
	protected String getXml(String contentType,String bodyText) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"    <address>tel:+86" + phone + "</address>\r\n" + 
				"    <destinationAddress>tel:+86" + phone + "</destinationAddress>\r\n" + 
				"    <senderAddress>" + getchatbotSip() + "</senderAddress>\r\n" + 
				"    <outboundIMMessage>\r\n" + 
				"        <contentType>hello,contentType,pi</contentType>\r\n" + 
				"        <conversationID>" + UUID.randomUUID().toString() + "</conversationID>\r\n" + 
				"        <contributionID>" + UUID.randomUUID().toString() +"</contributionID>\r\n" + 
				"        <serviceCapability>\r\n" + 
				"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				"        </serviceCapability>\r\n" + 
				"        <messageId>" + UUID.randomUUID().toString() + "</messageId>\r\n" + 
				"        <bodyText>\r\n" +
				"<![CDATA[\r\n" +
				"hello,bodyText,p\r\n" +
				"]]>\r\n" +
				"        </bodyText>\r\n" + 
				"    </outboundIMMessage>\r\n" + 
				"    <clientCorrelator>567895</clientCorrelator>\r\n" + 
				"</msg:outboundMessageRequest>\r\n" + 
				"";
		return xml.replace("hello,bodyText,p", bodyText).replace("hello,contentType,pi", contentType);
	}
	
	protected String tokenHeader(String appId, String password) {
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
