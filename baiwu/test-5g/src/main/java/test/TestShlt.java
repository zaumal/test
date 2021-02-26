package test;

import java.util.UUID;

import org.springframework.http.HttpHeaders;

public class TestShlt extends AbstractDemo{
	public TestShlt() {}
	public TestShlt(String chatbotId,String appid,String password) {
		super(chatbotId,appid,password);
		this.sendUrl = "https://220.196.54.31:8443/maapdiscovery/messaging/v1/outbound/" + getchatbotSip() + "/requests";
		this.revokeUrl = "https://220.196.54.31:8443/maapdiscovery/messaging/v1/outbound/" + getchatbotSip() + "/requests/messageId/status";
		this.uploadUrl = "https://ft101.sh.5gm.wo.cn:9443";
		this.deleteUrl = "https://ft101.sh.5gm.wo.cn:9443/Content";
		this.notifyUrl = "http://124.239.146.131:9090/rcs/api/shlt5g/mediaCallback";
	}
	
	public static void main(String[] args) {
		//和选电商
		String chatbotId = "10655210001";
		String appid = "iap_201201001008";
		String password = "12345678";
		
		TestShlt t = new TestShlt(chatbotId,appid,password);
		
		//联通号
		t.phone = "18611886117";
		//国芳联通手机
//		t.phone = "18518258286";
		//本奇联通手机
//		t.phone = "18501960660";

		//1、下行带建议回复的文本消息
//		t.requestSugTextXml();
//		//2、下行纯文本消息
		t.requestTextXml();
//		//3、下发纯文件消息
//		t.requestFileXml();
//		//4、下发带建议回复的文件消息
//		t.requestSugFileXml();
//		//5、下发单卡片消息
//		t.requestDkpXml();
//		//6、下发带建议回复的单卡片消息
//		t.requestSugDkpXml();
//		//7、下发多卡片消息
//		t.requestDuokpXml();
//		//8、下发带建议回复的多卡片消息
//		t.requestSugDuokpXml();
//		//9、下发地理位置推送回落消息
//		t.requestGeoXml();
		//11、下发回落up1.0
//		t.requestUp10FileXml();
//		t.requestUp10TextXml();
		//12、下发待回落短信消息
//		t.requestSmsTextXml();
		
		//撤回消息
//		String msg1 = t.requestTextXml();
//		t.revokeMessage(t.getMessageId(msg1));
//		t.revokeMessage("fasfd");
		//上传文件
		t.requestFileUpload();
		//下载文件
		t.requestDownload();
		
		//群发消息
//		t.requestGroupTextXml();
		
		//删除文件
//		t.deleteFile("0a32b5d9-8ba6-498e-8a8a-1e77f5dee158");
		
		//状态报告、撤回通知、文件审核通知、上行消息
	}
	
	FileInfo3 getFileInfo() {
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
	
	String getFile() {
		FileInfo3 fileInfo = getFileInfo();
		String thumbnailUrl = fileInfo.thumbnailFileUrl;
		String thumbnailType = fileInfo.thumbnailFileType;
		String thumbnailSize = fileInfo.thumbnailFileSize;
		String fileUrl = fileInfo.fileUrl;
		String fileType = fileInfo.fileType;
		String fileSize = fileInfo.fileSize;
		String fileName = fileInfo.fileName;
		return getFile(thumbnailUrl,thumbnailType,thumbnailSize,fileUrl,fileType,fileSize,fileName);
	}
	
	void requestDownload() {
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01151721411101410060100012FD.jpg";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01181352061101410040100010TD";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01181352061101410040100010FD.png";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181835421101410120100002FD.amr";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181843451101410130100003TD";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181843451101410130100003FD.jpg";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181846331101410100100002TD";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181846331101410100100002FD.mp4";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01181859091101410140100003TD";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01181859091101410140100003FD.mp4";
//		String url1 = "https://ft101.sh.5gm.wo.cn:10099/s/01181904031101410070100002FD.amr";
		
//		String url1 = "http://192.168.6.171:7085/rcs/shlt/20210120/upload/thumbnail/car.png";
//		String url1 = "http://192.168.6.171:7085/rcs/shlt/20210120/upload/file/car-thumbnail.jpg";
		String url1 = "https://ft101.sh.5gm.wo.cn:10001/s/01181846331101410100100002FD.mp4";
		downloadFile2(url1);
	}
	
	String getFileName(HttpHeaders responseHeaders) {
		return UUID.randomUUID().toString();
	}
	
	void requestFileUpload() {
//		String thumbnail = null;
//		String thumbnailType = "image/jpeg";
//		String file = "tmp/c9448c06-7dde-4bf2-9766-d1d9fd53e187.amr";
//		String fileType = "audio/amr";
		
//		String thumbnail = "x:\\rcs\\20210118\\zhang001\\dac26b5675e7ecf08c7a25a48598dff5.PNG";
//		String thumbnailType = "image/PNG";
//		String file = "x:\\rcs\\20210118\\zhang001\\a6131d73887c5fa669bb4518dac83173.JPG";
//		String fileType = "image/JPG";
		
		String thumbnail = "D:\\tmp\\202101\\dac26b5675e7ecf08c7a25a48598dff5.PNG";
        String thumbnailType = "image/PNG";
//        File file = new File("x:\\rcs\\20210118\\zhang001\\a6131d73887c5fa669bb4518dac83173.JPG");
        String file = "D:\\tmp\\202101\\a6131d73887c5fa669bb4518dac83173.JPG";
        String fileType = "image/JPG";
		
		uploadFile(thumbnail,thumbnailType, file,fileType);
	}
	
	String getSug() {
		String sug = 
				"{\r\n" +
				"  \"suggestions\": [\r\n" +
				"    {\r\n" +
				"      \"reply\": {\r\n" + 
				"        \"displayText\": \"是\",\r\n" + 
				"        \"postback\": {\r\n" +
				"          \"data\": \"是data\"\r\n" + 
				"        }\r\n" +
				"      }\r\n" +
				"    },\r\n" +
				"    {\r\n" +
				"      \"action\": {\r\n" + 
				"        \"urlAction\": {\r\n" + 
				"          \"openUrl\": {\r\n" +
				"            \"url\": \"https://www.baidu.com\"\r\n" + 
				"          }\r\n" +
				"        },\r\n" +
				"        \"displayText\": \"打开百度\",\r\n" + 
				"        \"postback\": {\r\n" +
				"          \"data\": \"打开百度data\"\r\n" + 
				"        }\r\n" +
				"      }\r\n" +
				"    },\r\n" +
				"    {\r\n" + 
				"      \"action\": {\r\n" + 
				"        \"mapAction\": {\r\n" + 
				"          \"showLocation\": {\r\n" + 
				"            \"location\": {\r\n" + 
				"              \"latitude\": 37.4220041,\r\n" + 
				"              \"longitude\": -122.0862515,\r\n" + 
				"              \"label\": \"Googleplex\"\r\n" + 
				"            },\r\n" + 
				"            \"fallbackUrl\": \"https://www.google.com/maps/@37.4219162,-122.078063,15z\"\r\n" + 
				"          }\r\n" + 
				"        },\r\n" + 
				"        \"displayText\": \"显示位置\",\r\n" + 
				"        \"postback\": {\r\n" + 
				"          \"data\": \"显示位置data\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"action\": {\r\n" + 
				"        \"calendarAction\": {\r\n" + 
				"          \"createCalendarEvent\": {\r\n" + 
				"            \"startTime\": \"2017-03-14T00:00:00Z\",\r\n" + 
				"            \"endTime\": \"2017-03-14T23:59:59Z\",\r\n" + 
				"            \"title\": \"会议\",\r\n" + 
				"            \"description\": \"GSG评审会议\"\r\n" + 
				"          }\r\n" + 
				"        },\r\n" + 
				"        \"displayText\": \"安排会议\",\r\n" + 
				"        \"postback\": {\r\n" + 
				"          \"data\": \"安排会议data\"\r\n" + 
				"        }\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" +
				"}";
		return sug;
	}
	
	//1、下发带建议回复的文本消息
	void requestSugTextXml() {
		System.out.println("下发带建议回复的文本消息：");
		String sug = getSug();
		
		String text = "hello,你好";
		
		String bodyText1 =
				"--next\r\n" +
				"Content-Type: text/plain\r\n" + 
				"Content-Length: " + text.getBytes().length + "\r\n\r\n" + 
				text + "\r\n\r\n" +
				"--next\r\n" +
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" +
				sug + "\r\n" +
				"--next--\r\n";
		
		sendMessage(getXml("multipart/mixed;boundary=\"next\"",bodyText1));
	}
	
	//2、下发纯文本消息
	String requestTextXml() {
		System.out.println("下发纯文本消息：");
		return sendMessage(getXml("text/plain","hello,你好"));
	}
	
	//3、下发纯文件消息
	String requestFileXml(String thumbnailUrl,String thumbnailType,String thumbnailSize,String fileUrl,String fileType,String fileSize,String fileName) {
		System.out.println("下发纯文件消息：");
		return sendMessage(getXml("application/vnd.gsma.rcs-ft-http+xml", getFile(thumbnailUrl,thumbnailType,thumbnailSize,fileUrl,fileType,fileSize,fileName)));
	}
	
	String requestFileXml() {
		System.out.println("下发纯文件消息2：");
		return sendMessage(getXml("application/vnd.gsma.rcs-ft-http+xml", getFile()));
	}
	
	//4、下发带建议回复的文件消息
	void requestSugFileXml() {
		System.out.println("下发带建议回复的文件消息：");
		String file = getFile();
		
		String sug = getSug();
		
		String body = "--next\r\n" + 
				"Content-Type: application/vnd.gsma.rcs-ft-http+xml\r\n" + 
				"Content-Length: " + file.getBytes().length + "\r\n\r\n" +
				file + "\r\n" + 
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" +
				sug + "\r\n" + 
				"--next--\r\n";
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	String getDkp() {
		FileInfo3 fileInfo = getFileInfo();
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
				"          \"mediaUrl\": \"" + fileInfo.fileUrl + "\",\r\n" + 
				"          \"mediaContentType\": \"" + fileInfo.fileType + "\",\r\n" +
				"          \"thumbnailUrl\": \"" + fileInfo.thumbnailFileUrl + "\",\r\n" + 
				"          \"thumbnailContentType\": \"" + fileInfo.thumbnailFileType + "\",\r\n" +
				"          \"height\": \"MEDIUM_HEIGHT\"\r\n" +
				"        },\r\n" +
				"        \"title\": \"这是一个单卡片消息.\",\r\n" + 
				"        \"description\": \"这是单卡片消息的描述. 如果它超过了卡片的最大宽度和长度，内容将会被截断.\"\r\n" + 
				"      }\r\n" +
				"    }\r\n" + 
				"  }\r\n" +
				"}\r\n";
		return dkp;
	}
	
	//5、下发单卡片消息
	void requestDkpXml() {
		System.out.println("下发单卡片消息：");
		sendMessage(getXml("application/vnd.gsma.botmessage.v1.0+json", getDkp()));
	}
	
	//6、下发带建议回复的单卡片消息
	void requestSugDkpXml() {
		System.out.println("下发带建议回复的单卡片消息：");
		String sug = getSug();
		String dkp = getDkp();
		
		String sugDkp = "--next\r\n" +
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: "+ dkp.length() + "\r\n\r\n" + 
				dkp + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.length() + "\r\n\r\n" +
				sug + "\r\n" +
				"--next--\r\n"; 
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", sugDkp));
	}
	
	String getDuokp() {
		FileInfo3 fileInfo1 = getFileInfo();
		
		String duokp = "{" + 
				"  \"message\": {" + 
				"    \"generalPurposeCardCarousel\": {" + 
				"      \"layout\": {" + 
				"        \"cardWidth\": \"MEDIUM_WIDTH\"" + 
				"      }," + 
				"      \"content\": [" + 
				"        {" + 
				"          \"media\": {" + 
				"            \"mediaUrl\": \"" + fileInfo1.fileUrl + "\"," + 
				"            \"mediaContentType\": \"" + fileInfo1.fileType + "\"," + 
				"            \"thumbnailUrl\": \"" + fileInfo1.thumbnailFileUrl + "\"," + 
				"            \"thumbnailContentType\": \"" + fileInfo1.thumbnailFileType + "\"," + 
				"            \"height\": \"SHORT_HEIGHT\"" + 
				"          }," + 
				"          \"title\": \"这是第一个卡片.\"," + 
				"          \"description\": \"这是第一个卡片消息的描述. 如果它超过了卡片的最大宽度和长度，内容将会被截断.\"," + 
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
				"                \"displayText\": \"显示位置\"," + 
				"                \"postback\": {" + 
				"                  \"data\": \"显示位置data\"" + 
				"                }" + 
				"              }" + 
				"            }," + 
				"            {" + 
				"              \"action\": {" + 
				"                \"calendarAction\": {" + 
				"                  \"createCalendarEvent\": {" + 
				"                    \"startTime\": \"2017-03-14T00:00:00Z\"," + 
				"                    \"endTime\": \"2017-03-14T23:59:59Z\"," + 
				"                    \"title\": \"会议\"," + 
				"                    \"description\": \"GSG 评审会议\"" + 
				"                  }" + 
				"                }," + 
				"                \"displayText\": \"预定会议\"," + 
				"                \"postback\": {" + 
				"                  \"data\": \"预定会议data\"" + 
				"                }" + 
				"              }" + 
				"            }" + 
				"          ]" + 
				"        }," + 
				"        {" + 
				"          \"title\": \"这是第二个卡片.\"," + 
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
	void requestDuokpXml() {
		System.out.println("下发多卡片消息：");
		sendMessage(getXml("application/vnd.gsma.botmessage.v1.0+json", getDuokp()));
	}
	
	//8、下发带建议回复的多卡片消息
	void requestSugDuokpXml() {
		System.out.println("下发带建议回复的多卡片消息：");
		String sug = getSug();
		String duokp = getDuokp();
		
		String body = "\r\n--next\r\n" + 
				"Content-Type: application/vnd.gsma.botsuggestion.v1.0+json\r\n" + 
				"Content-Length: " + sug.getBytes().length + "\r\n\r\n" +
				sug + "\r\n" +
				"--next\r\n" + 
				"Content-Type: application/vnd.gsma.botmessage.v1.0+json\r\n" + 
				"Content-Length: " + duokp.getBytes().length + "\r\n\r\n" +
				duokp + "\r\n" +
				"--next--\r\n";
		
		sendMessage(getXml("multipart/mixed; boundary=\"next\"", body));
	}
	
	//9、下发地理位置推送回落消息
	void requestGeoXml() {
		System.out.println("下发地理位置推送回落消息：");
		sendMessage(getXml("text/plain;charset=UTF-8", "geo:50.7311865,7.0914591;crs=gcj02;u=10;rcs-l=Qingfeng%20Steamed%20Dumpling%20Shop%20%F0%9F%8D%9A"));
	}
	
	//10、下发群发消息
	void requestGroupTextXml() {
		System.out.println("下发群发纯文本消息：");
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n" + 
				"    <address>tel:+8618611886117</address>\r\n" + 
				"    <destinationAddress>tel:+8618611886117</destinationAddress>\r\n" + 
				"    <destinationAddress>tel:+8619585550105</destinationAddress>\r\n" + 
				"    <destinationAddress>tel:+8619585550106</destinationAddress>\r\n" + 
				"    <senderAddress>sip:10655210001@botplatform.rcs.chinaunicom.cn</senderAddress>\r\n" + 
				"    <senderName>MyName</senderName>\r\n" + 
				"    <outboundIMMessage>\r\n" + 
				"        <subject>hello world</subject>\r\n" + 
				"        <contentType>text/plain</contentType>\r\n" + 
				"        <conversationID>b1067eb2-1982-4a52-b487-c1683d19e6a2</conversationID>\r\n" + 
				"        <contributionID>d86cf5e9-f54b-46fa-aa5b-9a29e22567af</contributionID>\r\n" + 
				"        <serviceCapability>\r\n" + 
				"            <capabilityId>ChatbotSA</capabilityId>\r\n" + 
				"            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n" + 
				"        </serviceCapability>\r\n" + 
				"        <bodyText><![CDATA[hello,你好]]></bodyText>\r\n" + 
				"        <reportRequest>Delivered</reportRequest>\r\n" + 
				"        <reportRequest>Displayed</reportRequest>\r\n" + 
				"        <reportRequest>Failed</reportRequest>\r\n" + 
				"    </outboundIMMessage>\r\n" + 
				"    <clientCorrelator>1102365</clientCorrelator>\r\n" + 
				"</msg:outboundMessageRequest>";
		sendMessage(xml);
	}
	
	String getUp10File() {
		String xml = getXml("application/vnd.gsma.botmessage.v1.0+json", getDkp());
		
		String up10 = "<fallbackSupported>true</fallbackSupported>\r\n" +
				"<shortMessageSupported>true</shortMessageSupported>\r\n" + 
				"<reportRequest>SMS</reportRequest>\r\n" +
				"<fallbackContentType>application/vnd.gsma.rcs-ft-http+xml</fallbackContentType>\r\n" + 
				"<rcsBodyText><![CDATA[" + 
				getFile() + 
				"]]></rcsBodyText>\r\n";
		return xml.replace("repace-rcsBody-replace", up10);
	}
	
	//11、下发待回落UP1.0文件消息
	String requestUp10FileXml() {
		System.out.println("下发待回落up1.0文件消息：");
		return sendMessage(getUp10File());
	}
	
	String getUp10Text() {
		String xml = getXml("text/plain","hello,你好");
		String up10 = "<fallbackContentType>text/plain</fallbackContentType>\r\n" + 
				"<rcsBodyText><![CDATA[测试回落up1.0-hello]]></rcsBodyText>\r\n";
//		return getXml("application/vnd.gsma.botmessage.v1.0+json", dkp).replace("repace-rcsBody-replace", up10);
//		return getXml("application/vnd.gsma.rcs-ft-http+xml", getFile()).replace("repace-rcsBody-replace", up10);
		return xml.replace("repace-rcsBody-replace", up10);
	}
	
	//11-2、下发待回落UP1.0文本消息
	String requestUp10TextXml() {
		System.out.println("下发待回落up1.0文本消息：");
		return sendMessage(getUp10Text());
	}
	
	private static String getFile(String thumbnailUrl,String thumbnailType,String thumbnailSize,String fileUrl,String fileType,String fileSize,String fileName) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" +
				"<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\">\r\n";
		if(null != thumbnailUrl && thumbnailUrl.length() > 1) {
			xml += "    <file-info type=\"thumbnail\">\r\n" +
					"        <file-size>" + thumbnailSize + "</file-size>\r\n" + 
					"        <content-type>" + thumbnailType + "</content-type>\r\n" +
					"        <data url=\"" + thumbnailUrl + "\" until=\"2021-04-25T12:17:07Z\"/>\r\n" + 
					"    </file-info>\r\n";
		}
		xml += "    <file-info type=\"file\">\r\n" + 
				"        <file-size>" + fileSize + "</file-size>\r\n" + 
				"        <file-name>" + fileName + "</file-name>\r\n" +
				"        <content-type>" + fileType + "</content-type>\r\n" + 
				"        <data url=\"" + fileUrl + "\" until=\"2021-04-25T12:17:07Z\"/>\r\n" + 
				"    </file-info>\r\n" +
				"</file>";
		return xml;
	}
	
	String getSms() {
		String dkp = getDkp();
		
		String sms = "<shortMessageSupported>true</shortMessageSupported>\r\n" + 
				"<reportRequest>SMS</reportRequest>\r\n"
				+ "<smsBodyText><![CDATA[" + 
				"测试转短信内容" + 
				"]]></smsBodyText>\r\n";
		
		return getXml("application/vnd.gsma.botmessage.v1.0+json", dkp).replace("repace-rcsBody-replace", sms);
	}
	
	//12、下发待回落短信消息
	String requestSmsTextXml() {
		System.out.println("下发待回落短信消息：");
		return sendMessage(getSms());
	}
	
	public String getXml(String contentType,String bodyText) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
				+ "<msg:outboundMessageRequest xmlns:msg=\"urn:oma:xml:rest:netapi:messaging:1\">\r\n"
				+ "    <address>tel:+86" + phone + "</address>\r\n"
				+ "    <destinationAddress>tel:+86" + phone + "</destinationAddress>\r\n"
				+ "    <senderAddress>" + getchatbotSip() + "</senderAddress>\r\n"
				+ "    <senderName>MyName</senderName>\r\n"
				+ "    <outboundIMMessage>\r\n"
				+ "        <subject>hello world</subject>\r\n"
				+ "        <contentType>hello,contenttype<o>k</contentType>\r\n"
//				+ "        <destinationTerminal>Native</destinationTerminal>\r\n"
				+ "        <conversationID>" + UUID.randomUUID().toString() + "</conversationID>\r\n"
				+ "        <contributionID>" + UUID.randomUUID().toString() + "</contributionID>\r\n"
				+ "        <serviceCapability>\r\n"
				+ "            <capabilityId>ChatbotSA</capabilityId>\r\n"
				+ "            <version>+g.gsma.rcs.botversion=&quot;#=1&quot;</version>\r\n"
				+ "        </serviceCapability>\r\n"
//				+ "        <messageId>" + UUID.randomUUID().toString() + "</messageId>\r\n"
				+ "        <bodyText><![CDATA["
				+ "hello,bodytext><o><"
				+ "]]></bodyText>\r\n"
				+ "        <reportRequest>Delivered</reportRequest>\r\n"
				+ "        <reportRequest>Displayed</reportRequest>\r\n" 
				+ "        <reportRequest>Failed</reportRequest>\r\n"
				+ "        <reportRequest>Sent</reportRequest>\r\n"
				+ "repace-rcsBody-replace"
				+ "    </outboundIMMessage>\r\n"
				+ "    <clientCorrelator>1102365</clientCorrelator>\r\n"
				+ "</msg:outboundMessageRequest>";
		return xml.replace("hello,bodytext><o><", bodyText).replace("hello,contenttype<o>k",contentType);
	}
	
	protected String getchatbotUri() {
		return chatbotId + "@botplatform.rcs.chinaunicom.cn";
	}
}
