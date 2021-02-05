package test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import util.CloseUtil;
import util.SHA256Util;

public class TestFjyd extends TestFjydtH5{
	public TestFjyd(String chatbotId,String appid, String password) {
		super(chatbotId,appid, password);
		init(appid,password);
		this.sendUrl = "http://112.35.162.232:8078/mbmp/developer/accesslayer/messaging/group/v1/outbound/" + getchatbotSip() + "/requests";
		this.uploadUrl = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer/Content";
		this.notifyUrl = "http://124.239.146.131:9090/rcs/api/fjyd5g/mediaCallback";
	}

	private RestTemplate restTemplate;

	public void init(String appid, String token) {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(10000);// 设置超时
		requestFactory.setReadTimeout(10000);
		this.restTemplate = new RestTemplate(requestFactory);
	}

	public static void main(String[] args) {
		//集微科技
//		String appid = "125200401111382";
//		String appSecret = "xH837(h2P0Q*rZ5U";
//		String chatbotId = "125200401111382";
		
		//百信银行
		String appid = "125200401111514";
		String appSecret = "52CzPp$X\\^k>YEcF>";
		
		//清锋时代5G消息
//		String appid = "125200401111835"; 
//		String chatbotId = "125200401111835"; 
//		String appSecret = "nF7#b<7Bk4iG/L]2";
//		String fileUrl = "https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12301903561132360460404544TD";
//		String fileUrl = "https://gz01ft.sbc.rcs.chinamobile.com:10003/s/12301903561132360460404544FD.mp4";
//		String fileUrl = "https://gz01ft.sbc.rcs.chinamobile.com:10012/s/01071514281132520021218757TD";
		
		TestFjyd t = new TestFjyd(appid,appid,appSecret);
//		t.downloadFile(chatbotId, fileUrl);
		
		t.phone = "15811491455";
//		t.url = "http://112.35.162.232:8078/mbmp/developer/accesslayer/messaging/interaction/v1/outbound/" + t.getchatbotUri(chatbotId) + "/requests";
//	
//		t.requestTextXml();
//		
//
//		t.downloadFile(chatbotId, fileUrl);
		
//		t.testSuoLueTu();
		
//		t.testZhiNengXueXi();
		
//		t.requestFileUpload();
		
		t.downloadFile("http://112.35.162.232:8078/mbmp/fileservice/accesslayer/556532080670953475");
	}
	
	void requestFileUpload() {
		String thumbnail = "file/image1-thumbnail.png";
		String thumbnailType = "image/png";
		String file = "file/image1.jpg";
		String fileType = "image/jpg";
		
		uploadFile(thumbnail,thumbnailType, file,fileType);
	}
	
	String getFileName(HttpHeaders responseHeaders) {
		List<String> contentDisposition = responseHeaders.get("Content-disposition");
		if(null != contentDisposition && contentDisposition.size() > 0) {
			String content = contentDisposition.get(0);
			return super.getFileName(content);
		}
		return null;
	}
	
	public void downloadFile(String fileUrl) {
		ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET,getRequest2(),byte[].class);
		
		int statusCode = response.getStatusCode().value();
		System.out.println("status code:" + statusCode);
		HttpHeaders responseHeaders = response.getHeaders();
		System.out.println("response headers:");
		responseHeaders.keySet().forEach(k -> {
			System.out.print(k + ":");
			responseHeaders.get(k).forEach(o -> {
				System.out.print(o);
				System.out.print(",");
			});
			System.out.println();
		});
//		System.out.println("response body:" + new String(response.getBody()));

		String filename = getFileName(responseHeaders);
		
		if(null != filename) {
			System.out.println("file name:" + filename);
			
			InputStream is = null;
			try {
				is = new ByteArrayInputStream(response.getBody());
				download(is, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				CloseUtil.close(is);
			}
		}else {
			System.out.println("response body:" + new String(response.getBody()));
		}
	}

	private HttpEntity<Void> getRequest2() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		headers.add("Date", headerDate);
		headers.add("X-3GPP-Intended-Identity", getchatbotUri());
		headers.add("User-Agent", "SP/" + getchatbotSip());
		headers.add("Terminal-type", "Chatbot");
		headers.add("Connection", "close");

//		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		return new HttpEntity<Void>(headers);
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
