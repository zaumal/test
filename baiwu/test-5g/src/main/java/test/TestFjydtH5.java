package test;

import java.util.Base64;
import java.util.UUID;

import okhttp3.Request;
import okhttp3.RequestBody;
import util.SHA256Util;

public class TestFjydtH5 extends AbstractDemo{
	private String spi = "sip:12520020000120@botplatform.rcs.chinamobile.com";
	public TestFjydtH5(String appid,String password) {
		super(appid,password);
	}
	
	public static void main(String[] args) {
		TestFjydtH5 t = new TestFjydtH5("iap_12520020000120", "4f95d15126615625250926f3747dd5861b745927ce58051295989de43deeb120");
		t.phone = "15811491455";
//		this.url = "https://test.andfx.net/chatbotim/chatbot/messaging/v1/outbound/sip:12520020000120@botplatform.rcs.chinamobile.com/requests";
		
//		t.requestSugDuokpXml();
//		t.requestDuokpXml();
//		t.requestSugDkpXml();
//		t.requestDkpXml();
//		t.requestSugFileXml();
//		t.requestFileXml();
//		t.requestSugTextXml();
//		t.requestTextXml();
		
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\"><address>tel:+8615811491455</address><destinationAddress>tel:+8615811491455</destinationAddress><senderAddress>sip:12520020000120@botplatform.rcs.chinamobile.com</senderAddress><outboundIMMessage><reportRequest>Delivered</reportRequest><reportRequest>Displayed</reportRequest><contributionID>01556cec-29a1-4406-90f3-20763508b879</contributionID><conversationID>fc7ac30e-5470-4af9-a8b3-597e2bf95f33</conversationID><messageId>01556cec-29a1-4406-90f3-20763508b879</messageId><inReplyToContributionID>0f5c383c-536b-467a-a946-9bb0471621da</inReplyToContributionID><serviceCapability><capabilityId>ChatbotSA</capabilityId><version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version></serviceCapability><contentType>multipart/mixed; boundary=\"next\"</contentType>\r\n" + 
//				"<bodyText><![CDATA[\r\n" + 
//				"--next\r\n" + 
//				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
//				"Content-Disposition: inline; filename=\"Message\"\r\n" + 
//				"Content-Length: 1557\r\n" + 
//				"\r\n" + 
//				"{\"message\":{\"generalPurposeCardCarousel\":{\"layout\":{\"cardWidth\":\"MEDIUM_WIDTH\"},\"content\":[{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541011132340470300029FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"151250\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541011132340470300029TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"集微IOT\",\"description\":\"Motion+传感器\",\"suggestions\":[{\"reply\":{\"displayText\":\"必选\",\"postback\":{\"data\":\",c6d08072-8538-4895-a1d7-02f50c3d4b7d\"}}}]},{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/12081751001132300460100285FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"27654\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/12081751001132300460100285TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"小米\",\"description\":\"加湿器\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择加湿器\",\"postback\":{\"data\":\",5e00adfd-8aa1-4d2c-aa68-9a2a0c5ff77c\"}}}]},{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541001132360460400223FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"19451\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541001132360460400223TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"++GF++\",\"description\":\"地暖\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择地暖\",\"postback\":{\"data\":\",8de63428-0464-485d-9aea-d53041b8437f\"}}}]}]}}}\r\n" + 
//				"\r\n" + 
//				"--next\r\n" + 
//				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//				"Content-Disposition: inline; filename=\"Chiplist.lst\"\r\n" + 
//				"Content-Length: 18\r\n" + 
//				"\r\n" + 
//				"{\"suggestions\":[]}\r\n" + 
//				"--next--\r\n" + 
//				"]]></bodyText>\r\n" + 
//				"</outboundIMMessage><clientCorrelator>567895</clientCorrelator></msg:outboundMessageRequest>";
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\"><address>tel:+8615811491455</address><destinationAddress>tel:+8615811491455</destinationAddress><senderAddress>sip:12520020000120@botplatform.rcs.chinamobile.com</senderAddress><outboundIMMessage><reportRequest>Delivered</reportRequest><reportRequest>Displayed</reportRequest><contributionID>01556cec-29a1-4406-90f3-20763508b879</contributionID><conversationID>fc7ac30e-5470-4af9-a8b3-597e2bf95f33</conversationID><messageId>01556cec-29a1-4406-90f3-20763508b879</messageId><inReplyToContributionID>0f5c383c-536b-467a-a946-9bb0471621da</inReplyToContributionID><serviceCapability><capabilityId>ChatbotSA</capabilityId><version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version></serviceCapability><contentType>multipart/mixed; boundary=\"next\"</contentType>\r\n" + 
//				"<bodyText><![CDATA[\r\n" + 
//				"--next\r\n" + 
//				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
//				"Content-Disposition: inline; filename=\"Message\"\r\n" + 
//				"Content-Length: 1557\r\n" + 
//				"\r\n" + 
//				"{\"message\":{\"generalPurposeCardCarousel\":{\"layout\":{\"cardWidth\":\"MEDIUM_WIDTH\"},\"content\":[{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541011132340470300029FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"151250\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541011132340470300029TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"集微IOT\",\"description\":\"Motion+传感器\",\"suggestions\":[{\"reply\":{\"displayText\":\"必选\",\"postback\":{\"data\":\",c6d08072-8538-4895-a1d7-02f50c3d4b7d\"}}}]},{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/12081751001132300460100285FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"27654\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10001/s/12081751001132300460100285TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"小米\",\"description\":\"加湿器\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择加湿器\",\"postback\":{\"data\":\",5e00adfd-8aa1-4d2c-aa68-9a2a0c5ff77c\"}}}]},{\"media\":{\"mediaUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541001132360460400223FD.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"19451\",\"thumbnailUrl\":\"https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12081541001132360460400223TD\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"++GF++\",\"description\":\"地暖\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择地暖\",\"postback\":{\"data\":\",8de63428-0464-485d-9aea-d53041b8437f\"}}}]}]}}}\r\n" + 
//				"\r\n" + 
//				"--next\r\n" + 
//				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
//				"Content-Disposition: inline; filename=\"Chiplist.lst\"\r\n" + 
//				"Content-Length: 18\r\n" + 
//				"\r\n" + 
//				"{\"suggestions\":[]}\r\n" + 
//				"--next--\r\n" + 
//				"]]></bodyText>\r\n" + 
//				"</outboundIMMessage><clientCorrelator>567895</clientCorrelator></msg:outboundMessageRequest>\r\n" + 
//				"";
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\"><address>tel:+8615811491455</address><destinationAddress>tel:+8615811491455</destinationAddress><senderAddress>sip:12520020000120@botplatform.rcs.chinamobile.com</senderAddress><outboundIMMessage><reportRequest>Delivered</reportRequest><reportRequest>Displayed</reportRequest><contributionID>c20e8bea-bca2-4f4d-96ac-46bb0799c620</contributionID><conversationID>d8ddcb51-7816-4607-ad1c-3296b6c5362d</conversationID><messageId>c20e8bea-bca2-4f4d-96ac-46bb0799c620</messageId><inReplyToContributionID>889be393-c303-46a7-ae3b-618d1f9802db</inReplyToContributionID><serviceCapability><capabilityId>ChatbotSA</capabilityId><version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version></serviceCapability><contentType>multipart/mixed; boundary=\"next\"</contentType>\r\n" + 
				"<bodyText><![CDATA[\r\n" + 
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: 1605\r\n" + 
				"\r\n" + 
				"{\"message\":{\"generalPurposeCardCarousel\":{\"layout\":{\"cardWidth\":\"MEDIUM_WIDTH\"},\"content\":[{\"media\":{\"mediaUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/be818dfe52ec5ecccad6cd4000ae77c5.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"151250\",\"thumbnailUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/b79826a2a221420b59b6bb512c92b844.jpg\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"集微IOT\",\"description\":\"Motion+传感器\",\"suggestions\":[{\"reply\":{\"displayText\":\"必选\",\"postback\":{\"data\":\",c6d08072-8538-4895-a1d7-02f50c3d4b7d\"}}}]},{\"media\":{\"mediaUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/b405a8e0e3fa353042ff5d2d3b74d9fb.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"27654\",\"thumbnailUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/9a8b14bdf2b1969e6b5667e41fc5e54d.jpg\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"小米\",\"description\":\"加湿器\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择加湿器\",\"postback\":{\"data\":\",5e00adfd-8aa1-4d2c-aa68-9a2a0c5ff77c\"}}}]},{\"media\":{\"mediaUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/be9356d1e2a3f02b7f825a379a3b9b2d.jpg\",\"mediaContentType\":\"image/jpg\",\"mediaFileSize\":\"19451\",\"thumbnailUrl\":\"http://124.239.146.131:9000/rcs/20201208/jwkj658/0100d82e759e0fbda9b71c004be19ce9.jpg\",\"thumbnailContentType\":\"\",\"thumbnailFileSize\":\"\",\"height\":\"SHORT_HEIGHT\"},\"title\":\"++GF++\",\"description\":\"地暖\",\"suggestions\":[{\"reply\":{\"displayText\":\"选择地暖\",\"postback\":{\"data\":\",8de63428-0464-485d-9aea-d53041b8437f\"}}}]}]}}}\r\n" + 
				"\r\n" + 
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: 18\r\n" + 
				"\r\n" + 
				"{\"suggestions\":[]}\r\n" + 
				"--next--\r\n" + 
				"]]></bodyText>\r\n" + 
				"</outboundIMMessage><clientCorrelator>567895</clientCorrelator></msg:outboundMessageRequest>";
		t.request(xml);
	}
	
	protected void request(String data) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = "https://f.10086.cn/5g/v/chatbotim/chatbot/messaging/v1/outbound/sip:12520020000120@botplatform.rcs.chinamobile.com/requests";
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
		request(getXml("multipart/mixed; boundary=\"next\"", body));
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
		request(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getDuokp() {
		String xml = "{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"generalPurposeCardCarousel\": {\r\n" + 
				"      \"layout\": {\r\n" + 
				"        \"cardWidth\": \"MEDIUM_WIDTH\"\r\n" + 
				"      },\r\n" + 
				"      \"content\": [\r\n" + 
				"        {\r\n" + 
				"          \"media\": {\r\n" + 
				"            \"mediaUrl\": \"https://cdn.server/path/media.mp4\",\r\n" + 
				"            \"mediaContentType\": \"video/mp4\",\r\n" + 
				"            \"thumbnailUrl\": \"https://cdn.server/path/media.png\",\r\n" + 
				"            \"thumbnailContentType\": \"image/png\",\r\n" + 
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
		request(getXml("application/vnd.gsma.botmessage.v1.0+json", duokp));
	}
	
	String getDkp() {
		String xml = "{\r\n" + 
				"  \"message\": {\r\n" + 
				"    \"generalPurposeCard\": {\r\n" + 
				"      \"layout\": {\r\n" + 
				"        \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
				"        \"imageAlignment\": \"LEFT\"\r\n" + 
				"      },\r\n" + 
				"      \"content\": {\r\n" + 
				"        \"media\": {\r\n" + 
				"          \"mediaUrl\": \"https://cdn.server/path/media.mp4\",\r\n" + 
				"          \"mediaContentType\": \"video/mp4\",\r\n" + 
				"          \"thumbnailUrl\": \"https://cdn.server/path/media.png\",\r\n" + 
				"          \"thumbnailContentType\": \"image/png\",\r\n" + 
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
		request(getXml("application/vnd.gsma.botmessage.v1.0+json", dkp));
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
		request(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getFile() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" + 
				"    <file-info type=\"thumbnail\">\r\n" + 
				"        <file-size>" + getThumbnailSize() + "</file-size>\r\n" + 
				"        <content-type>image/jpeg</content-type>\r\n" + 
				"        <data url=\"" + getThumbnailUrl() + "\"/>\r\n" + 
				"    </file-info>\r\n" + 
				"    <file-info type=\"file\">\r\n" + 
				"        <file-size>" + getFileSize() +"</file-size>\r\n" + 
				"        <file-name>timg.jpg</file-name>\r\n" + 
				"        <content-type>image/jpeg</content-type>\r\n" + 
				"        <data url=\"" + getFileUrl() + "\"/>\r\n" + 
				"    </file-info>\r\n" + 
				"</file>";
		return xml;
	}
	
	protected void requestFileXml() {
		System.out.println("下发纯文件消息：");
		request(getXml("application/vnd.gsma.rcs-ft-http+xml", getFile()));
	}
	
	protected void requestTextXml() {
		System.out.println("下发纯文本消息：");
		String text = "hello word"; 
		request(getXml("text/plain",text));
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
		
		request(getXml("multipart/mixed; boundary=\"next\"",body));
	}
		
	
	protected String getXml(String contentType,String bodyText) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"    <address>tel:+86" + phone + "</address>\r\n" + 
				"    <destinationAddress>tel:+86" + phone + "</destinationAddress>\r\n" + 
				"    <senderAddress>" + spi + "</senderAddress>\r\n" + 
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
