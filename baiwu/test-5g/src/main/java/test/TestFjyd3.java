package test;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import util.CloseUtil;
import util.SHA256Util;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

public class TestFjyd3 extends AbstractDemo3{
	protected String host = "cmic-csp-cgw.cmicmaap.com";

	public TestFjyd3(String chatbotId, String cspId, String cspToken,String serverRoot,String fileServerRoot,String phone) {
		super(chatbotId,cspId,cspToken,serverRoot,fileServerRoot);
		this.phone = "+86"+phone;
	}
	
	public static void main(String[] args) {
		String cspToken = "NzYyMjc5MTQ2OTExMzU3ZjE3ZjhlZjI5OWNkY2U5NTE3NTZmYzA3MDg0YTQ4ZjRlMWUxNDIyODYwY2NiODExZA==";
		String cspId = "202102240001";
		String chatbotId = "2021022410001";
		String serverRoot = "https://cmic-csp-cgw.cmicmaap.com";
		String fileServerRoot = "https://cmic-csp-cgw.cmicmaap.com";

		String phone = "15132291613";

		TestFjyd3 t = new TestFjyd3(chatbotId,cspId,cspToken,serverRoot,fileServerRoot,phone);
		
//		t.requestSugDuokpXml();
//		t.requestDuokpXml();
//		t.requestSugDkpXml();
//		t.requestDkpXml();
//		t.requestSugFileXml();
//		t.requestFileXml();
//		t.requestSugTextXml();
		t.requestTextXml();

//		t.requestFileUpload();

//		t.downloadFile("https://http01.hzq.rcs.chinamobile.com:9091/Access/PF?ID=MUQzM0NERUFGM0MzRUExQTQ2M0I2NjY4Q0MyNDkyOEZFNzZGNkZFRTVDMzNGNjM1REY5MDg5OThERDZFQUY4MDExRjAyODkzQUE2QUQ3MzU2NkVFQzdERTJEQTQ0QzM2");

		t.revokeMessage("50c73aab-95d9-4081-bb9c-6d11e90a6b8f");
	}


	void downloadFile2(String fileUrl) {
		System.out.println("下载文件：");

		System.out.println("request ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
		System.out.println(fileUrl);

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);// 设置超时
		requestFactory.setReadTimeout(10000);
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		InputStream is = null;
		OutputStream os = null;
		try {
			ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET, getRequest(null), byte[].class);

			System.out.println("response ---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: ");
			System.out.println(response);
			System.out.println();

			String filename = getFileName(response.getHeaders());

			if(null != filename) {
				is = new ByteArrayInputStream(response.getBody());

				String filePath = "tmp/" + filename;

				File file = new File(filePath);

				os = new FileOutputStream(file);

				int len = 0;
				byte[] buf = new byte[1024];
				while((len = is.read(buf,0,1024)) != -1) {
					os.write(buf,0,len);
				}
				os.flush();

				System.out.println("下载成功：" + filePath);
			}else {
				System.out.println("下载失败：" + new String(response.getBody()));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseUtil.close(is);
			CloseUtil.close(os);
		}
	}

	void requestFileUpload() {
		//图片
//		String thumbnail = "D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\thumbnail2.jpg";
//		String thumbnailType = "image/jpg";
//		String file = "D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\image2.png";
//		String fileType = "image/png";

		String thumbnail = "D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\thumbnail2.jpg";
		String thumbnailType = "image/jpg";
		String file = "D:\\prodata\\github\\test\\baiwu\\test-5g\\file\\video1.mp4";
		String fileType = "image/png";

		uploadFile(thumbnail,thumbnailType, file,fileType);
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
		FileInfo3 fileInfo = getFileInfo();
		
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
		FileInfo3 fileInfo = getFileInfo();
		
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
		FileInfo3 fileInfo = getFileInfo();
		
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<file xmlns=\"urn:gsma:params:xml:ns:rcs:rcs:fthttp\" xmlns:e=\"urn:gsma:params:xml:ns:rcs:rcs:up:fthttpext\">\n" +
				"\t<file-info type=\"thumbnail\">\n" +
				"\t\t<file-size>\n" +
				"\t\t\t6624\n" +
				"\t\t</file-size>\n" +
				"\t\t<content-type>\n" +
				"\t\t\timage/jpeg\n" +
				"\t\t</content-type>\n" +
				"\t\t<data url=\"https://http01.hzq.rcs.chinamobile.com:9091/Access/PF?ID=MUQzM0NERUFGM0MzRUExQTQ2M0I2NjY4Q0MyNDkyOEZFNzZGNkZFRTVDMzNGNjM1REY5MDg5OThERDZFQUY4MDExRjAyODkzQUE2QUQ3MzU2NkVFQzdERTJEQTQ0QzM2\" until=\"2020-11-22T16:08:47Z\" />\n" +
				"\t</file-info>\n" +
				"\t<file-info type=\"file\">\n" +
				"\t\t<file-size>\n" +
				"\t\t\t69617\n" +
				"\t\t</file-size>\n" +
				"\t\t<file-name>\n" +
				"\t\t\t9b1e705c9feabbfa28454872cc4488e.png\n" +
				"\t\t</file-name>\n" +
				"\t\t<content-type>\n" +
				"\t\t\timage/png\n" +
				"\t\t</content-type>\n" +
				"\t\t<data url=\"https://http01.hzq.rcs.chinamobile.com:9091/Access/PF?ID=MUQzM0NERUFGM0MzRUExQTQ2M0I2NjY4Q0MyNDkyOEZFNzZGNkZFRTVDMzNGNjM1REY5MDg5OThERDZFQUY4MDExRjAyODkzQUE2QUQ3MzU2NkVFQzdERTJEQTQ0QzM2\" until=\"2020-11-22T16:08:47Z\" />\n" +
				"\t\t<file-exist>\n" +
				"\t\t\t1\n" +
				"\t\t</file-exist>\n" +
				"\t\t<branded-url>\n" +
				"\t\t\thttps://1/s/ebRDIvABscN.png\n" +
				"\t\t</branded-url>\n" +
				"\t</file-info>\n" +
				"</file>\n";
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
				"		 <reportRequest>sent</reportRequest>" +
				"		 <reportRequest>Delivered</reportRequest>" +
				"		 <reportRequest>Displayed</reportRequest>" +
				"		 <reportRequest>Failed</reportRequest>" +
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
