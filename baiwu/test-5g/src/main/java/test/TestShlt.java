package test;

import java.io.File;
import java.util.UUID;

import okhttp3.Request;
import okhttp3.RequestBody;

public class TestShlt extends AbstractDemo{
	private String spi = "sip:10655210001@botplatform.rcs.chinaunicom.cn";
	public TestShlt() {
		super("iap_201201001008","12345678");
		//联通号
		this.phone = "18611886117";
	}
	
	public static void main(String[] args) {
		TestShlt t = new TestShlt();
//		t.getFileSize();
		
		t.getSugDkpXml();
	}
	
	
	void request(String data) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = "https://220.196.54.31:8443/maapdiscovery/messaging/v1/outbound/sip:10655210001@botplatform.rcs.chinaunicom.cn/requests";
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
	
	void requestFileUpload(String file,String thumbnail) {
		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		String url = "https://ft101.sh.5gm.wo.cn:9443";
		System.out.println(url);
		System.out.println();
		System.out.println();
	}
	
	String getSug() {
		String sug = 
				"{\r\n" +
				"  \"suggestions\": [\r\n" +
				"    {\r\n" +
				"      \"reply\": {\r\n" + 
				"        \"displayText\": \"Yes\",\r\n" + 
				"        \"postback\": {\r\n" +
				"          \"data\": \"set_by_chatbot_reply_yes\"\r\n" + 
				"        }\r\n" +
				"      }\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"action\": {\r\n" + 
				"        \"urlAction\": {\r\n" + 
				"          \"openUrl\": {\r\n" +
				"            \"url\": \"https://www.google.com\"\r\n" + 
				"          }\r\n" +
				"        },\r\n" +
				"        \"displayText\": \"Open website or deep link\",\r\n" + 
				"        \"postback\": {\r\n" +
				"          \"data\": \"set_by_chatbot_open_url\"\r\n" + 
				"        }\r\n" +
				"      }\r\n" +
				"    }\r\n" +
				"  ]\r\n" +
				"}";
		return sug;
	}
	
	//1、下发带建议回复的文本消息
	void getSugTextXml() {
		System.out.println("下发带建议回复的文本消息：");
		String sug = getSug();
		
		String text = "hello,你好";
		
		String bodyText1 =
				"--next\r\n" +
				"Content-Type: text/plain\r\n" + 
				"Content-Length: " + text.getBytes().length + "\r\n\r\n" + 
				text + "\r\n" +
				"--next\r\n" +
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" +
				sug + "\r\n" +
				"--next--\r\n";
		
		request(getXml("multipart/mixed;boundary=\"next\"",bodyText1));
	}
	
	//2、下发纯文本消息
	void getTextXml() {
		System.out.println("下发纯文本消息：");
		request(getXml("text/plain","hello,你好"));
	}
	
	//3、下发纯文件消息
	void getFileXml() {
		System.out.println("下发纯文件消息：");
		request(getXml("application/vnd.gsma.rcs-ft-http+xml", getFile()));
	}
	
	//4、下发带建议回复的文件消息
	void getSugFileXml() {
		System.out.println("下发带建议回复的文件消息：");
		String file = getFile();
		
		String sug = getSug();
		
		String body = "--next" + 
				"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
				"Content-Length: " + file.getBytes().length + "\r\n\r\n" +
				file + "\r\n" + 
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + 
				sug + "\r\n" + 
				"--next--\r\n";
		request(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getDkp() {
		String dkp = 
				"{\r\n" +
				"  \"message\": {\r\n" + 
				"    \"generalPurposeCard\": {\r\n" +
				"      \"layout\": {\r\n" +
				"        \"cardOrientation\": \"HORIZONTAL\",\r\n" + 
				"        \"imageAlignment\": \"LEFT\"\r\n" +
				"      },\r\n" +
				"      \"content\": {\r\n" + 
				"        \"media\": {\r\n" +
				"          \"mediaUrl\": \"http://124.239.146.131:9000/rcs/20201127/bwkj-test/d69eb04e2d665d68443c82757a938ae2.mp4\",\r\n" + 
				"          \"mediaContentType\": \"video/mp4\",\r\n" +
				"          \"thumbnailUrl\": \"http://124.239.146.131:9000/rcs/20201127/bwkj-test/6b21dad90d149eb65da504b1856cab42.jpg\",\r\n" + 
				"          \"thumbnailContentType\": \"image/png\",\r\n" +
				"          \"height\": \"MEDIUM_HEIGHT\"\r\n" +
				"        },\r\n" +
				"        \"title\": \"This is a single rich card.\",\r\n" + 
				"        \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\"\r\n" + 
				"      }\r\n" +
				"    }\r\n" + 
				"  }\r\n" +
				"}\r\n";
		return dkp;
	}
	//5、下发单卡片消息
	void getDkpXml() {
		System.out.println("下发单卡片消息：");
		request(getXml("application/vnd.gsma.botmessage.v1.0+json", getDkp()));
	}
	
	//6、下发带建议回复的单卡片消息
	void getSugDkpXml() {
		System.out.println("下发带建议回复的单卡片消息：");
		String sug = getSug();
		String dkp = getDkp();
		
		String sugDkp = "--next\r\n" +
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: [content length]\r\n\r\n" + 
				dkp + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: [content length]\r\n\r\n" +
				sug + "\r\n" +
				"--next--\r\n"; 
		request(getXml("multipart/mixed; boundary=\"next\"", sugDkp));
	}
	
	String getDuokp() {
		String duokp = "{" + 
				"  \"message\": {" + 
				"    \"generalPurposeCardCarousel\": {" + 
				"      \"layout\": {" + 
				"        \"cardWidth\": \"MEDIUM_WIDTH\"" + 
				"      }," + 
				"      \"content\": [" + 
				"        {" + 
				"          \"media\": {" + 
				"            \"mediaUrl\": \"https://cdn.server/path/media.mp4\"," + 
				"            \"mediaContentType\": \"video/mp4\"," + 
				"            \"thumbnailUrl\": \"https://cdn.server/path/media.png\"," + 
				"            \"thumbnailContentType\": \"image/png\"," + 
				"            \"height\": \"SHORT_HEIGHT\"" + 
				"          }," + 
				"          \"title\": \"This is the first rich card in a carousel.\"," + 
				"          \"description\": \"This is the description of the rich card.It's the first field that will be truncated if it exceeds the maximum width or height of a card.\"," + 
				"          \"suggestions\": [" + 
				"            {" + 
				"              \"action\": {" + 
				"                \"mapAction\": {" + 
				"                  \"showLocation\": {" + 
				"                    \"location\": {" + 
				"                      \"latitude\": 37.4220041," + 
				"                      \"longitude\": -122.0862515," + 
				"                      \"label\": \"Googleplex\"" + 
				"                    }," + 
				"                    \"fallbackUrl\": \"https://www.google.com/maps/@37.4219162,-122.078063,15z\"" + 
				"                  }" + 
				"                }," + 
				"                \"displayText\": \"Show location on a map\"," + 
				"                \"postback\": {" + 
				"                  \"data\": \"set_by_chatbot_open_map\"" + 
				"                }" + 
				"              }" + 
				"            }," + 
				"            {" + 
				"              \"action\": {" + 
				"                \"calendarAction\": {" + 
				"                  \"createCalendarEvent\": {" + 
				"                    \"startTime\": \"2017-03-14T00:00:00Z\"," + 
				"                    \"endTime\": \"2017-03-14T23:59:59Z\"," + 
				"                    \"title\": \"Meeting\"," + 
				"                    \"description\": \"GSG review meeting\"" + 
				"                  }" + 
				"                }," + 
				"                \"displayText\": \"Schedule Meeting\"," + 
				"                \"postback\": {" + 
				"                  \"data\": \"set_by_chatbot_create_calendar_event\"" + 
				"                }" + 
				"              }" + 
				"            }" + 
				"          ]" + 
				"        }," + 
				"        {" + 
				"          \"title\": \"This is the second rich card in the carousel.\"," + 
				"          \"description\": \"Carousel cards need to specify a card width in the 'layout' section. For small width cards, only short and medium height media are supported.\"," + 
				"          \"[...]\": \"[...]\"" + 
				"        }" + 
				"      ]" + 
				"    }" + 
				"  }" + 
				"}";
		return duokp;
	}
	
	//7、下发多卡片消息
	void getDuokpXml() {
		System.out.println("下发多卡片消息：");
		request(getXml("application/vnd.gsma.botmessage.v1.0+json", getDuokp()));
	}
	
	void getSugDuokpXml() {
		System.out.println("下发带建议回复的多卡片消息：");
		String sug = getSug();
		String duokp = getDuokp();
		
		String body = "--next\r\n" + 
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: " + duokp.getBytes().length + "\r\n\r\n" +
				sug + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n" +
				sug + "\r\n" +
				"--next--\r\n";
		
		request(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	//9、下发地理位置推送回落消息
	void getGeoXml() {
		System.out.println("下发地理位置推送回落消息：");
		request(getXml("text/plain;charset=UTF-8", "geo:50.7311865,7.0914591;crs=gcj02;u=10;rcs-l=Qingfeng%20Steamed%20Dumpling%20Shop%20%F0%9F%8D%9A"));
	}
	
	//10、下发群发消息
	void getGroupTextXml() {
		System.out.println("下发群发纯文本消息：");
		String xml10 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<msg:outboundMessageRequest" + 
			"    xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">" + 
			"    <Address>tel:+8619585550104</Address>" + 
			"    <destinationAddress>tel:+8619585550104</destinationAddress>" + 
			"    <destinationAddress>tel:+8619585550105</destinationAddress>" + 
			"    <destinationAddress>tel:+8619585550106</destinationAddress>" + 
			"    <senderAddress>sip:12599@botplatform.rcs.chinaunicom.com</senderAddress>" + 
			"    <senderName>MyName</senderName>" + 
			"    <outboundIMMessage>" + 
			"        <subject>hello from the rest of us!</subject>" + 
			"        <contentType>text/plain</contentType>" + 
			"        <conversationID>" + UUID.randomUUID().toString() + "</conversationID>" + 
			"        <contributionID>" + UUID.randomUUID().toString() + "</contributionID>" + 
			"        <serviceCapability>" + 
			"            <capabilityId>ChatbotSA</capabilityId>" + 
			"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>" + 
			"        </serviceCapability>" + 
			"        <messageId>" + UUID.randomUUID().toString() + "</messageId>" + 
			"        <bodyText>hello world</bodyText>" + 
			"    </outboundIMMessage>" + 
			"    <clientCorrelator>567895</clientCorrelator>" + 
			"</msg:outboundMessageRequest>";
		request(xml10);
	}
	
	//11、下发待回落UP1.0消息
	void getUpFileXml() {
		System.out.println("下发带回落的文件消息：");
		String xml11 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<msg:outboundMessageRequest" + 
			"    xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">" + 
			"    <address>tel:+86" + phone + "</address>" + 
			"    <destinationAddress>tel:+86" + phone + "</destinationAddress>" + 
			"    <senderAddress>sip:12599@botplatform.rcs.chinaunicom.com</senderAddress>" + 
			"    <senderName>MyName</senderName>" + 
			"    <outboundIMMessage>" + 
			"        <subject>hello from the rest of us!</subject>" + 
			"        <contentType> application/vnd.gsma.botmessage.v1.0+json</contentType>" + 
			"        <conversationID>" + UUID.randomUUID().toString() + "</conversationID>" + 
			"        <contributionID>" + UUID.randomUUID().toString() + "</contributionID>" + 
			"        <serviceCapability>" + 
			"            <capabilityId> ChatbotSA </capabilityId>" + 
			"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>" + 
			"        </serviceCapability>" + 
			"        <messageId>" + UUID.randomUUID().toString() + "</messageId>" + 
			"        <bodyText>" + 
			"            <![CDATA[" + 
			"{" + 
			"  \"message\": {" + 
			"    \"generalPurposeCard\": {" + 
			"      \"layout\": {" + 
			"        \"cardOrientation\": \"HORIZONTAL\"," + 
			"        \"imageAlignment\": \"LEFT\"" + 
			"      }," + 
			"      \"content\": {" + 
			"        \"media\": {" + 
			"          \"mediaUrl\": \"https://cdn.server/path/media.mp4\"," + 
			"          \"mediaContentType\": \"video/mp4\"," + 
			"          \"thumbnailUrl\": \"https://cdn.server/path/media.png\"," + 
			"          \"thumbnailContentType\": \"image/png\"," + 
			"          \"height\": \"MEDIUM_HEIGHT\"" + 
			"        }," + 
			"        \"title\": \"This is a single rich card.\"," + 
			"        \"description\": \"This is the description of the rich card. It's the first field that will be truncated if it exceeds the maximum width or height of a card.\"" + 
			"      }" + 
			"    }" + 
			"  }" + 
			"}" + 
			"			 ]]>" + 
			"        </bodyText>" + 
			"        <fallbackContentType>application/vnd.gsma.rcs-ft-http+xml</fallbackContentType>" + 
			"        <rcsBodyText>" + 
			"            <![CDATA[" + 
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
			"<file" + 
			"    xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">" + 
			"    <file-info type=\"thumbnail\">" + 
			"        <file-size>7427</file-size>" + 
			"        <content-type>image/jpeg</content-type>" + 
			"        <data url=\"https://ftcontentserver.rcs.mnc123.mcc456.pub.3gppnetwork.org/ftsf58cdb29d1-a3d3-427c-a8a4-a496759fbf6b\" until=\"2017-04-25T12:17:07Z\"/>" + 
			"        <data localurl=\"http://192.168.0.1/ftsf58cdb29d1-a3d3-427c-a8a4-a496759fbf6b\" until=\"2017-04-25T12:17:07Z\"/>" + 
			"    </file-info>" + 
			"    <file-info type=\"file\">" + 
			"        <file-size>183524</file-size>" + 
			"        <file-name>DSC_379395051.JPG</file-name>" + 
			"        <content-type>image/jpeg</content-type>" + 
			"        <data url=\"https://ftcontentserver.rcs.mnc123.mcc456.pub.3gppnetwork.org/ftsf0d5ea6d1-a94c-2-9634-2d90244d3e8e\" until=\"2017-04-25T12:17:07Z\"/>" + 
			"        <data localurl=\"http://192.168.0.1/ftsf-0d5ea6d1-a94c-2-9634-2d90244d3e8e\" until=\"2017-04-25T12:17:07Z\"/>" + 
			"    </file-info>" + 
			"</file>" + 
			"            ]]>" + 
			"        </rcsBodyText>" + 
			"    </outboundIMMessage>" + 
			"    <clientCorrelator>567895</clientCorrelator>" + 
			"</msg:outboundMessageRequest>";
		request(xml11);
	}
	
	private static String getFile() {
		File file = new File("C:\\Users\\gongxiaoliang\\Documents\\timg.jpg");
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n" + 
				"    <file-info type=\"thumbnail\">\r\n" +
				"        <file-size>" + file.length() + "</file-size>\r\n" + 
				"        <content-type>image/jpeg</content-type>\r\n" +
				"        <data url=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608208202035&di=264a57f1685fa99847a4841dd7aec9b0&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F25%2F99%2F19300000421423134190997943822.jpg\" until=\"2017-04-25T12:17:07Z\"/>\r\n" + 
				"    </file-info>\r\n" +
				"    <file-info type=\"file\">\r\n" + 
				"        <file-size>" + file.length() + "</file-size>\r\n" + 
				"        <file-name>timg.jpg</file-name>\r\n" +
				"        <content-type>image/jpeg</content-type>\r\n" + 
				"        <data url=\"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608208202035&di=264a57f1685fa99847a4841dd7aec9b0&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F25%2F99%2F19300000421423134190997943822.jpg\"/>\r\n" + 
				"    </file-info>\r\n" +
				"</file>\r\n";
		return xml;
	}
	
	public String getXml(String contentType,String bodyText) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n"
				+ "    <address>tel:+86" + phone + "</address>\r\n"
				+ "    <destinationAddress>tel:+86" + phone + "</destinationAddress>\r\n"
				+ "    <senderAddress>" + spi + "</senderAddress>\r\n"
				+ "    <senderName>MyName</senderName>\r\n"
				+ "    <outboundIMMessage>\r\n"
				+ "        <subject>hello world</subject>\r\n"
				+ "        <contentType>hello,contenttype<o>k</contentType>\r\n"
				+ "        <destinationTerminal>Native</destinationTerminal>\r\n"
				+ "        <conversationID>" + UUID.randomUUID().toString() + "</conversationID>\r\n"
				+ "        <contributionID>" + UUID.randomUUID().toString() + "</contributionID>\r\n"
				+ "        <serviceCapability>\r\n"
				+ "            <capabilityId>ChatbotSA</capabilityId>\r\n"
				+ "            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n"
				+ "        </serviceCapability>\r\n"
				+ "        <messageId>" + UUID.randomUUID().toString() + "</messageId>\r\n"
				+ "        <bodyText>\r\n"
				+ "<![CDATA[\r\n"
				+ "hello,bodytext><o><"
				+ "]]>\r\n"
				+ "        </bodyText>\r\n"
				+ "    </outboundIMMessage>\r\n"
				+ "    <clientCorrelator>1102365</clientCorrelator>\r\n"
				+ "</msg:outboundMessageRequest>";
		return xml.replace("hello,bodytext><o><", bodyText).replace("hello,contenttype<o>k",contentType);
	}
}
