package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import util.FileUtil;
import util.SHA256Util;

public class TestFjyd extends TestFjydtH5{
	public TestFjyd(String appid, String password) {
		super(appid, password);
		init(appid,password);
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
		String chatbotId = "125200401111514";
//		String fileUrl = "http://112.35.162.232:8078/mbmp/fileservice/accesslayer/543447716626194436";
		
		TestFjyd t = new TestFjyd(appid,appSecret);
//		t.downloadFile(chatbotId, fileUrl);
		
		t.phone = "15811491455";
//		t.url = "http://112.35.162.232:8078/mbmp/developer/accesslayer/messaging/interaction/v1/outbound/" + t.getchatbotUri(chatbotId) + "/requests";
//	
//		t.getTextXml();
	}

	private String getFileName(HttpHeaders responseHeaders) {
		List<String> contentDisposition = responseHeaders.get("Content-disposition");
		if(null != contentDisposition && contentDisposition.size() > 0) {
			String content = contentDisposition.get(0);
			if(null != content) {
				String[] attachment = content.split(";");
				if(attachment.length == 2) {
					String[] filename = attachment[1].split("=");
					if(filename.length == 2) {
						return filename[1];
					}
				}
			}
		}
		return null;
	}
	
	public void downloadFile(String chatbotId,String fileUrl) {
		ResponseEntity<byte[]> response = restTemplate.exchange(fileUrl, HttpMethod.GET,getRequest2(chatbotId),byte[].class);
		
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
			OutputStream os = null;
			try {
				is = new ByteArrayInputStream(response.getBody());
				
				String filePath = "tmp" + File.separator + filename;
				
				File file = new File(filePath);
				
				os = new FileOutputStream(file);
				
				int len = 0;
				byte[] buff = new byte[1024];
				while((len = is.read(buff,0,1024)) != -1) {
					os.write(buff,0,len);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				FileUtil.close(is);
				FileUtil.close(os);
			}
		}else {
			System.out.println("response body:" + new String(response.getBody()));
		}
	}

	private HttpEntity<Void> getRequest2(String chatbotId) {
		String chatbotUri = getchatbotUri(chatbotId);
//		String chatbotUri = "sip:125200401111514@botplatform.rcs.chinamobile.com";
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorization);
		headers.add("Date", headerDate);
		headers.add("X-3GPP-Intended-Identity", chatbotUri);
		headers.add("User-Agent", "SP/" + chatbotUri);
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
